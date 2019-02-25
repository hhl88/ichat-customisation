import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Route, CanLoad, Router} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';

import {AuthService} from 'core/authentication/authentication.service';
import {LOGIN_PAGE} from 'core/constants/routing.constants';
import {map, take, tap} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {
  isAuthenticated: Observable<boolean>;

  constructor(private store: Store<fromRoot.State>,
              private router: Router,
              private auth: AuthService) {
    this.isAuthenticated = this.auth.isAuthenticated();

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.auth.getToken()) {
      this.auth.isSessionAlive();
    } else {
      this.router.navigate([LOGIN_PAGE]);
    }
    return this.isAuthenticated;
  }

  canLoad(route: Route): boolean {
    return true;
  }


}
