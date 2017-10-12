import Woowahan from 'woowahan';
import DefaultLayout from './views/layout/layout';
import * as views from './views';
import * as reducers from './reducers';
import * as actions from './actions';

global.$ = global.jQuery = Woowahan.$;

const app = new Woowahan();

app.use(Woowahan.Layout('.layout', DefaultLayout));
app.use(Woowahan.Store.create({
    gatekeeperServerDomain: ''
}));

app.use(reducers);

const routes = {
  url: '/', view: views.UserInfoView, container: '.navbar-right', layout: 'DefaultLayout',
    pages: [
        { url: '/server-info', view: views.ServerInfosView, container: '.main-contents' }
    ]
};

app.dispatch(Woowahan.Action.create(actions.SERVER_INFOS), function(data) {
    let gatekeeperServerDoamin = JSON.parse(data);
    app.getStates().gatekeeperServerDomain = gatekeeperServerDoamin.url;
    app.start(routes);
});
