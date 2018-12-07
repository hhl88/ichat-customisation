import {
  CHAT_LAYOUT_CREATE,
  CHAT_LAYOUT_UPDATE,
  FETCH_CHAT_LAYOUT, GET_CHAT_LAYOUT
} from '../constants/action.type'
import {
  SET_ERROR,
  SET_LAYOUT_LIST,
  ADD_ITEM_TO_LAYOUT_LIST,
  UPDATE_ITEM_LAYOUT_LIST,
  SET_CURRENT_LAYOUT, UNSELECT_CURRENT_LAYOUT, SET_LAYOUT
} from '../constants/mutation.type'

import {IChatService} from '../api/ichat.service'

const state = {
  errors: null,
  chatLayout: null,
  chatLayoutList: [],
  selectedChatLayout: null
}

const getters = {
  currentChatLayout (state) {
    return JSON.parse(JSON.stringify(state.chatLayout))
  },
  layoutList (state) {
    return JSON.parse(JSON.stringify(state.chatLayoutList))
  },
  selectedChatLayout (state) {
    return JSON.parse(JSON.stringify(state.selectedChatLayout))
  }
}

const actions = {
  [CHAT_LAYOUT_CREATE] (context, layout) {
    return new Promise((resolve, reject) => {
      IChatService
        .createLayout(layout)
        .then(({data}) => {
          const newLayout = JSON.parse(JSON.stringify(layout))
          newLayout.id = data.id
          context.commit(ADD_ITEM_TO_LAYOUT_LIST, newLayout)
          context.commit(SET_CURRENT_LAYOUT, newLayout)
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [CHAT_LAYOUT_UPDATE] (context, chatFrontEndSetting) {
    return new Promise((resolve, reject) => {
      IChatService
        .updateLayout(chatFrontEndSetting.id, chatFrontEndSetting)
        .then(({data}) => {
          context.commit(UPDATE_ITEM_LAYOUT_LIST, chatFrontEndSetting)
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [FETCH_CHAT_LAYOUT] (context) {

    return new Promise((resolve, reject) => {
      IChatService
        .getChatLayouts()
        .then(({data}) => {
          context.commit(SET_LAYOUT_LIST, data)
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [GET_CHAT_LAYOUT] (context, chatLayoutId) {
    return new Promise((resolve, reject) => {
      IChatService
        .getChatLayout(chatLayoutId)
        .then(({data}) => {
          context.commit(SET_LAYOUT, data)
          resolve(data)
        })
        .catch(({response}) => {
          if(response) {
            context.commit(SET_ERROR, response.data.errors)

          }
        })
    })
  }
}

const mutations = {
  [SET_ERROR] (state, error) {
    state.errors = error
  },
  [SET_LAYOUT_LIST] (state, list) {
    state.chatLayoutList = JSON.parse(JSON.stringify(list))
  },
  [ADD_ITEM_TO_LAYOUT_LIST] (state, item) {
    state.chatLayoutList.push(JSON.parse(JSON.stringify(item)))
  },
  [UPDATE_ITEM_LAYOUT_LIST] (state, item) {
    const index = state.chatLayoutList.findIndex(layout => layout.id === item.id)
    state.chatLayoutList[index] = JSON.parse(JSON.stringify(item))
  },
  [SET_CURRENT_LAYOUT] (state, chatLayout) {
    state.chatLayout = JSON.parse(JSON.stringify(chatLayout))
  },
  [SET_LAYOUT] (state, chatLayout) {
    console.log('SET_LAYOUT', chatLayout)
    state.selectedChatLayout = JSON.parse(JSON.stringify(chatLayout))
  },
  [UNSELECT_CURRENT_LAYOUT] (state) {
    state.chatLayout = null
  }
}

export default {
  state,
  actions,
  mutations,
  getters
}
