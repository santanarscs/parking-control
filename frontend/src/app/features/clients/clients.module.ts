import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsListComponent } from './clients-list/clients-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ClientsCreateComponent } from './clients-create/clients-create.component';

@NgModule({
  declarations: [
    ClientsListComponent,
    ClientsCreateComponent
  ],
  imports: [
    CommonModule,
    ClientsRoutingModule, SharedModule
  ]
})
export class ClientsModule { }
