import {Component, Input, OnInit} from '@angular/core';
import { Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Frontend} from 'core/interfaces/frontend.interface';

@Component({
  selector: 'app-frontend-content',
  templateUrl: './frontend-content.component.html',
  styleUrls: ['./frontend-content.component.scss']
})
export class FrontendContentComponent implements OnInit {
  @Input() frontEnd: Frontend;

  constructor(private store: Store<fromRoot.State>) {

  }

  ngOnInit() {
    console.log('frontend', this.frontEnd);
  }

}
