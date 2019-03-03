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
import { RadioSelectionComponent } from './components/radio-selection/radio-selection.component';
import { EditableSelectionComponent } from './components/editable-selection/editable-selection.component';
import { ToggleOptionComponent } from './components/toggle-option/toggle-option.component';

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
    RadioSelectionComponent,
    EditableSelectionComponent,
    ToggleOptionComponent,
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
    RadioSelectionComponent,
    EditableSelectionComponent,
    ToggleOptionComponent,
    AutoScrollDirective
  ],

})
export class SharedModule {
}
