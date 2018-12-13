import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import {SharedModule} from 'shared/shared.module';
import {TranslateModule} from '@ngx-translate/core';
import {SignInComponent} from 'home/components/sign-in/sign-in.component';
import {HomeComponent} from 'home/pages/home.component';
import {SignUpComponent} from 'home/components/sign-up/sign-up.component';
import {UserService} from 'core/services/user.service';

@NgModule({
  imports: [
    SharedModule,
    TranslateModule.forChild(),
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent,
    SignUpComponent,
    SignInComponent,
  ],
  providers: [
    UserService,
  ]
})
export class HomeModule { }
