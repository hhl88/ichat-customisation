import ApiService from './api.service'
import {
  ICHAT_FRONTEND_API,
  ICHAT_FRONTEND_UPDATE_PATH,
  ICHAT_LAYOUT_API,
  ICHAT_LAYOUT_UPDATE_PATH
} from '../config/enviroment'

export const IChatService = {

  createFrontEnd (params) {
    return ApiService.post(ICHAT_FRONTEND_API, params)
  },

  createLayout (params) {
    return ApiService.post(ICHAT_LAYOUT_API, params)
  },

  updateFrontEnd (id, params) {
    return ApiService.put(ICHAT_FRONTEND_API, ICHAT_FRONTEND_UPDATE_PATH, id, params)
  },

  updateLayout (id, params) {
    return ApiService.put(ICHAT_LAYOUT_API, ICHAT_LAYOUT_UPDATE_PATH, id, params)
  },

  getChatFrontEndById(id) {
    return ApiService.get(ICHAT_FRONTEND_API, id)
  },

  getChatLayoutById(id) {
    return ApiService.get(ICHAT_FRONTEND_API, id)
  },

}
