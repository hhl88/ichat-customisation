import {ActionReducer, ActionReducerMap, combineReducers, createSelector, MetaReducer} from '@ngrx/store';
import {environment} from 'environments/environment';


import * as fromHeader from './header';


// import * as fromRouter from './router';


export interface State {
  header: fromHeader.State;

  // router: fromRouter.RouterReducerState;
}

export const reducers: ActionReducerMap<State> = {
  header: fromHeader.reducer,
  // router: fromRouter.routerReducer,
};


export function logger(reduce: ActionReducer<State>): ActionReducer<State> {
  return (state: State, action: any): any => {
    const result = reduce(state, action);
    console.groupCollapsed(action.type);
    console.log('prev state', state);
    console.log('action', action);
    console.log('next state', result);
    console.groupEnd();

    return result;
  };
}

// const developmentReducer: ActionReducer<State> = compose(storeFreeze, combineReducers)(reducers);
export const productionReducer: ActionReducer<State> = combineReducers(reducers);

export function reducer(state: any, action: any) {
//   if (environment.production) {
  return productionReducer(state, action);
//   } else {
//     return developmentReducer(state, action);
//   }
}

export const getHeaderState = (state: State) => state.header;


export const getUser = createSelector(getHeaderState, fromHeader.getUser);


// export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];
export const metaReducers: MetaReducer<State>[] = !environment.production
  ? []
  : [];
