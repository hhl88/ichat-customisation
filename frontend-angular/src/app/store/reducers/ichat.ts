import * as ichat from 'store/actions/ichat';
import {Frontend} from 'core/interface/frontend.interface';
import {Layout} from 'core/interface/layout.interface';
import {Item} from 'core/interface/item.interface';

export interface State {
  chatFrontEnds: Frontend[];
  chatLayouts: Layout[];
  selectedItem: Item;
  frontEndsLoading: boolean;
  frontEndsLoaded: boolean;
  layoutsLoading: boolean;
  layoutsLoaded: boolean;

}

const initialState: State = {
  chatFrontEnds: [],
  chatLayouts: [],
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


    case ichat.LAYOUT_LIST_LOADING: {
      // console.log(action.payload);
      return Object.assign({}, state, {
        layoutsLoading: true,
        layoutsLoaded: false
      });
    }

    case ichat.LAYOUT_LIST_LOAD_SUCCESS: {
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

    case ichat.CURRENT_ITEM_SELECTED: {
      return Object.assign({}, state, {
        selectedItem: action.payload
      });
    }

    default: {
      return state;
    }
  }
}

export const getSelectedItem = (state: State) => state.selectedItem;

export const getLoadedChatFrontEnds = (state: State) => state.chatFrontEnds;
export const getLoadedChatLayouts = (state: State) => state.chatLayouts;

export const getStatusChatFrontEnds = (state: State) => state.frontEndsLoaded;
export const getStatusChatLayouts = (state: State) => state.layoutsLoaded;


