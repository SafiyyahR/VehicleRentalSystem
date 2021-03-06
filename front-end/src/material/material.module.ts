import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import * as Material from '@angular/material';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    Material.MatFormFieldModule,
    Material.MatCardModule,
    Material.MatButtonModule,
    Material.MatGridListModule,
    Material.MatDialogModule,
    Material.MatFormFieldModule,
    Material.MatDatepickerModule,
    Material.MatNativeDateModule,
    Material.MatInputModule,
    Material.MatListModule,
    Material.MatTableModule,
    Material.MatIconModule,
    Material.MatSelectModule,
  ],
  exports: [
    Material.MatFormFieldModule,
    Material.MatCardModule,
    Material.MatButtonModule,
    Material.MatGridListModule,
    Material.MatDialogModule,
    Material.MatFormFieldModule,
    Material.MatDatepickerModule,
    Material.MatNativeDateModule,
    Material.MatInputModule,
    Material.MatListModule,
    Material.MatTableModule,
    Material.MatIconModule,
    Material.MatSelectModule,
  ]
})
export class MaterialModule { }
