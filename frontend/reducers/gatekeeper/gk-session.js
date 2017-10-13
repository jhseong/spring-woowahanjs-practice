import Woowahan from 'woowahan';
import { successHandler, failHandler, ajax } from './toolbox';
import { GK_SESSION} from "../../actions/index";

export const gkSessionReducer = Woowahan.Reducer.create(GK_SESSION, function(data) {
    this.use(Woowahan.Reducer.SUCCESS, successHandler);
    this.use(Woowahan.Reducer.FAIL, failHandler);

    this.onSuccess = function (response) {
        this.finish(response);
    };

    let url = this.getStates().gatekeeperServerDomain + '/v1/sessions/' + data;
    ajax.get(this, url);
});
