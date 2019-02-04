import {BrowserModule, Title} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {SweetAlert2Module} from '@toverux/ngx-sweetalert2';
import {StoreModule} from '@ngrx/store';
import {metaReducers, reducers} from './store/reducers';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from 'environments/environment';
import {StoreRouterConnectingModule} from '@ngrx/router-store';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatBadgeModule, MatCheckboxModule, MatIconModule, MatNativeDateModule, MatTableModule} from '@angular/material';
import {CookieService} from 'ngx-cookie-service';
import {TokenInterceptor} from 'core/services/token.interceptor';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    MatNativeDateModule, MatCheckboxModule, MatBadgeModule, MatTableModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'X-CSRF-Token',
      headerName: 'X-CSRF-Token'
    }),
    AppRoutingModule,
    MatIconModule,

    BrowserAnimationsModule,
    SweetAlert2Module.forRoot({
      buttonsStyling: false,
      customClass: 'modal-content',
      confirmButtonClass: 'btn btn-primary',
      cancelButtonClass: 'btn'
    }),
    StoreModule.forRoot(reducers, {metaReducers}),
    // StoreRouterConnectingModule.forRoot(),
    StoreRouterConnectingModule.forRoot(),


    // !environment.production ? StoreDevtoolsModule.instrument() : [],

    StoreDevtoolsModule.instrument({
      name: 'NgRx IChat Customisation Store App',
      logOnly: environment.production,
    }),
  ],
  providers: [
    CookieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent],

})
export class AppModule {
}



