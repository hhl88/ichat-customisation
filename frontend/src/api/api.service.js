import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

import {SERVER_URL} from '../config/enviroment'
import {TIMEOUT} from '../constants/constants'

const ApiService = {

  init () {
    Vue.use(VueAxios, axios)
    Vue.axios.defaults.baseURL = SERVER_URL
    Vue.axios.defaults.timeout = TIMEOUT
  },

  // setHeader () {
  //   Vue.axios.defaults.headers['Authorization'] = `Bearer ${JwtService.getToken()}`
  // },

  post (path, body, params) {
    return Vue.axios.post(`${path}`, body, {params: params})
  },

  get (path, id = '', subPath = '') {
    return Vue.axios
      .get(`${path}/${id}${subPath}`)
      .catch((error) => {
        throw new Error(`[RWV] ApiService ${error}`)
      })
  },

  put (path, id, params) {
    return Vue.axios
      .put(`${path}/${id}`, params)
  }

}

export default ApiService
