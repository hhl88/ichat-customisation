import Vue from 'vue'
import Vuex from 'vuex'

import auth from './auth.module'
import frontend from './ichat-frontend.module'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    frontend
  }
});
