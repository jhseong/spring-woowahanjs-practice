import Woowahan from 'woowahan';
import DefaultLayout from './views/layout/layout';
import * as views from './views';
import * as reducers from './reducers';

global.$ = global.jQuery = Woowahan.$;

const app = new Woowahan();

app.use(Woowahan.Layout('.layout', DefaultLayout));
app.use(reducers);

const routes = {
  url: '/', view: views.UserInfoView, container: '.navbar-right', layout: 'DefaultLayout'
};

app.start(routes);
