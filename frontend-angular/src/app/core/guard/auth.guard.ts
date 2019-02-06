import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route, CanLoad} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';

import {AuthService} from 'core/authentication/authentication.service';
import {UserLoginAction, UserLogoutAction} from 'store/actions/entry';
import {User} from 'core/interface/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(private store: Store<fromRoot.State>,
              private auth: AuthService,
              private router: Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    this.auth.fetchUser().subscribe(r => {
      if (r.body !== null) {
        this.store.dispatch(new UserLoginAction(r.body));
        return of(true);
      } else {
        this.auth.logout();
        this.router.navigate(['']);
        return of(false);
      }
    });
  }

  canLoad(route: Route): boolean {
    return true;
  }

}
