import Woowahan from 'woowahan';
import { successHandler, failHandler, ajax } from './toolbox';
import { SERVER_INFOS } from '../actions';

export const serverInfos = Woowahan.Reducer.create(SERVER_INFOS, function(options) {
    this.use(Woowahan.Reducer.SUCCESS, successHandler);
    this.use(Woowahan.Reducer.FAIL, failHandler);

    this.onSuccess = function(response) {
        this.finish(null, response);
    };

    ajax.get(this, '/v1/domain-info');
});
