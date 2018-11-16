import axios from 'axios'
import JWTService from '../api/jwt.service'

export default function setup () {
  axios.interceptors.request.use( (config) => {
    config.headers = {
      "Access-Control-Allow-Origin": "*",
      'Access-Control-Allow-Origin-Type': '*',
      'Accept': '*/*'
    }
    const token = JWTService.getToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },  (err) => {
    return Promise.reject(err)
  })
}
