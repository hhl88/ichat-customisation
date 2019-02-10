import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {IChatService} from 'ichat/services/ichat.service';
import {
  FrontEndListLoadingAction,
  FrontEndListLoadSuccessAction,
  LayoutListLoadingAction,
  LayoutListLoadSuccessAction
} from 'store/actions/ichat';
import {ItemType} from 'core/enum/item-type.enum';

@Component({
  selector: 'app-ichat',
  templateUrl: './ichat.component.html',
  styleUrls: ['./ichat.component.scss']
})
export class IChatComponent implements OnInit {

  constructor(private store: Store<fromRoot.State>,
              private iChatService: IChatService) {
  }

  ngOnInit() {
    this.fetchChatFrontEnd();
    this.fetchChatLayout();
  }

  fetchChatFrontEnd() {
    this.store.dispatch(new FrontEndListLoadingAction());
    this.iChatService.getChatFrontends().subscribe(frontends => {
      this._modifyData(frontends, ItemType.FRONTEND);
      this.store.dispatch(new FrontEndListLoadSuccessAction(frontends));
    });
  }

  fetchChatLayout() {
    this.store.dispatch(new LayoutListLoadingAction());
    this.iChatService.getChatLayouts().subscribe(layouts => {
      this._modifyData(layouts, ItemType.LAYOUT);

      this.store.dispatch(new LayoutListLoadSuccessAction(layouts));

    });
  }

  private _modifyData(list: any[], type: ItemType) {
    for (let i = 0; i < list.length; i++) {
      list[i]['index'] = i;
      list[i]['type'] = type;
    }
  }

}