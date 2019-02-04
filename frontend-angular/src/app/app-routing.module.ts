import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';



const routes: Routes = [
  {path: '', loadChildren: 'entry/entry.module#EntryModule'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      onSameUrlNavigation: 'reload'
    }),
    CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
