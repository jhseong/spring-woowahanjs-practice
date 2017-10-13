import Woowahan from 'woowahan';
import { successHandler, failHandler, ajax } from './toolbox';
import { GATEKEEPER_INFO } from '../../actions/index';


export const serverInfos = Woowahan.Reducer.create(GATEKEEPER_INFO, function(options) {
    this.use(Woowahan.Reducer.SUCCESS, successHandler);
    this.use(Woowahan.Reducer.FAIL, failHandler);

    this.onSuccess = function(response) {
        this.finish(response);
    };

    ajax.get(this, '/v1/gatekeeper-info');
});
