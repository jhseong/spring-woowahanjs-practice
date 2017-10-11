import Woowahan from 'woowahan';
import template from './index.hbs';

import {SERVER_INFOS} from '../../actions';

export const ServerInfosView = Woowahan.View.create('ServerInfosView', {
    template,

    initialize() {
        this.dispatch(Woowahan.Action.create(SERVER_INFOS), this.getServerInfos);

        this.super();
    },

    getServerInfos(data) {
        var domain = JSON.parse(data);
        console.log("data ==> " + domain.url);
        $(".domain").text(domain.url);
    }
});
