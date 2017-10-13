import Woowahan from 'woowahan';
import template from './layout.hbs';
import { GatekeeperUserVeiw } from '../gatekeeper';

export default Woowahan.View.create('DefaultLayout', {

    viewDidMount($el) {
        this.addView("#gk_nav", GatekeeperUserVeiw);
    },

    template
});
