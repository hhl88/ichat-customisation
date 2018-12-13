import * as header from '../actions/header';
import {User} from 'core/interfaces/user.interface';

export interface State {
  user: User;
}

const initialState: State = {
  user: null
};


export function reducer(state = initialState, action: header.Actions): State {

  switch (action.type) {
    case header.USER_LOGIN: {
      // console.log(action.payload);
      return Object.assign({}, state, {
        user: action.payload
      });
    }

    case header.USER_LOGOUT: {
      return Object.assign({}, state, {
        user: null
      });
    }

    // case header.LOAD_ALL_SUCCESS: {
    //   return Object.assign({}, state, {
    //     user: action.payload
    //   });
    // }

    default: {
      return state;
    }
  }
}

export const getUser = (state: State) => state.user;

