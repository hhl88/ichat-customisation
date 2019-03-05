import * as ichat from 'store/actions/ichat';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Layout} from 'core/interfaces/layout.interface';
import {Item} from 'core/interfaces/item.interface';

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

    case ichat.FRONTEND_LIST_ADD: {
      const newItem = JSON.parse(JSON.stringify(action.payload));
      return Object.assign({}, state, {
        chatFrontEnds: [...state.chatFrontEnds, newItem]

      });
    }

    case ichat.FRONTEND_LIST_REMOVE: {
      return Object.assign({}, state, {
        chatFrontEnds: state.chatFrontEnds.filter(chatLayout => chatLayout['_uid'] !== action.payload._uid),
      });
    }

    case ichat.FRONTEND_LIST_UPDATE_ITEM: {
      return Object.assign({}, state, {
        chatFrontEnds: state.chatFrontEnds.map(
          (chatFrontEnd, i) => chatFrontEnd._uid === action.payload._uid ? {...chatFrontEnd, ...action.payload}
            : chatFrontEnd)
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


    case ichat.LAYOUT_LIST_ADD: {
      const newItem = JSON.parse(JSON.stringify(action.payload));
      return Object.assign({}, state, {
        chatLayouts: [...state.chatLayouts, newItem],
      });
    }

    case ichat.LAYOUT_LIST_REMOVE: {
      return Object.assign({}, state, {
        chatLayouts: state.chatLayouts.filter(chatLayout => chatLayout['_uid'] !== action.payload._uid),
      });
    }

    case ichat.LAYOUT_LIST_UPDATE_ITEM: {
      return Object.assign({}, state, {
        chatLayouts: state.chatLayouts.map(
          (chatLayout, i) => chatLayout._uid === action.payload._uid ? {...chatLayout, ...action.payload}
            : chatLayout)
      });
    }

    case ichat.CURRENT_ITEM_SELECTED: {
      return Object.assign({}, state, {
        selectedItem: JSON.parse(JSON.stringify(action.payload)),
      });
    }

    default: {
      return state;
    }
  }
}


export const getSelectedItem = (state: State) => JSON.parse(JSON.stringify(state.selectedItem));

export const getLoadedChatFrontEnds = (state: State) => JSON.parse(JSON.stringify(state.chatFrontEnds));
export const getLoadedChatLayouts = (state: State) => JSON.parse(JSON.stringify(state.chatLayouts));



