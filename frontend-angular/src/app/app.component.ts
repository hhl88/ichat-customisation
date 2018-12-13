import {Component, OnInit} from '@angular/core';
import {AuthService} from 'core/authentication/authentication.service';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import {routeAnimations} from 'core/animations/route.animation';
import {select, Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  animations: [routeAnimations]

})
export class AppComponent implements OnInit {
  title = 'IChat Customisation';
  languages = [];
  isLoading = true;

  isOnHomepage: boolean;

  constructor(private translate: TranslateService,
              private authService: AuthService,
              private store: Store<fromRoot.State>,
              public router: Router) {

  }

  ngOnInit(): void {
    this.authService.performAutoLogin();
    this.router.events.subscribe(val => {
      this.isOnHomepage = this.router.url === '/';
    });
    this.initLanguage();

    this.store.pipe(select(fromRoot.getUser)).subscribe(user => {
      if (user) {
        this.router.navigate(['ichat']);
      }
    });
  }


  private initLanguage() {

    this.translate.setDefaultLang('en');

  }
}
