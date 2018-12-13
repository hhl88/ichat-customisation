import ApiService from './api.service'
import {
  ICHAT_FRONTEND_API,
  ICHAT_FRONTEND_UPDATE_PATH,
  ICHAT_LAYOUT_API,
  ICHAT_LAYOUT_UPDATE_PATH
} from '../config/enviroment'

export const IChatService = {

  createFrontEnd(chatFrontEnd) {
    return ApiService.post(ICHAT_FRONTEND_API, chatFrontEnd, null)
  },

  createLayout(layout) {
    return ApiService.post(ICHAT_LAYOUT_API, layout, null)
  },

  updateFrontEnd(id, params) {
    return ApiService.put(ICHAT_FRONTEND_API, id, params)
  },

  updateLayout(id, params) {
    const formData = new FormData;
    Object.keys(params).forEach(key => {
      if (key !== 'font' && key !== 'logo') {
        formData.append(key, params[key])
      } else if (key === 'font') {
        formData.append(key, JSON.stringify(params[key]))
      } else if (key === 'logo'){
        console.log('logo', params[key])
        formData.append('logo', params[key], 'logo.png')
        console.log('logoform', formData.get('logo'))

      }
    })
    let config = {
      header : {
        'Content-Type' : 'multipart/form-data'
      }
    }

    return ApiService.put(ICHAT_LAYOUT_API, id, params, config)
  },

  getChatFrontends() {
    return ApiService.get(ICHAT_FRONTEND_API)
  },

  getChatLayouts() {
    return ApiService.get(ICHAT_LAYOUT_API)
  },

  getChatLayout(id) {
    return ApiService.get(ICHAT_LAYOUT_API + '/' + id)
  }

};

