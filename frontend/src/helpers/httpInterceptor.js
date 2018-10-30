import axios from 'axios'
import JWTService from '../api/jwt.service'

export default function setup () {
  axios.interceptors.request.use( (config) => {
    const token = JWTService.getToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },  (err) => {
    return Promise.reject(err)
  })
}
