import * as entry from 'store/actions/entry';
import {User} from 'core/interfaces/user.interface';

export interface State {
  user: User;
}

const initialState: State = {
  user: null
};

export function reducer(state = initialState, action: entry.Actions): State {

  switch (action.type) {
    case entry.USER_LOGIN: {
      // console.log(action.payload);
      return Object.assign({}, state, {
        user: action.payload
      });
    }

    case entry.USER_LOGOUT: {
      return Object.assign({}, state, {
        user: null
      });
    }

    case entry.USER_LOADING: {
      return Object.assign({}, state, {
      });
    }

    default: {
      return state;
    }
  }
}

export const getUser = (state: State) => state.user;
