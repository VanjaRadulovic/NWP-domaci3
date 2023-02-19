import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app/app.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from "@angular/common/http";
import { LoginComponent } from './components/login/login.component';
import { UsersComponent } from './components/users/users.component';
import { EditUserComponent } from './components/editUser/edit-user.component';
import { NewMachineComponent } from './components/machine/new-machine.component';
import { SearchComponent } from './components/search/search.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { ErrorsComponent } from './components/errors/errors.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UsersComponent,
    EditUserComponent,
    NewMachineComponent,
    SearchComponent,
    ScheduleComponent,
    ErrorsComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ReactiveFormsModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
