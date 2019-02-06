import {Component, Input, OnInit} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {ItemType} from 'core/enum/item-type.enum';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {CurrentFrontEndSelectedAction, CurrentLayoutSelectedAction} from 'store/actions/ichat';
import {Frontend, FrontendDefault} from 'core/interfaces/frontend.interface';
import {Layout, LayoutDefault} from 'core/interfaces/layout.interface';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {
  @Input() header: ItemType;
  @Input() hideToggle: boolean;
  @Input() itemList: any[] = [];
  expanded = false;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    if (this.header === ItemType.FRONTEND) {
      this.itemList = [] as Frontend[];
    } else if (this.header === ItemType.LAYOUT) {
      this.itemList = [] as Layout[];
    }
  }

  selectItem(index: number, item) {
    if (this.header === ItemType.FRONTEND) {
      this.store.dispatch(new CurrentFrontEndSelectedAction(JSON.parse(JSON.stringify(item))));
    } else if (this.header === ItemType.LAYOUT) {
      this.store.dispatch(new CurrentLayoutSelectedAction(JSON.parse(JSON.stringify(item))));
    }

  }

  addItem() {
    let item;
    if (this.header === ItemType.FRONTEND) {
      item = FrontendDefault;
    } else if (this.header === ItemType.LAYOUT) {
      item = LayoutDefault;
    }
    item['index'] = this.itemList.length;
    item['name'] = this.header + ' ' + this.itemList.length;

    this.itemList.push(JSON.parse(JSON.stringify(item)));
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
