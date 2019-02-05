import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {Item} from 'core/interface/item.interface';
import {ItemType} from 'core/interface/item.type';
import {Frontend} from 'core/interface/frontend.interface';

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

  constructor() {
  }

  ngOnInit() {
  }

  selectItem(index: number, item: Item) {

  }

  addItem() {
    this.itemList.push({name: this.header + ' ' + this.itemList.length});
  }

  expandPanel(matExpansionPanel: MatExpansionPanel, event): void {
    event.stopPropagation();
    console.log(matExpansionPanel);
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
