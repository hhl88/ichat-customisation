import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Router} from '@angular/router';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  stepCase = 0;


  constructor(private cd: ChangeDetectorRef,
              private store: Store<fromRoot.State>,
              private router: Router) {
  }

  ngOnInit() {

    this.cd.detectChanges();
    this.store.pipe(select(fromRoot.getUser)).subscribe(user => {
      if (user) {
        this.router.navigate(['ichat']);
      }
    });
  }

  onSwitchEntryType(stepCase: number) {
    this.stepCase = stepCase;
    this.cd.detectChanges();
  }

  onLoggedIn(event) {
  }

  onRegistered(event) {
  }

  onCloseSign(event) {
  }

}
