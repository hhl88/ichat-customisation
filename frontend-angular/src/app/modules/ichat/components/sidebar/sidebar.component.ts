import {Component, OnInit} from '@angular/core';
import {ItemType} from 'core/interface/item.type';
import {Frontend} from 'core/interface/frontend.interface';
import {Layout} from 'core/interface/layout.interface';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {getLoadedChatFrontEnds, getLoadedChatLayouts} from 'store/reducers';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  itemTypes: ItemType[] = [];
  chatFrontEnds: Frontend[] = [];
  chatLayouts: Layout[] = [];

  subFrontEnd: Subscription;
  subLayout: Subscription;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    this.itemTypes.push(ItemType.LAYOUT);
    this.itemTypes.push(ItemType.FRONTEND);
    this.subFrontEnd = this.store.pipe(select(getLoadedChatFrontEnds)).subscribe(frontends => {
      this.chatFrontEnds = frontends;
    });


    this.subLayout = this.store.pipe(select(getLoadedChatLayouts)).subscribe(layouts => {
      this.chatLayouts = layouts;
    });

  }

}
