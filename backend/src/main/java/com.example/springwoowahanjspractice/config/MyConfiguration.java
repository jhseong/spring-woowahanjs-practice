package com.example.springwoowahanjspractice.config;

import in.woowa.platform.gatekeeper.client.DefaultGateKeeperLoginCheckService;
import in.woowa.platform.gatekeeper.client.GateKeeperClient;
import in.woowa.platform.gatekeeper.client.GateKeeperClientConfiguration;
import in.woowa.platform.gatekeeper.client.GateKeeperLoginCheckService;
import in.woowa.platform.gatekeeper.client.GateKeeperRestClient;
import in.woowa.platform.gatekeeper.client.rest.HttpComponentsRestClientTemplate;
import in.woowa.platform.gatekeeper.client.rest.RestClientOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ServerInfoProperties.class)
public class MyConfiguration {

    @Autowired
    private ServerInfoProperties serverInfoProperties;

    @Bean
    public GateKeeperClientConfiguration gateKeeperClientConfiguration() {
        return GateKeeperClientConfiguration
                .Builder.create()
                .baseUrl(serverInfoProperties.getDomain())
                .connectionRequestTimeout(1000) //milliseconds
                .socketTimeout(1000) //milliseconds
                .connectTimeout(1000)  //milliseconds
                .maxConnTotal(30) //maxConnectionTotal 갯수
                .build();
    }

    @Bean
    public RestClientOperation restClientOperation(GateKeeperClientConfiguration configuration) {
        return new HttpComponentsRestClientTemplate(configuration);
    }

    @Bean
    public GateKeeperClient gateKeeperApiService(RestClientOperation restClientOperation,
                                                 GateKeeperClientConfiguration configuration) {
        return new GateKeeperRestClient(restClientOperation,configuration);
    }

    @Bean
    public GateKeeperLoginCheckService gateKeeperLoginCheckService(GateKeeperClient gateKeeperClient,
                                                                   GateKeeperClientConfiguration configuration) {
        return new DefaultGateKeeperLoginCheckService(gateKeeperClient, configuration);
    }
}
