import {Component, OnInit} from '@angular/core';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {getSelectedFrontend, getSelectedLayout} from 'store/reducers';
import {Layout} from 'core/interfaces/layout.interface';
import {Frontend} from 'core/interfaces/frontend.interface';

@Component({
  selector: 'app-ichat-content',
  templateUrl: './ichat-content.component.html',
  styleUrls: ['./ichat-content.component.scss']
})
export class IChatContentComponent implements OnInit {
  selectedLayout: Layout;
  selectedFrontend: Frontend;

  constructor(private store: Store<fromRoot.State>) {

  }

  ngOnInit() {
    this.store.pipe(select(getSelectedFrontend)).subscribe(frontend => {
      this.selectedFrontend = JSON.parse(JSON.stringify(frontend));
    });

    this.store.pipe(select(getSelectedLayout)).subscribe(layout => {
      this.selectedLayout = JSON.parse(JSON.stringify(layout));
    });
  }

}
