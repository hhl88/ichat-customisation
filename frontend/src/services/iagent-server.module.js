import {
  FETCH_IAGENT_SERVER
} from '../constants/action.type'
import {
  SET_ERROR,
  SET_IAGENT_SERVER
} from '../constants/mutation.type'

import {IAgentService} from '../api/iagent.service'

const state = {
  errors: null,
  isLoggedIn: false
}


const getters = {
  isLoggedIn (state) {
    return (state.isLoggedIn)
  },
}

const actions = {
  [FETCH_IAGENT_SERVER] (context, layout) {
    return new Promise((resolve, reject) => {
      IAgentService
        .fetchIAgentServer(layout)
        .then(({data}) => {
          context.commit(SET_IAGENT_SERVER, true)
          resolve(data)
        })
        .catch(({response}) => {
          console.log('response', response)
          context.commit(SET_IAGENT_SERVER, false)
          context.commit(SET_ERROR, response)
          resolve(response)
        })
    })
  }
}

const mutations = {
  [SET_ERROR] (state, error) {
    state.errors = error
  },
  [SET_IAGENT_SERVER] (state, status) {
    state.chatLayoutList = status
  }
}

export default {
  state,
  actions,
  mutations,
  getters
}
