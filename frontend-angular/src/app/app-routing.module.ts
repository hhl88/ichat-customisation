import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';


const routes: Routes = [
  {path: '', loadChildren: 'home/home.module#HomeModule'},
  {path: 'ichat', loadChildren: 'ichat/i-chat.module#IChatModule'},

  {path: '*', redirectTo: ''},
  {path: '**', redirectTo: ''}

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
