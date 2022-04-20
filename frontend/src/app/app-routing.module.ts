import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    redirectTo: "dashboard"
  },
  {
    path: "dashboard",
    loadChildren: () => import("./features/dashboard/dashboard.module").then(m => m.DashboardModule)
  },
  {
    path: "clients",
    loadChildren: () => import("./features/clients/clients.module").then(m => m.ClientsModule)
  },
  {
    path: "**",
    redirectTo: "dashboard"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
