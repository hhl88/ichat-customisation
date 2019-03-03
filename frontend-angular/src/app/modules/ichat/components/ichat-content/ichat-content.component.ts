import {Component, OnInit} from '@angular/core';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {getSelectedItem} from 'store/reducers';
import {ItemType} from 'core/enum/item-type.enum';
import {LayoutListUpdateAction, LayoutListUpdateItemAction} from 'store/actions/ichat';

@Component({
  selector: 'app-ichat-content',
  templateUrl: './ichat-content.component.html',
  styleUrls: ['./ichat-content.component.scss']
})
export class IChatContentComponent implements OnInit {
  currentItem: any;
  layoutType: ItemType = ItemType.LAYOUT;
  frontendType: ItemType = ItemType.FRONTEND;


  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    this.store.pipe(select(getSelectedItem)).subscribe(storedItem => {
      this.currentItem = storedItem;
    });

  }

  updateLayout(data) {
    this.store.dispatch(new LayoutListUpdateItemAction(JSON.parse(JSON.stringify(this._addInfoToNewItem(data.old, data.new)))));
  }

  updateFrontend(data){

  }

  private _addInfoToNewItem(oldItem, newItem) {
    newItem['index'] = oldItem['index'];
    newItem['type'] = oldItem['type'];
    newItem['editing'] = oldItem['editing'];
    return newItem;
  }

}
