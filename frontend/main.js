import Woowahan from 'woowahan';
import DefaultLayout from './views/layout/layout';
import * as views from './views';
import * as reducers from './reducers';
import { GATEKEEPER_INFO } from './actions';

global.$ = global.jQuery = Woowahan.$;
global.GK = {
    isMessageUse: true,
};

const app = new Woowahan();

app.use(Woowahan.Layout('.layout', DefaultLayout));
app.use(Woowahan.Store.create({
    gatekeeperServerDomain: ''
}));

app.use(reducers);

const routes = {
    url: '/',
    view: views.FirstView,
    container: '.main-contents',
    layout: 'DefaultLayout',
    pages: [
        { url: '/second', container: '.main-contents', view: views.SecondView }
    ]
};

app.dispatch(Woowahan.Action.create(GATEKEEPER_INFO), function(response) {
    let gatekeeperServerDoamin = JSON.parse(response);
    app.getStates().gatekeeperServerDomain = gatekeeperServerDoamin.url;
    app.start(routes);
});
