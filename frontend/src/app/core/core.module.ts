import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MainLayoutComponent } from './main-layout/main-layout.component';


@NgModule({
  declarations: [
    MainLayoutComponent,
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [ MainLayoutComponent ]
})
export class CoreModule { }
