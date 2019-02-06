import {Action} from '@ngrx/store';

export const USER_LOADING = 'entry/USER_LOADING';

export const USER_LOGIN = 'entry/USER_LOGIN';
export const USER_LOGOUT = 'entry/USER_LOGOUT';


/**
 * User logged in Action
 */
export class UserLoginAction implements Action {
  readonly type = USER_LOGIN;

  constructor(public payload: any) {
  }
}

/**
* Remove user from entry Actions
*/
export class UserLogoutAction implements Action {
  readonly type = USER_LOGOUT;

  constructor() {
  }
}

/**
 * Load User Action
 */
export class UserLoadingAction implements Action {
  readonly type = USER_LOADING;

  constructor(public payload?: any) { }
}

export type Actions
  = UserLoginAction
  | UserLogoutAction
  | UserLoadingAction;
