import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route, CanLoad} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';

import {AuthService} from 'core/authentication/authentication.service';
import {USER_LOGOUT, UserLoginAction} from 'store/actions/entry';
import {ICHAT_PAGE} from 'core/constants/routing.constants';
import {catchError, tap} from 'rxjs/operators';

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
    return this.auth.fetchUser()
      .pipe(
        tap(r => {
          if (r.body !== null) {
            this.store.dispatch(new UserLoginAction(r.body));
            this.router.navigate([ICHAT_PAGE]);
            return of(true);
          } else {
            this.auth.logout();
            this.router.navigate(['']);
            return of(false);
          }
        }),
        catchError(this.handleError(USER_LOGOUT, null))
      );
    // return this.auth.fetchUser().subscribe(r => {
    //   if (r.body !== null) {
    //     this.store.dispatch(new UserLoginAction(r.body));
    //     this.router.navigate([ICHAT_PAGE]);
    //     return of(true);
    //   } else {
    //     this.auth.logout();
    //     this.router.navigate(['']);
    //     return of(false);
    //   }
    // });
  }

  canLoad(route: Route): boolean {
    return true;
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      if (operation === USER_LOGOUT)


      // TODO: send the error to remote logging infrastructure
      // console.error(error); // log to console instead

      // TODO: better job of transforming error for supplier consumption
      // console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
        return of(result as T);
    };
  }

}
