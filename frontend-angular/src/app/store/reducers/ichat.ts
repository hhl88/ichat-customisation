import * as ichat from 'store/actions/ichat';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Layout} from 'core/interfaces/layout.interface';
import {Item} from 'core/interfaces/item.interface';
import {st} from '@angular/core/src/render3';

export interface State {
  chatFrontEnds: Frontend[];
  chatLayouts: Layout[];
  selectedFrontend: Frontend;
  selectedLayout: Layout;
  selectedItem: Item;
  frontEndsLoading: boolean;
  frontEndsLoaded: boolean;
  layoutsLoading: boolean;
  layoutsLoaded: boolean;
}

const initialState: State = {
  chatFrontEnds: [],
  chatLayouts: [],
  selectedFrontend: null,
  selectedLayout: null,
  selectedItem: null,
  frontEndsLoading: false,
  frontEndsLoaded: false,
  layoutsLoading: false,
  layoutsLoaded: false,
};

export function reducer(state = initialState, action: ichat.Actions): State {

  switch (action.type) {
    case ichat.FRONTEND_LIST_LOADING: {
      // console.log(action.payload);
      return Object.assign({}, state, {
        frontEndsLoading: true,
        frontEndsLoaded: false
      });
    }

    case ichat.FRONTEND_LIST_LOAD_SUCCESS: {
      return Object.assign({}, state, {
        chatFrontEnds: action.payload,
        frontEndsLoading: false,
        frontEndsLoaded: true
      });
    }

    case ichat.FRONTEND_LIST_LOAD_FAILED: {
      return Object.assign({}, state, {
        frontEndsLoading: false,
        frontEndsLoaded: false
      });
    }

    case ichat.FRONTEND_LIST_ADD: {
      state.chatFrontEnds[state.chatFrontEnds.length] = action.payload;

      return Object.assign({}, state, {
        chatFrontEnds: state.chatFrontEnds,

      });
    }

    case ichat.FRONTEND_LIST_UPDATE: {
      return Object.assign({}, state, {
        chatFrontEnds: action.payload,
      });
    }


    case ichat.LAYOUT_LIST_LOADING: {
      // console.log(action.payload);
      return Object.assign({}, state, {
        layoutsLoading: true,
        layoutsLoaded: false
      });
    }

    case ichat.LAYOUT_LIST_LOAD_SUCCESS: {
      console.log('chatlayouts', action.payload);
      return Object.assign({}, state, {
        chatLayouts: action.payload,
        layoutsLoading: false,
        layoutsLoaded: true
      });
    }

    case ichat.LAYOUT_LIST_LOAD_FAILED: {
      return Object.assign({}, state, {
        layoutsLoading: false,
        layoutsLoaded: false
      });
    }


    case ichat.LAYOUT_LIST_ADD: {
      state.chatLayouts[state.chatLayouts.length] = action.payload;
      return Object.assign({}, state, {
        chatLayouts: state.chatLayouts,

      });
    }

    case ichat.LAYOUT_LIST_UPDATE: {
      return Object.assign({}, state, {
        chatLayouts: action.payload,
      });
    }

    case ichat.LAYOUT_LIST_UPDATE_ITEM: {
      const index = action.payload.index;
      state.chatLayouts[index] = action.payload;

      return Object.assign({}, state, {
        chatLayouts: state.chatLayouts,
      });
    }

    case ichat.CURRENT_ITEM_SELECTED: {
      return Object.assign({}, state, {
        selectedItem: action.payload,
      });
    }

    case ichat.CURRENT_FRONTEND_SELECTED: {
      return Object.assign({}, state, {
        selectedFrontend: action.payload,
        selectedLayout: null
      });
    }

    case ichat.CURRENT_LAYOUT_SELECTED: {
      return Object.assign({}, state, {
        selectedLayout: action.payload,
        selectedFrontend: null
      });
    }

    default: {
      return state;
    }
  }
}


export const getSelectedItem = (state: State) => JSON.parse(JSON.stringify(state.selectedItem));

export const getSelectedFrontend = (state: State) => JSON.parse(JSON.stringify(state.selectedFrontend));
export const getSelectedLayout = (state: State) => JSON.parse(JSON.stringify(state.selectedLayout));


export const getLoadedChatFrontEnds = (state: State) => JSON.parse(JSON.stringify(state.chatFrontEnds));
export const getLoadedChatLayouts = (state: State) => JSON.parse(JSON.stringify(state.chatLayouts));

export const getStatusChatFrontEnds = (state: State) => state.frontEndsLoaded;
export const getStatusChatLayouts = (state: State) => state.layoutsLoaded;


