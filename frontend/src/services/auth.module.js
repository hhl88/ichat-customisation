import {LOGIN, LOGOUT, REGISTER, CHECK_AUTH} from '../constants/action.type'
import {SET_AUTH, SET_TOKEN, SET_ERROR, PURGE_AUTH} from '../constants/mutation.type'

import JwtService from '../api/jwt.service'
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

const actions = {
  [LOGIN] (context, {email, password}) {
    return new Promise((resolve, reject) => {
      UserService
        .login({email, password})
        .then(({data}) => {
          context.commit(SET_TOKEN, data)
          resolve(data)
        })
        .catch(({response}) => {
          context.commit(PURGE_AUTH)
        })
    })
  },
  [LOGOUT] (context) {
    context.commit(PURGE_AUTH)
  },
  [REGISTER] (context, {email}) {
    context.commit(PURGE_AUTH)
    return new Promise((resolve, reject) => {
      UserService
        .register(email)
        .then(({data}) => {
          context.commit(SET_TOKEN, data)
          resolve(data)
        })
        .catch(({response}) => {
            context.commit(SET_ERROR, response.data.errors)
        })
    })
  },
  [CHECK_AUTH] (context) {
    if (JwtService.getToken()) {
      // ApiService.setHeader()
      UserService
        .fetchUser()
        .then(({data}) => {
          context.commit(SET_AUTH, data)
        })
        .catch(({response}) => {
          context.commit(PURGE_AUTH);
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
  [SET_TOKEN] (state, token) {
    state.errors = {}
    JwtService.saveToken(token.access_token)
  },
  [SET_AUTH] (state, user) {
    state.isAuthenticated = true
    state.user = user
    state.errors = {}
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
