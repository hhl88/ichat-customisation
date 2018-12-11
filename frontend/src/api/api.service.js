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
    Vue.axios.defaults.headers.common = {
      'Accept-Version': 1,
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json, application/x-www-form-urlencoded',
      'Access-Control-Allow-Headers': 'Origin, Accept, Content-Type, Authorization, Access-Control-Allow-Origin'
    }
  },

  // setHeader () {
  //   Vue.axios.defaults.headers['Authorization'] = `Bearer ${JwtService.getToken()}`
  // },

  post (path, body, params) {
    console.log('Path', body)
    console.log('params', params)

    return Vue.axios.post(`${path}`, body, {params: params['params'], headers: params['headers']}).catch((error) => {
      throw new Error(`[RWV] ApiService ${error}`)
    })
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
