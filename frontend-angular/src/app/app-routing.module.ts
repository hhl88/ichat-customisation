import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';



const routes: Routes = [
  {path: '', loadChildren: 'entry/entry.module#EntryModule'},
  {path: '*', redirectTo: ''},
  {path: 'ichat', loadChildren: 'ichat/ichat.module#IChatModule'},

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
