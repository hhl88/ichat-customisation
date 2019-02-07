import {Component, Input, OnInit} from '@angular/core';
import {MatExpansionPanel} from '@angular/material';
import {ItemType} from 'core/enum/item-type.enum';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {
  CurrentFrontEndSelectedAction,
  CurrentLayoutSelectedAction,
  FrontEndListLoadSuccessAction,
  LayoutListLoadSuccessAction
} from 'store/actions/ichat';
import {Frontend, FrontendDefault} from 'core/interfaces/frontend.interface';
import {Layout, LayoutDefault} from 'core/interfaces/layout.interface';
import {getLoadedChatFrontEnds, getLoadedChatLayouts} from 'store/reducers';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {
  @Input() header: ItemType;
  @Input() hideToggle: boolean;
  @Input() frontEndList: Frontend[] = [];
  @Input() layoutList: Layout[] = [];
  isFrontEnd: boolean;
  isLayout: boolean;

  expanded = false;
  isLoading = true;

  constructor(private store: Store<fromRoot.State>) {
  }

  ngOnInit() {
    this.isFrontEnd = this.header === ItemType.FRONTEND;
    this.isLayout = this.header === ItemType.LAYOUT;
    this.isLoading = true;
    if (this.isFrontEnd) {
      this.store.pipe(select(getLoadedChatFrontEnds)).subscribe(frontends => {
        this.frontEndList = frontends;
        this.isLoading = false;
      });
    } else if (this.isLayout) {
      this.store.pipe(select(getLoadedChatLayouts)).subscribe(layouts => {
        this.layoutList = layouts;
        this.isLoading = false;
      });
    }

  }

  selectItem(index: number, item) {
    if (this.isFrontEnd) {
      this.store.dispatch(new CurrentFrontEndSelectedAction(JSON.parse(JSON.stringify(item))));
    } else if (this.isLayout) {
      this.store.dispatch(new CurrentLayoutSelectedAction(JSON.parse(JSON.stringify(item))));
    }

  }

  addItem() {
    if (this.isFrontEnd) {
      const item = FrontendDefault;
      item['index'] = this.frontEndList.length;
      item['name'] = this.header + ' ' + this.frontEndList.length;
      this.frontEndList.push(JSON.parse(JSON.stringify(item)));
      this.store.dispatch(new FrontEndListLoadSuccessAction(JSON.parse(JSON.stringify(this.frontEndList))));
    } else if (this.isLayout) {
      const item = LayoutDefault;
      item['index'] = this.layoutList.length;
      item['name'] = this.header + ' ' + JSON.parse(JSON.stringify(this.layoutList)).length;
      this.layoutList.push(JSON.parse(JSON.stringify(item)));
      this.store.dispatch(new LayoutListLoadSuccessAction(JSON.parse(JSON.stringify(this.layoutList))));
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


}
