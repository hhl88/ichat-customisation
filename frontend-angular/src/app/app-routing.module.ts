import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {ICHAT_PAGE, PASSWORD_PAGE, PREVIEW_PAGE} from 'core/constants/routing.constants';


const routes: Routes = [
  {path: '', loadChildren: 'entry/entry.module#EntryModule', data: {isPublic: true}},
  {path: '*', redirectTo: ''},
  {path: ICHAT_PAGE, loadChildren: 'ichat/ichat.module#IChatModule'},
  {path: PASSWORD_PAGE, loadChildren: 'ichat/ichat.module#IChatModule'},
  {path: PREVIEW_PAGE, loadChildren: 'preview/preview.module#PreviewModule', data: {isPublic: true}},

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
