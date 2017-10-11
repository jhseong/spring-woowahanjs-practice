import Woowahan from 'woowahan';
import template from './layout.hbs';
import LoginNavView from '../userinfo';

export default Woowahan.View.create('DefaultLayout', {

    viewDidMount($el) {
        this.addView("#gk_nav", LoginNavView);
    },

    template
});
