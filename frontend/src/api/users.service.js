import ApiService from './api.service'

import {UPDATE_PASSWORD_PATH, USERS_PATH, USR_FRONTEND_PATH, USR_LAYOUT_PATH, TOKEN_PATH, AUTH_PATH} from '../config/enviroment'

export const UserService = {

  register (email) {
    console.log(email);
    return ApiService.post(USERS_PATH, {"email": email}, null)
  },

  login(data) {
    const params = {
      'grant_type' : 'password',
      'client_id' : 'clientid',
      'client_secret': 'secret',
      'username': data.email,
      'password': data.password
    }
    return ApiService.post(TOKEN_PATH, null, params)
  },

  changePassword (user, data) {
    return ApiService.put(USERS_PATH, UPDATE_PASSWORD_PATH, user.id, data)
  },

  fetchUser() {
    return ApiService.get(AUTH_PATH)
  },



}
