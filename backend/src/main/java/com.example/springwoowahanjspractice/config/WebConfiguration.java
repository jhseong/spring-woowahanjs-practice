package com.example.springwoowahanjspractice.config;

import in.woowa.platform.gatekeeper.client.GateKeeperClientConfiguration;
import in.woowa.platform.gatekeeper.client.filter.GateKeeperConfigProvideFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private UserSessionCheckerInterceptor interceptor;

	@Autowired
	private StaticResourcePathStrategy staticResourcePathStrategy;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String staticResourcePath = staticResourcePathStrategy.getStaticResourcePath();
		registry.addResourceHandler("/**").addResourceLocations(staticResourcePath);
		registry.addResourceHandler("/index.html")
				.setCacheControl(CacheControl.noStore().mustRevalidate().sMaxAge(0, TimeUnit.SECONDS))
				.addResourceLocations(staticResourcePath);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
				.excludePathPatterns("/signin/callback")
//                .excludePathPatterns("/static/*.js")
				.excludePathPatterns("/static/css/**")
		;
	}

	@Bean
	public FilterRegistrationBean gatekeeperConfigProvideFilter(
			GateKeeperClientConfiguration gateKeeperClientConfiguration) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.addUrlPatterns(GateKeeperConfigProvideFilter.DEFAULT_FILTER_URL);
		registrationBean.setFilter(new GateKeeperConfigProvideFilter(gateKeeperClientConfiguration));
		return registrationBean;
	}

	public interface StaticResourcePathStrategy {
		String getStaticResourcePath();
	}

	@Configuration
	@Profile("default")
	public static class DefaultStaticResourcePathStrategy implements StaticResourcePathStrategy {

		@Override
		public String getStaticResourcePath() {
			return String.format("file:%s/dist/", filePath());
		}

		private String filePath() {
			File currentPath = new File(System.getProperty("user.dir"));
			File[] listFiles = currentPath.listFiles(
					subDirectory -> subDirectory.isDirectory() && subDirectory.getName().equals("frontend"));
			if (listFiles == null || listFiles.length != 1) {
				throw new IllegalStateException("There is no frontend module!!");
			}

			return listFiles[0].getPath();
		}
	}

	@Configuration
	@Profile("staging")
	public static class ServerStaticResourcePathStrategy implements StaticResourcePathStrategy {

		@Override
		public String getStaticResourcePath() {
			return "classpath:/static/";
		}
	}
}
