import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoadingSpinnerComponent} from 'shared/components/loading-spinner/loading-spinner.component';
import {MatButtonModule, MatIconModule, MatListModule, MatNativeDateModule, MatSidenavModule, MatToolbarModule} from '@angular/material';
import {CustomMaterialModule} from 'shared/material.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CustomMaterialModule
  ],
  declarations: [
    LoadingSpinnerComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    CustomMaterialModule,

    LoadingSpinnerComponent
  ]
})
export class SharedModule {
}
