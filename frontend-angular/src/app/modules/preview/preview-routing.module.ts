import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PreviewComponent} from 'preview/pages/preview.component';

const routes: Routes = [
  {
    path: '',
    component: PreviewComponent,
    data: {isPublic: true}
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreviewRoutingModule {
}
