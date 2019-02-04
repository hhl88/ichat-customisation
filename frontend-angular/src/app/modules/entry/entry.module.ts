import {NgModule} from '@angular/core';
import {EntryRoutingModule} from './entry-routing.module';
import {SignInComponent} from './sign-in/sign-in.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {SharedModule} from 'shared/shared.module';
import {EntryComponent} from './entry.component';
import {UserService} from 'core/services/user.service';

@NgModule({
  declarations: [
    SignInComponent,
    SignUpComponent,
    EntryComponent
  ],
  imports: [
    SharedModule,
    EntryRoutingModule
  ],
  providers: [
    UserService
  ]
})
export class EntryModule {
}
