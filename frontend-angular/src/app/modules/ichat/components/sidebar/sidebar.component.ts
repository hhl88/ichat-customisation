import {Component, OnInit} from '@angular/core';
import {ItemType} from 'core/enum/item-type.enum';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Layout} from 'core/interfaces/layout.interface';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {getLoadedChatFrontEnds, getLoadedChatLayouts} from 'store/reducers';
import {Subscription} from 'rxjs';
import {Item} from 'core/interfaces/item.interface';
import {CurrentItemSelectedAction} from 'store/actions/ichat';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  layoutType: ItemType = ItemType.LAYOUT;
  frontendType: ItemType = ItemType.FRONTEND;

  chatFrontEnds: Frontend[] = [];
  chatLayouts: Layout[] = [];

  currentItem: Item = null;

  subFrontEnd: Subscription;
  subLayout: Subscription;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    this.subFrontEnd = this.store.pipe(select(getLoadedChatFrontEnds)).subscribe(frontends => {
      this.chatFrontEnds = frontends;
    });

    this.subLayout = this.store.pipe(select(getLoadedChatLayouts)).subscribe(layouts => {
      this.chatLayouts = layouts;
    });

  }

  changeItem(item) {
    if (this.currentItem === null || this.currentItem.index !== item.index || this.currentItem.type !== item.type) {
      this.currentItem = JSON.parse(JSON.stringify(item));
      this.store.dispatch(new CurrentItemSelectedAction(JSON.parse(JSON.stringify(item))));
    }
  }

}