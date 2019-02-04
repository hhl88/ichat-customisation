import { Injectable } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Observable, of} from 'rxjs';
import {environment} from 'environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {USER_LOGOUT, UserLoginAction, UserLogoutAction} from 'store/actions/entry';
import {LOCAL_STORAGE_AUTH_TOKEN} from 'core/constants/storage.constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient,
              private store: Store<fromRoot.State>,
              private cookieService: CookieService) {
  }

  login(email: string, password: string): Observable<any> {

    const options = {};
    return this.httpClient.post<any>(`${environment.tokenApi}`, null, {
      params: new HttpParams()
        .set('email', email)
        .set('password', password)
        .set('grant_type', 'password')
        .set('client_id', 'clientid')
        .set('client_secret', 'secret')
    })
      .pipe(
        tap(r => {
          if (r.body === null) {
            // console.log('login failed');
            this.logout();
            return false;
          } else {
            this.store.dispatch(new UserLoginAction(r));

            localStorage.setItem(LOCAL_STORAGE_AUTH_TOKEN, r.access_token);
            // this.getUserData();
            return true;
          }
        }),
        catchError(this.handleError(USER_LOGOUT, null))
      );
  }

  getToken() {
    return localStorage.getItem(LOCAL_STORAGE_AUTH_TOKEN);
  }

  fetchUser() {
    // console.log('fetching with token: ', this.getToken());
    return this.httpClient.get(`${environment.authApi}`, {withCredentials: true, observe: 'response'});
  }

  logout() {
    localStorage.removeItem(LOCAL_STORAGE_AUTH_TOKEN);

    this.cookieService.deleteAll();

    this.store.dispatch(new UserLogoutAction());
  }

  performAutoLogin() {
    // console.log('autologin');
    const token = this.getToken();
    if (token !== null) {
      this.fetchUser().subscribe(r => {
        if (r.body !== null)
          this.store.dispatch(new UserLoginAction(r.body));
        else
          this.store.dispatch(new UserLogoutAction());
      }, catchError(this.handleError(USER_LOGOUT, null)));
    }
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      if (operation === USER_LOGOUT)
        this.logout();


      // TODO: send the error to remote logging infrastructure
      // console.error(error); // log to console instead

      // TODO: better job of transforming error for supplier consumption
      // console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


}
