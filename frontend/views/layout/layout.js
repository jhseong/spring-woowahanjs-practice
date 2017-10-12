import Woowahan from 'woowahan';
import template from './layout.hbs';
import { UserInfoView } from './userinfo';

export default Woowahan.View.create('DefaultLayout', {

    viewDidMount($el) {
        this.addView(".navbar-right", UserInfoView);
    },

    template
});
