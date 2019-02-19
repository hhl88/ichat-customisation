import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';

import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {IChatService} from '../../../core/services/ichat.service';
import {
  FrontEndListLoadingAction,
  FrontEndListLoadSuccessAction,
  LayoutListLoadingAction,
  LayoutListLoadSuccessAction
} from 'store/actions/ichat';
import {ItemType} from 'core/enum/item-type.enum';
import {ActivatedRoute} from '@angular/router';
import {ICHAT_PAGE, PASSWORD_PAGE} from 'core/constants/routing.constants';
import {AuthService} from 'core/authentication/authentication.service';

@Component({
  selector: 'app-ichat',
  templateUrl: './ichat.component.html',
  styleUrls: ['./ichat.component.scss']
})
export class IChatComponent implements OnInit {
  isPasswordPage = false;

  constructor(private store: Store<fromRoot.State>,
              private iChatService: IChatService,
              private authService: AuthService,
              private location: Location,
              private router: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.router.snapshot.pathFromRoot[1].url[0].path === PASSWORD_PAGE) {
      this.isPasswordPage = true;
    } else {
      this.isPasswordPage = false;
    }
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
      list[i]['editing'] = false;

    }
  }

  onNavigateToPasswordPage(isNavigatingToPasswordPage: boolean) {
    if (!this.isPasswordPage && isNavigatingToPasswordPage) {
      this.location.replaceState(PASSWORD_PAGE);
    } else {
      this.location.replaceState(ICHAT_PAGE);
    }
    this.isPasswordPage = isNavigatingToPasswordPage;

  }

  logout() {
    this.authService.logout();
    window.location.reload();
  }


}
