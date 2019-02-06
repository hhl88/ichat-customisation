import {NgModule} from '@angular/core';
import {EntryRoutingModule} from './entry-routing.module';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {SharedModule} from 'shared/shared.module';
import {EntryComponent} from './pages/entry.component';
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
