// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'

import store from './services'
import { CHECK_AUTH } from './constants/action.type'
import AuthService from './api/api.service'

Vue.use(BootstrapVue);
Vue.config.productionTip = false

/* eslint-disable no-new */

AuthService.init()

router.beforeEach(
  (to, from, next) => {
    return Promise
      .all([store.dispatch(CHECK_AUTH)])
      .then(next)
  }
)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");


