import Vue from 'vue'
import Vuex from 'vuex'

import auth from './auth.module'
import frontend from './ichat-frontend.module'
import layout from './ichat-layout.module'
import iAgentServer from './iagent-server.module'


Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth,
    frontend,
    layout,
    iAgentServer
  }
});
