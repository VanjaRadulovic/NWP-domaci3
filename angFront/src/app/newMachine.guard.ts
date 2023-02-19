import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanDeactivate,
  Route,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewMachineGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let permissionsString = localStorage.getItem("permissions");
    let permissions = JSON.parse(permissionsString || "[]")
    for(let permission of permissions){
      if(permission.description === 'can_create_machines')
        return true;
      }
    alert("Access denied. Current user doesn't have sufficient privileges.")
    return false;
  }

}