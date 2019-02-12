import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ICHAT_PAGE, PASSWORD_PAGE} from 'core/constants/routing.constants';



const routes: Routes = [
  {path: '', loadChildren: 'entry/entry.module#EntryModule'},
  {path: '*', redirectTo: ''},
  {path: ICHAT_PAGE, loadChildren: 'ichat/ichat.module#IChatModule'},
  {path: PASSWORD_PAGE, loadChildren: 'ichat/ichat.module#IChatModule'},

];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      //onSameUrlNavigation: 'reload'
    }),
    CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
