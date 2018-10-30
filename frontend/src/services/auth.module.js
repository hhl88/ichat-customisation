import { LOGIN, LOGOUT, REGISTER, CHECK_AUTH } from '../constants/action.type'
import { SET_AUTH, SET_ERROR, PURGE_AUTH } from '../constants/mutation.type'

import JwtService from '../api/jwt.service'
import ApiService from '../api/api.service'
import {UserService} from '../api/users.service'


const state = {
  errors: null,
  user: {},
  isAuthenticated: !!JwtService.getToken()
}

const getters = {
  currentUser (state) {
    return state.user
  },
  isAuthenticated (state) {
    return state.isAuthenticated
  }
}

const actions =  {
  [LOGIN] (context, {email, password}) {
    console.log('authModule', email, password);

    return new Promise((resolve, reject) => {
      UserService
        .login({email, password})
        .then(({data}) => {
          console.log('success', data);
          context.commit(SET_AUTH, data.user)
          resolve(data)
        })
        .catch(({response}) => {
          console.log('error', response);
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [LOGOUT] (context) {
    context.commit(PURGE_AUTH)
  },
  [REGISTER] (context, {email}) {
    return new Promise((resolve, reject) => {
      UserService
        .register(email)
        .then(({data}) => {
          context.commit(SET_AUTH, data.user)
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [CHECK_AUTH] (context) {
    if (JwtService.getToken()) {
      ApiService.setHeader()
      UserService
        .getUser()
        .then(({data}) => {
          context.commit(SET_AUTH, data.user)
        })
        .catch(({response}) => {
          context.commit(SET_ERROR, response.data.errors)
        })
    } else {
      context.commit(PURGE_AUTH)
    }
  },
}

const mutations = {
  [SET_ERROR] (state, error) {
    state.errors = error
  },
  [SET_AUTH] (state, user) {
    state.isAuthenticated = true
    state.user = user
    state.errors = {}
    JwtService.saveToken(state.user.token)
  },
  [PURGE_AUTH] (state) {
    state.isAuthenticated = false
    state.user = {}
    state.errors = {}
    JwtService.destroyToken()
  }
}

export default {
  state,
  actions,
  mutations,
  getters
}
