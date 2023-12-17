import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {CommonModule, NgFor, NgForOf, NgIf} from "@angular/common";
import {RouterModule} from "@angular/router";
import {HttpClientModule} from "@angular/common/http";
import {SmallViewComponent} from "./main-menu/small-view/small-view.component";
import {CuaMenuComponent} from "./cua-menu/cua-menu.component";
import {ConfirmQueueDialogComponent} from "./main-menu/small-view/confirm-queue-dialog/confirm-queue-dialog.component";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {SmallViewClientComponent} from "./cua-menu/small-view-client/small-view-client.component";

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    SmallViewComponent,
    CuaMenuComponent,
    ConfirmQueueDialogComponent,
    SmallViewClientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    CommonModule,
    RouterModule,
    HttpClientModule,
    NgForOf,
    NgFor,
    NgIf,
    MatInputModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
