import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {SweetAlert2Module} from '@toverux/ngx-sweetalert2';
import {CustomMaterialModule} from 'shared/material/custom-material.module';
import {LoadingSpinnerComponent} from './components/loading-spinner/loading-spinner.component';
import {ImageCropperModule} from 'ngx-image-cropper';
import {AutoScrollDirective} from 'shared/directives/auto-scroll.directive';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';

@NgModule({
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    SweetAlert2Module,
    CustomMaterialModule,
    ImageCropperModule
  ],
  declarations: [
    LoadingSpinnerComponent,
    ImageUploadComponent,
    AutoScrollDirective,
  ],
  exports: [
    CommonModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    SweetAlert2Module,
    CustomMaterialModule,
    ImageCropperModule,

    LoadingSpinnerComponent,
    ImageUploadComponent,
    AutoScrollDirective
  ],

})
export class SharedModule {
}
