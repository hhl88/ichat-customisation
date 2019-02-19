import {ChangeDetectorRef, Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {ItemType} from 'core/enum/item-type.enum';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {
  CurrentItemSelectedAction, FrontEndListAddAction, FrontEndListUpdateAction, LayoutListAddAction, LayoutListUpdateAction
} from 'store/actions/ichat';
import {Frontend, FrontendDefault} from 'core/interfaces/frontend.interface';
import {Layout, LayoutDefault} from 'core/interfaces/layout.interface';
import {getSelectedItem} from 'store/reducers';
import {Subscription} from 'rxjs';
import {Item} from 'core/interfaces/item.interface';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {
  @Input() header: ItemType;
  @Input() itemList: any[] = [];
  @Input() isActive: boolean;
  @Output() onChangeItem = new EventEmitter();
  isFrontEnd: boolean;
  isLayout: boolean;
  selectedIndex: number;
  expanded = false;

  formName: FormGroup;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    this.formName = new FormGroup({
      itemName: new FormControl('')
    });
    if (this.header === ItemType.FRONTEND) {
      this.isFrontEnd = true;
      this.isLayout = false;
      this.itemList = [] as Frontend[];
    } else if (this.header === ItemType.LAYOUT) {
      this.isFrontEnd = false;
      this.isLayout = true;
      this.itemList = [] as Layout[];
    }
  }

  selectItem(index: number, item, event) {
    console.log('click');
    event.stopPropagation();
    this.selectedIndex = index;
    this.onChangeItem.emit(item);
  }

  changeName(item) {
    this.formName.reset();
    this.formName.controls['itemName'].setValue(item.name);
    this.itemList.forEach(i => i.editing = false);
    item.editing = true;
  }

  submitNewName(item) {
    item.name = this.formName.getRawValue().itemName;
    item.editing = false;
    this.formName.reset();
    if (this.isFrontEnd) {
      this.store.dispatch(new FrontEndListUpdateAction(JSON.parse(JSON.stringify(this.itemList))));

    } else if (this.isLayout) {
      this.store.dispatch(new LayoutListUpdateAction(JSON.parse(JSON.stringify(this.itemList))));
    }

  }

  addItem() {
    let item;
    if (this.isFrontEnd) {
      item = FrontendDefault;
      item.type = ItemType.FRONTEND;
    } else if (this.isLayout) {
      item = LayoutDefault;
      console.log('item', item);
      item.type = ItemType.LAYOUT;

    }
    const length = this.itemList.length;
    item['index'] = length;
    item['editing'] = false;

    item['name'] = this.header + ' ' + length;

    this.itemList.push(JSON.parse(JSON.stringify(item)));
    if (this.isFrontEnd) {
      this.store.dispatch(new FrontEndListAddAction(JSON.parse(JSON.stringify(item))));
    } else if (this.isLayout) {
      this.store.dispatch(new LayoutListAddAction(JSON.parse(JSON.stringify(item))));
    }

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

  @HostListener('document:click', ['$event']) clickout(event) {
    console.log('clickout side');
    this.itemList.forEach(i => i.editing = false);
  }

}
