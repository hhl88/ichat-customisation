import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IChatComponent} from 'ichat/pages/ichat.component';

const routes: Routes = [
  {
    path: '',
    component: IChatComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IChatRoutingModule { }
