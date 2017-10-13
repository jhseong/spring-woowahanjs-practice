export const ajax = {
  'get': (reducerContext, url, options) => requestor(reducerContext, 'get', url, options),
  'put': (reducerContext, url, options) => requestor(reducerContext, 'put', url, options),
  'post': (reducerContext, url, options) => requestor(reducerContext, 'post', url, options),
  'delete': (reducerContext, url, options) => requestor(reducerContext, 'delete', url, options),
};

function requestor(reducerContext, method, url, options = {}) {
  let defaultOptions = {
    timeout: 1000*10, // 10sec
    headers: {
      'Content-Type':'application/json'
    }
  };

  reducerContext.__method = method;
  reducerContext.__url = url;
  reducerContext.__t1 = window.performance ? window.performance.now() : Date.now();

  reducerContext[`${method}Data`](url, Object.assign(defaultOptions, options));
}

export function successHandler(response, type, xhr) {
  this.__t2 = window.performance ? window.performance.now() : Date.now();

  console.info(`%c${this.__method.toUpperCase()} %c${this.__url} %c${Math.round(this.__t2 - this.__t1)}ms`, 'color: yellow', 'color: lightgray', 'color: gray');
}

export function failHandler(error) {
  switch (error.status) {
      case 400:
      case 401:
          alert('로그인 정보가 존재하지 않습니다.');

          let gatekeeperLoginPageUrl = this.getStates().gatekeeperServerDomain + "/web/login?returnUrl=";
          window.location.replace(gatekeeperLoginPageUrl + encodeURIComponent(window.location.href));

          break;
      case 404: alert('잘못된 요청입니다.'); break;
      default: alert('시스템 오류가 발생하였습니다.'); break;
  }

  this.finish(error, null);
}
