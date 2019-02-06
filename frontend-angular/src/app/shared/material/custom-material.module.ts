import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatDialogModule, MatExpansionModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatMenuModule, MatRadioModule,
  MatStepperModule, MatToolbarModule,
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
    MatExpansionModule,
    MatToolbarModule,
    MatRadioModule
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
    MatInputModule,
    MatExpansionModule,
    MatToolbarModule,
    MatRadioModule
  ]

})
export class CustomMaterialModule {
}
