import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EntryComponent} from './pages/entry.component';
import {LOGIN_PAGE, SIGN_UP_PAGE} from 'core/constants/routing.constants';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';

const routes: Routes = [
  {
    path: '',
    component: EntryComponent,
    children: [
      {
        path: LOGIN_PAGE, component: SignInComponent, data: {isPublic: true}
      },
      {
        path: SIGN_UP_PAGE, component: SignUpComponent, data: {isPublic: true}
      },
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EntryRoutingModule { }
