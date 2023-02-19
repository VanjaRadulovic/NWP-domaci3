import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'nwp3-front';

  constructor(private router: Router) {
  }

  logout(){
    localStorage.clear()
    this.router.navigate(['/login']);
  }

  loggedIn(){
    return localStorage.getItem('token') != null;
  }

  checkPermission(p: string){
    // let token = localStorage.getItem("token");
    // let permissions = JSON.parse(localStorage.getItem("permissions") || "[]")
    // if(token != null){
    //   for(let permission of permissions) {
    //     if (permission.description === p) {
    //       return true;
    //     }
    //   }
    // }
    return false;
  }
}
