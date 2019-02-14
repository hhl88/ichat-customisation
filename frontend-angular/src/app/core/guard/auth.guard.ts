import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route, CanLoad} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';

import {AuthService} from 'core/authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(private store: Store<fromRoot.State>,
              private auth: AuthService) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const isAuthenticated: boolean = this.auth.isAuthenticated();
    if (isAuthenticated) {
      return of(true);
    } else {
      return this.auth.isSessionAlive();
    }
  }

  canLoad(route: Route): boolean {
    return true;
  }


}
