import {CHAT_FRONTEND_CREATE} from '../constants/action.type'
import {SET_ERROR} from '../constants/mutation.type'

import {IChatService} from '../api/ichat.service'

const state = {
  errors: null,
  chatFrontEnd: {},
  chatFrontEndList: []
}

const getters = {
  currentChatFrontEnd (state) {
    return state.chatFrontEnd
  },
}

const actions = {
  [CHAT_FRONTEND_CREATE] (context, {chatFrontEndSetting}) {
    console.log('chatfrontend', chatFrontEndSetting)
    return new Promise((resolve, reject) => {
      IChatService
        .createFrontEnd(chatFrontEndSetting)
        .then(({data}) => {
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
}

const mutations = {
  [SET_ERROR] (state, error) {
    state.errors = error
  },
}

export default {
  state,
  actions,
  mutations,
  getters
}
