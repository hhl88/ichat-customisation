// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'

import store from './services'
import AuthService from './api/api.service'
import {CHECK_AUTH} from './constants/action.type'
import interceptorsSetup from './helpers/httpInterceptor'

Vue.use(BootstrapVue)
Vue.config.productionTip = false

/* eslint-disable no-new */

AuthService.init()

router.beforeEach(
  (to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
      Promise
        .all([
          store.dispatch(CHECK_AUTH).finally(() => {
            if (!store.getters.isAuthenticated) {
              console.log('not authenticated', store)

              next('/login')
            } else {
              console.log('authenticated', store)

              return next()
            }
          })
        ])
        // .then(next)

      // Promise.all()store.dispatch(CHECK_AUTH).finally(() => {
      //   if (!store.getters.isAuthenticated) {
      //     next('/login')
      //   } else {
      //     return next()
      //   }
      // })

    } else {
      return next()
    }
  }
)

interceptorsSetup()

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


