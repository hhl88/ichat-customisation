import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {Observable, of} from 'rxjs';
import {environment} from 'environments/environment';
import {HttpClient, HttpParams} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {USER_LOGOUT, UserLoginAction, UserLogoutAction} from 'store/actions/entry';
import {LOCAL_STORAGE_AUTH_TOKEN} from 'core/constants/storage.constants';
import {Router} from '@angular/router';
import {ICHAT_PAGE} from 'core/constants/routing.constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isAuthenticated = false;

  constructor(private httpClient: HttpClient,
              private store: Store<fromRoot.State>,
              private cookieService: CookieService) {
  }

  login(email: string, password: string): Observable<any> {
    return this.httpClient.post<any>(`${environment.tokenApi}`, null, {
      params: new HttpParams()
        .set('username', email)
        .set('password', password)
        .set('grant_type', 'password')
        .set('client_id', 'clientid')
        .set('client_secret', 'secret')
    });
  }

  getToken() {
    return localStorage.getItem(LOCAL_STORAGE_AUTH_TOKEN);
  }

  private _fetchUser() {
    return this.httpClient.get(`${environment.authApi}`, {withCredentials: true, observe: 'response'}).pipe(map(res => {
      if (res && res.body) {
        this._isAuthenticated = true;
        this.store.dispatch(new UserLoginAction(res.body));
      } else {
        this._isAuthenticated = false;
        this.logout();
      }
      return res;
    }));
  }

  logout() {
    localStorage.removeItem(LOCAL_STORAGE_AUTH_TOKEN);
    this.cookieService.deleteAll();
    this.store.dispatch(new UserLogoutAction());
  }

  isAuthenticated(): boolean {
    return this._isAuthenticated;
  }

  isSessionAlive(): Observable<any> {
    if (!this.getToken()) {
      return of(false);
    }
    return of(
      this._fetchUser()
        .subscribe((res) => {
          this._isAuthenticated = true;
        }, (err) => {
          this._isAuthenticated = false;
          // observer.error(err); // won't work here you need to use next
        })
    );
  }

}
