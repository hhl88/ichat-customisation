import {Action} from '@ngrx/store';

export const FRONTEND_LIST_LOADING = 'ichat/FRONTEND_LIST_LOADING';
export const FRONTEND_LIST_LOAD_SUCCESS = 'ichat/FRONTEND_LIST_LOAD_SUCCESS';
export const FRONTEND_LIST_LOAD_FAILED = 'ichat/FRONTEND_LIST_LOAD_FAILED';
export const FRONTEND_LIST_UPDATE = 'ichat/FRONTEND_LIST_UPDATE';
export const FRONTEND_LIST_ADD = 'ichat/FRONTEND_LIST_ADD';


export const LAYOUT_LIST_LOADING = 'ichat/LAYOUT_LIST_LOADING';
export const LAYOUT_LIST_LOAD_SUCCESS = 'ichat/FRONTEND_LIST_LOAD_SUCCESS';
export const LAYOUT_LIST_LOAD_FAILED = 'ichat/FRONTEND_LIST_LOAD_FAILED';
export const LAYOUT_LIST_UPDATE = 'ichat/LAYOUT_LIST_UPDATE';
export const LAYOUT_LIST_ADD = 'ichat/LAYOUT_LIST_ADD';

export const CURRENT_FRONTEND_SELECTED = 'ichat/CURRENT_FRONTEND_SELECTED';
export const CURRENT_LAYOUT_SELECTED = 'ichat/CURRENT_LAYOUT_SELECTED';
export const CURRENT_ITEM_SELECTED = 'ichat/CURRENT_ITEM_SELECTED';


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
 * Frontend List Update (update/remove) in Action
 */
export class FrontEndListUpdateAction implements Action {
  readonly type = FRONTEND_LIST_UPDATE;

  constructor(public payload: any) {
  }
}

/**
 * Frontend List Add in Action
 */
export class FrontEndListAddAction implements Action {
  readonly type = FRONTEND_LIST_ADD;

  constructor(public payload: any) {
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
 * Layout List Update (update/remove) in Action
 */
export class LayoutListUpdateAction implements Action {
  readonly type = LAYOUT_LIST_UPDATE;

  constructor(public payload: any) {
  }
}

/**
 * Layout List Add in Action
 */
export class LayoutListAddAction implements Action {
  readonly type = LAYOUT_LIST_ADD;

  constructor(public payload: any) {
  }
}


/**
 *Item Selection in Action
 */
export class CurrentItemSelectedAction implements Action {
  readonly type = CURRENT_ITEM_SELECTED;

  constructor(public payload: any) {
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
  | FrontEndListUpdateAction
  | FrontEndListAddAction
  | LayoutListLoadingAction
  | LayoutListLoadSuccessAction
  | LayoutListLoadFailedAction
  | LayoutListAddAction
  | LayoutListUpdateAction
  | CurrentItemSelectedAction
  | CurrentFrontEndSelectedAction
  | CurrentLayoutSelectedAction;
