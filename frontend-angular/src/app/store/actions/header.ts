import {Action} from '@ngrx/store';

export const USER_LOGIN = 'header/USER_LOGIN';
export const USER_LOGOUT = 'header/USER_LOGOUT';
export const LOAD_ALL = 'header/LOAD_SUCCESS';


/**
 * Add user to header Actions
 */
export class UserLoginAction implements Action {
  readonly type = USER_LOGIN;

  constructor(public payload: any) {
  }
}


/**
 * Remove user from header Actions
 */
export class UserLogoutAction implements Action {
  readonly type = USER_LOGOUT;

  constructor() {
  }
}


export class LoadAllAction implements Action {
  readonly type = LOAD_ALL;

  constructor(public payload?: any) { }
}

export type Actions
  = UserLoginAction
  | UserLogoutAction;
