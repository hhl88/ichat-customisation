import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {Item} from 'core/interface/item.interface';
import {ItemType} from 'core/interface/item.type';
import {Frontend} from 'core/interface/frontend.interface';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {getSelectedItem} from 'store/reducers';
import {CurrentItemSelectedAction} from 'store/actions/ichat';
import {st} from '@angular/core/src/render3';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {
  @Input() header: ItemType;
  @Input() hideToggle: boolean;
  @Input() itemList: Item[] = [];
  expanded = false;
  sub: Subscription;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
  }

  selectItem(index: number, item: Item) {
    this.sub = this.store.pipe(select(getSelectedItem)).subscribe(storedItem => {
      if (storedItem === null || item.index !== storedItem.index || item.type !== storedItem.type) {
        if (this.sub) {
          this.sub.unsubscribe();
        }
        this.store.dispatch(new CurrentItemSelectedAction(JSON.parse(JSON.stringify(item))));
      }
    });
  }

  addItem() {
    this.itemList.push({
      index: this.itemList.length,
      type: this.header,
      name: this.header + ' ' + this.itemList.length
    });
  }

  expandPanel(matExpansionPanel: MatExpansionPanel, event): void {
    event.stopPropagation();
    if (this._isAddButton(event.target as HTMLElement)) {
      matExpansionPanel.open();
      this.expanded = true;
    }
  }

  private _isAddButton(target: HTMLElement): boolean {
    const addButton = 'add-button';
    const addIcon = 'add-icon';
    return (target.classList && (target.classList.contains(addButton) || target.classList.contains(addIcon)));
  }


}
