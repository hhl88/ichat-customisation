import {ActionReducer, ActionReducerMap, combineReducers, createSelector, MetaReducer} from '@ngrx/store';
import {environment} from 'environments/environment';

import * as fromEntry from './entry';
import * as fromIChat from './ichat';

// import * as fromRouter from './router';


export interface State {
  entry: fromEntry.State;
  iChat: fromIChat.State;
  // router: fromRouter.RouterReducerState;
}

export const reducers: ActionReducerMap<State> = {
  entry: fromEntry.reducer,
  iChat: fromIChat.reducer,
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
export const getEntryState = (state: State) => state.entry;
export const getIChatState = (state: State) => state.iChat;


export const getUser = createSelector(getEntryState, fromEntry.getUser);

export const getSelectedItem = createSelector(getIChatState, fromIChat.getSelectedItem);

export const getLoadedChatFrontEnds = createSelector(getIChatState, fromIChat.getLoadedChatFrontEnds);
export const getLoadedChatLayouts = createSelector(getIChatState, fromIChat.getLoadedChatLayouts);



// export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];
export const metaReducers: MetaReducer<State>[] = !environment.production
  ? []
  : [];
