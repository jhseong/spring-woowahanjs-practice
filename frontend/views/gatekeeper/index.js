import Woowahan from 'woowahan';
import template from './index.hbs';
import {GK_SESSION} from "../../actions/index";
import {gkAlert} from "../../reducers/gatekeeper/toolbox";

const GK_SESSION_ID = 'gk-session-id';
const GK_SUB_DOMAIN = '.woowa.in';

export const GatekeeperUserVeiw = Woowahan.View.create('GatekeeperUserVeiw', {
    template,

    initialize() {
        let sessionId = CookieHelper.read(GK_SESSION_ID);

        this.dispatch(Woowahan.Action.create(GK_SESSION, sessionId), function (response) {
            this.setModel({
                corporateName: response.corporateName,
                name: response.name
            });

            this.updateView();
        });

        this.super();
    },

    events: {
        "click .sign-out": "onSignOut"
    },

    onSignOut(event) {
        CookieHelper.delete(GK_SESSION_ID);
        this.redirectLoginPage();
    },

    redirectLoginPage() {
        let gatekeeperLoginPageUrl = this.getStates().gatekeeperServerDomain + "/web/login?returnUrl=";
        window.location.replace(gatekeeperLoginPageUrl + encodeURIComponent(window.location.href));
    }
});

var CookieHelper = {
    _getExpireTimeFromNow: function(time) {
        var currentDate = new Date();
        if (typeof(time) === 'undefined') {
            time = 7 * 24 * 60;
        }
        return new Date(currentDate.getTime() + (time * 60 * 1000)).toUTCString();
    },

    create: function(key, value, time) {
        document.cookie = key + '=' + value + '; expires=' + CookieHelper._getExpireTimeFromNow(time) + '; path=/';
    },

    read: function (key) {
        var keyParam = key + '=';
        var cookieArray = document.cookie.split(';');
        for (var i = 0; i < cookieArray.length; i++) {
            var cookie = cookieArray[i];
            while (cookie.charAt(0) == ' ') {
                cookie = cookie.substring(1, cookie.length);
            }
            if (cookie.indexOf(keyParam) == 0) {
                return cookie.substring(keyParam.length, cookie.length);
            }
        }
        return null;
    },

    delete: function (key) {
        document.cookie = key + '=; expires=' + this._getExpireTimeFromNow(-10) + '; path=/; domain=' + GK_SUB_DOMAIN + ";";
    }
}
