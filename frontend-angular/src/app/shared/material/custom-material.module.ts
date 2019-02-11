import {NgModule} from '@angular/core';
import {
  MatButtonModule, MatButtonToggleModule,
  MatCheckboxModule,
  MatDialogModule, MatExpansionModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule, MatListModule,
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
    MatRadioModule,
    MatListModule,
    MatButtonToggleModule
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
    MatRadioModule,
    MatListModule,
    MatButtonToggleModule
  ]

})
export class CustomMaterialModule {
}
