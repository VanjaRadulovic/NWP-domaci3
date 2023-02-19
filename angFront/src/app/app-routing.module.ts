import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditUserComponent } from './components/editUser/edit-user.component';
import { ErrorsComponent } from './components/errors/errors.component';
import { LoginComponent } from './components/login/login.component';
import { NewMachineComponent } from './components/machine/new-machine.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { SearchComponent } from './components/search/search.component';
import { UsersComponent } from './components/users/users.component';
import { EditGuard } from './edit.guard';
import { NewMachineGuard } from './newMachine.guard';
import { ReadGuard } from './read.guard';
import { SearchGuard } from './search.guard';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent,
  },
  {
    path: "users",
    component: UsersComponent,
    canActivate: [ReadGuard],
    },
  
  {
    path: "users/:id",
    component: EditUserComponent,
    canActivate: [EditGuard]
  },
  {
    path: "newMachine",
    component: NewMachineComponent,
    canActivate: [NewMachineGuard],
  },
  {
    path: "search",
    component: SearchComponent,
    canActivate: [SearchGuard],
  },
  {
    path: "schedule/:id",
    component: ScheduleComponent,
  },
  {
    path: "errors",
    component: ErrorsComponent,
  }




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
