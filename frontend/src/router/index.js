import Vue from 'vue'
import Router from 'vue-router'
import IChatConfiguration from '../modules/ichat-configuration/pages/IChatConfiguration'
import Registration from '../modules/registration/pages/Registration'
import Login from '../modules/login/pages/Login'


Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/ichat/:view?', component: IChatConfiguration,
      name: 'ichat',
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '',
      name:'registration',
      component: Registration
    },
    {
      path: '/login',
      name:'login',
      component: Login
    },
  ]
})
