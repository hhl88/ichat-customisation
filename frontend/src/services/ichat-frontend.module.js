import {CHAT_FRONTEND_CREATE, FETCH_CHAT_FRONTEND, CHAT_FRONTEND_UPDATE} from '../constants/action.type'
import {
  SET_ERROR,
  SET_FRONT_END_LIST,
  SET_CURRENT_FRONT_END,
  ADD_ITEM_TO_FRONT_END_LIST,
  UPDATE_ITEM_FRONT_END_LIST, UNSELECT_CURRENT_FRONT_END, ADD_ITEM_TO_LAYOUT_LIST, SET_CURRENT_LAYOUT
} from '../constants/mutation.type'

import {IChatService} from '../api/ichat.service'

const state = {
  errors: null,
  chatFrontEnd: null,
  chatFrontEndList: []
};

const getters = {
  currentChatFrontEnd(state) {
    return JSON.parse(JSON.stringify(state.chatFrontEnd))
  },
  frontEndList(state) {
    return JSON.parse(JSON.stringify(state.chatFrontEndList))
  }
};

const actions = {
  [CHAT_FRONTEND_CREATE](context, frontend) {
    return new Promise((resolve, reject) => {
      IChatService
        .createFrontEnd(frontend)
        .then(({data}) => {
          const newFrontEnd = JSON.parse(JSON.stringify(frontend));
          newFrontEnd.id = data.id;
          context.commit(ADD_ITEM_TO_LAYOUT_LIST, newFrontEnd);
          context.commit(SET_CURRENT_LAYOUT, newFrontEnd);
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [CHAT_FRONTEND_UPDATE](context, item) {
    return new Promise((resolve, reject) => {
      IChatService
        .updateFrontEnd(item.id, item)
        .then(({data}) => {
          context.commit(UPDATE_ITEM_FRONT_END_LIST, item);
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [FETCH_CHAT_FRONTEND](context) {

    return new Promise((resolve, reject) => {
      IChatService
        .getChatFrontends()
        .then(({data}) => {
          context.commit(SET_FRONT_END_LIST, data);
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  }
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_FRONT_END_LIST](state, list) {
    state.chatFrontEndList = JSON.parse(JSON.stringify(list));
  },
  [ADD_ITEM_TO_FRONT_END_LIST](state, item) {
    state.chatFrontEndList.push(JSON.parse(JSON.stringify(item)));
  },
  [UPDATE_ITEM_FRONT_END_LIST](state, item) {
    const index = state.chatFrontEndList.findIndex(frontend => frontend.id === item.id);
    state.chatFrontEndList[index] = JSON.parse(JSON.stringify(item));
  },
  [SET_CURRENT_FRONT_END](state, chatFrontEnd) {
    state.chatFrontEnd = JSON.parse(JSON.stringify(chatFrontEnd));
  },
  [UNSELECT_CURRENT_FRONT_END](state) {
    state.chatFrontEnd = null
  }
};

export default {
  state,
  actions,
  mutations,
  getters
}
