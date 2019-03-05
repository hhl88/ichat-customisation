import {Action} from '@ngrx/store';

export const FRONTEND_LIST_LOADING = 'ichat/FRONTEND_LIST_LOADING';
export const FRONTEND_LIST_LOAD_SUCCESS = 'ichat/FRONTEND_LIST_LOAD_SUCCESS';
export const FRONTEND_LIST_LOAD_FAILED = 'ichat/FRONTEND_LIST_LOAD_FAILED';
export const FRONTEND_LIST_ADD = 'ichat/FRONTEND_LIST_ADD';
export const FRONTEND_LIST_REMOVE = 'ichat/FRONTEND_LIST_REMOVE';
export const FRONTEND_LIST_UPDATE_ITEM = 'ichat/FRONTEND_LIST_UPDATE_ITEM';


export const LAYOUT_LIST_LOADING = 'ichat/LAYOUT_LIST_LOADING';
export const LAYOUT_LIST_LOAD_SUCCESS = 'ichat/LAYOUT_LIST_LOAD_SUCCESS';
export const LAYOUT_LIST_LOAD_FAILED = 'ichat/LAYOUT_LIST_LOAD_FAILED';
export const LAYOUT_LIST_ADD = 'ichat/LAYOUT_LIST_ADD';
export const LAYOUT_LIST_REMOVE = 'ichat/LAYOUT_LIST_REMOVE';
export const LAYOUT_LIST_UPDATE_ITEM = 'ichat/LAYOUT_LIST_UPDATE_ITEM';


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
 * Frontend List Add in Action
 */
export class FrontEndListAddAction implements Action {
  readonly type = FRONTEND_LIST_ADD;

  constructor(public payload: any) {
  }
}

/**
 * Frontend List Remove in Action
 */
export class FrontEndListRemoveAction implements Action {
  readonly type = FRONTEND_LIST_REMOVE;

  constructor(public payload: any) {
  }
}

/**
 * Frontend List Update Item in Action
 */
export class FrontendListUpdateItemAction implements Action {
  readonly type = FRONTEND_LIST_UPDATE_ITEM;

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
 * Layout List Add in Action
 */
export class LayoutListAddAction implements Action {
  readonly type = LAYOUT_LIST_ADD;

  constructor(public payload: any) {
  }
}

/**
 * Layout List Remove in Action
 */
export class LayoutListRemoveAction implements Action {
  readonly type = LAYOUT_LIST_REMOVE;

  constructor(public payload: any) {
  }
}

/**
 * Layout List Update Item in Action
 */
export class LayoutListUpdateItemAction implements Action {
  readonly type = LAYOUT_LIST_UPDATE_ITEM;

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



export type Actions
  = FrontEndListLoadingAction
  | FrontEndListLoadSuccessAction
  | FrontEndListLoadFailedAction
  | FrontEndListAddAction
  | FrontEndListRemoveAction
  | FrontendListUpdateItemAction

  | LayoutListLoadingAction
  | LayoutListLoadSuccessAction
  | LayoutListLoadFailedAction
  | LayoutListAddAction
  | LayoutListRemoveAction
  | LayoutListUpdateItemAction
  | CurrentItemSelectedAction;
