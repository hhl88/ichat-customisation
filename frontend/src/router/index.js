import Vue from 'vue'
import Router from 'vue-router'
import IChatConfiguration from '../modules/ichat-configuration/pages/IChatConfiguration'
import Registration from '../modules/entry/registration/Registration'
import Login from '../modules/entry/login/Login'
import Preview from '../modules/preview/pages/Preview'
import Chat from '../modules/preview/components/Chat'


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
    {
      path: '/preview',
      name:'preview',
      component: Preview
    },
    {
      path: '/chat',
      name:'chat',
      component: Chat
    },
  ]
})
