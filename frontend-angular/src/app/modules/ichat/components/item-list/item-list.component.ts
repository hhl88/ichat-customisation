import {Component, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {ItemType} from 'core/enum/item-type.enum';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {FrontEndListAddAction, FrontendListUpdateItemAction, LayoutListAddAction, LayoutListUpdateItemAction} from 'store/actions/ichat';
import {Frontend, FrontendDefault} from 'core/interfaces/frontend.interface';
import {Layout, LayoutDefault} from 'core/interfaces/layout.interface';
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
      this.store.dispatch(new FrontendListUpdateItemAction(item));

    } else if (this.isLayout) {
      this.store.dispatch(new LayoutListUpdateItemAction(item));
    }

  }

  addItem() {
    let item;
    if (this.isFrontEnd) {
      item = FrontendDefault;
      item.type = ItemType.FRONTEND;
    } else if (this.isLayout) {
      item = LayoutDefault;
      item.type = ItemType.LAYOUT;

    }
    item._uid = new Date().valueOf();
    item.editing = false;

    item['name'] = this.header;

    this.itemList.push(JSON.parse(JSON.stringify(item)));
    if (this.isFrontEnd) {
      this.store.dispatch(new FrontEndListAddAction(item));
    } else if (this.isLayout) {
      this.store.dispatch(new LayoutListAddAction(item));
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
    this.itemList.forEach(i => i.editing = false);
  }

}
