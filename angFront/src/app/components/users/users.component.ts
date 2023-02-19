import { Component, OnInit } from '@angular/core';

import {Observable} from "rxjs";

import {Router} from "@angular/router";
import { BackendService } from 'src/app/service/backendService';
import { User } from 'src/app/model';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[]

  constructor(private backendService: BackendService, private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers()
  }

  getAllUsers(){
    this.backendService.getAllUsers()
      .subscribe(result => {
        this.users = result;
      })
  }

  navigateToUser(id: number){
    this.router.navigate([`/users/${id}`])
  }

  checkPermission(p: string){
    let token = localStorage.getItem("token");
    let permissions = JSON.parse(localStorage.getItem("permissions") || "[]")
    if(token != null){
      for(let permission of permissions) {
        if (permission.description === p) {
          return true;
        }
      }

    }
    return false;
  }

  deleteUser(id: number){
    this.backendService.deleteUser(String(id))
      .subscribe(result=> {
        alert(`User with id ${id} deleted.`)
        this.getAllUsers()
      });
  }

}
