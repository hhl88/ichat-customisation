import ApiService from './api.service'
import {
  ICHAT_FRONTEND_API,
  ICHAT_FRONTEND_UPDATE_PATH,
  ICHAT_LAYOUT_API,
  ICHAT_LAYOUT_UPDATE_PATH
} from '../config/enviroment'

export const IChatService = {

  createFrontEnd (chatFrontEnd) {
    console.log('chatfrontend', chatFrontEnd);
    return ApiService.post(ICHAT_FRONTEND_API, chatFrontEnd, null)
  },

  createLayout (layout) {
    return ApiService.post(ICHAT_LAYOUT_API, layout, null)
  },

  updateFrontEnd (id, params) {
    return ApiService.put(ICHAT_FRONTEND_API, id, params)
  },

  updateLayout (id, params) {
    return ApiService.put(ICHAT_LAYOUT_API, id, params)
  },

  getChatFrontends() {
    return ApiService.get(ICHAT_FRONTEND_API)
  },

  getChatLayouts() {
    return ApiService.get(ICHAT_LAYOUT_API)

  }

};

