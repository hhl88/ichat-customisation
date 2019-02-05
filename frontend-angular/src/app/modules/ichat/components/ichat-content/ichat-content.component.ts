import {Component, OnInit} from '@angular/core';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Item} from 'core/interface/item.interface';
import {getSelectedItem} from 'store/reducers';
import {ItemType} from 'core/interface/item.type';

@Component({
  selector: 'app-ichat-content',
  templateUrl: './ichat-content.component.html',
  styleUrls: ['./ichat-content.component.scss']
})
export class IChatContentComponent implements OnInit {
  currentItem: Item;
  itemTypes: ItemType[] = [];
  constructor(private store: Store<fromRoot.State>) {
    this.itemTypes.push(ItemType.LAYOUT);
    this.itemTypes.push(ItemType.FRONTEND);
  }

  ngOnInit() {
    this.store.pipe(select(getSelectedItem)).subscribe(storedItem => {
      this.currentItem = JSON.parse(JSON.stringify(storedItem));
    });
  }

}
