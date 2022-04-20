import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeadPageComponent } from './head-page/head-page.component';



@NgModule({
  declarations: [ HeadPageComponent ],
  imports: [
    CommonModule
  ],
  exports: [ HeadPageComponent ]
})
export class SharedModule { }
