import {Action} from '@ngrx/store';

export const FRONTEND_LIST_LOADING = 'ichat/FRONTEND_LIST_LOADING';
export const FRONTEND_LIST_LOAD_SUCCESS = 'ichat/FRONTEND_LIST_LOAD_SUCCESS';
export const FRONTEND_LIST_LOAD_FAILED = 'ichat/FRONTEND_LIST_LOAD_FAILED';

export const LAYOUT_LIST_LOADING = 'ichat/LAYOUT_LIST_LOADING';
export const LAYOUT_LIST_LOAD_SUCCESS = 'ichat/FRONTEND_LIST_LOAD_SUCCESS';
export const LAYOUT_LIST_LOAD_FAILED = 'ichat/FRONTEND_LIST_LOAD_FAILED';

export const CURRENT_FRONTEND_SELECTED = 'ichat/CURRENT_FRONTEND_SELECTED';
export const CURRENT_LAYOUT_SELECTED = 'ichat/CURRENT_LAYOUT_SELECTED';


/**
 * Frontend List Loading in Action
 */
export class FrontEndListLoadingAction implements Action {
  readonly type = FRONTEND_LIST_LOADING;

  constructor() {
  }
}

/**
 * Frontend List loaded successful in Action
 */
export class FrontEndListLoadSuccessAction implements Action {
  readonly type = FRONTEND_LIST_LOAD_SUCCESS;

  constructor(public payload: any) {
  }
}

/**
 * Frontend List loaded fail in Action
 */
export class FrontEndListLoadFailedAction implements Action {
  readonly type = FRONTEND_LIST_LOAD_FAILED;

  constructor() {
  }
}


/**
 * Layout List Loading in Action
 */
export class LayoutListLoadingAction implements Action {
  readonly type = LAYOUT_LIST_LOADING;

  constructor() {
  }
}

/**
 * Layout List loaded successful in Action
 */
export class LayoutListLoadSuccessAction implements Action {
  readonly type = LAYOUT_LIST_LOAD_SUCCESS;

  constructor(public payload: any) {
  }
}

/**
 * Layout List loaded fail in Action
 */
export class LayoutListLoadFailedAction implements Action {
  readonly type = LAYOUT_LIST_LOAD_FAILED;

  constructor() {
  }
}


/**
 * Frontend Selection in Action
 */
export class CurrentFrontEndSelectedAction implements Action {
  readonly type = CURRENT_FRONTEND_SELECTED;

  constructor(public payload: any) {
  }
}

/**
 * Layout Selection in Action
 */
export class CurrentLayoutSelectedAction implements Action {
  readonly type = CURRENT_LAYOUT_SELECTED;

  constructor(public payload: any) {
  }
}

export type Actions
  = FrontEndListLoadingAction
  | FrontEndListLoadSuccessAction
  | FrontEndListLoadFailedAction
  | LayoutListLoadingAction
  | LayoutListLoadSuccessAction
  | LayoutListLoadFailedAction
  | CurrentFrontEndSelectedAction
  | CurrentLayoutSelectedAction;
