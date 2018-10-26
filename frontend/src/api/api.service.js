import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import JwtService from './jwt.service'

import {API_URL} from '../config/enviroment'
import {TIMEOUT} from '../constants/constants'

const ApiService = {

  init () {
    Vue.use(VueAxios, axios)
    Vue.axios.defaults.baseURL = API_URL
    Vue.axios.defaults.timeout = TIMEOUT
  },

  setHeader () {
    Vue.axios.defaults.headers['Authorization'] = `Bearer ${JwtService.getToken()}`
  },

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

  put (path, updatePath, id, params) {
    return Vue.axios
      .put(`${path}/${id}${updatePath}`, params)
  }

}

export default ApiService
