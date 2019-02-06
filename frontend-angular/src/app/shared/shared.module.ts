import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {SweetAlert2Module} from '@toverux/ngx-sweetalert2';
import {CustomMaterialModule} from 'shared/material/custom-material.module';
import {LoadingSpinnerComponent} from './components/loading-spinner/loading-spinner.component';

@NgModule({
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    SweetAlert2Module,
    CustomMaterialModule
  ],
  declarations: [
    LoadingSpinnerComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    NgbModule,
    ReactiveFormsModule,
    SweetAlert2Module,
    CustomMaterialModule,

    LoadingSpinnerComponent,
  ],

})
export class SharedModule {
}
