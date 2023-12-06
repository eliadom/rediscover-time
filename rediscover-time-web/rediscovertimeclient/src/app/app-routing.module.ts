import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainMenuComponent} from "./main-menu/main-menu.component";
import {CuaMenuComponent} from "./cua-menu/cua-menu.component";

const routes: Routes = [
  { path: 'main', component: MainMenuComponent },
  { path: 'cua', component: CuaMenuComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
