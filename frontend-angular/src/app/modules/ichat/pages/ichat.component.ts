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
    this.iChatService.getChatFrontends().subscribe(async frontends => {
      this.store.dispatch(new FrontEndListLoadSuccessAction(frontends));
    });
  }

  fetchChatLayout() {
    this.store.dispatch(new LayoutListLoadingAction());
    this.iChatService.getChatLayouts().subscribe(async layouts => {
      this.store.dispatch(new LayoutListLoadSuccessAction(layouts));

    });
  }

}
