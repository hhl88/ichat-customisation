import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatDialogModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule,
  MatStepperModule,
  MatTooltipModule
} from '@angular/material';

@NgModule({
  imports: [
    MatTooltipModule,
    MatStepperModule,
    MatIconModule,
    MatDialogModule,
    MatCheckboxModule,
    MatButtonModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    MatTooltipModule,
    MatStepperModule,
    MatIconModule,
    MatDialogModule,
    MatCheckboxModule,
    MatButtonModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule
  ]

})
export class CustomMaterialModule {
}
