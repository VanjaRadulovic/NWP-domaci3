import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

import {ActivatedRoute, Router} from "@angular/router";
import { Permission, User } from 'src/app/model';
import { BackendService } from 'src/app/service/backendService';


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  editUserForm: FormGroup;
  user: User 
  permissions: Permission[] 


  constructor(private backendService: BackendService, private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, ) {
    this.editUserForm = this.formBuilder.group({
      firstName: '',
      lastName: '',
      email: '',
      formPermissions: new FormArray([])

    });


  }

  ngOnInit(): void {
    this.getById()
  }

  updateUser(){

    let permissionArray = []

    let elements = (<HTMLInputElement[]><any>document.getElementsByClassName("form-check-input"));

    for (let i = 0; i < elements.length; i++) {
      if (elements[i].type == "checkbox") {
        if (elements[i].checked){
          let value = elements[i].value;
          if(value === 'can_create_users') {
            permissionArray.push({
              "id" : 1,
              "description" : value,
            });
          } else if(value === 'can_read_users'){
            permissionArray.push({
              "id" : 2,
              "description" : value,
            });
          } else if(value === 'can_update_users'){
            permissionArray.push({
              "id" : 3,
              "description" : value,
            });
          } else if(value === 'can_delete_users') {
            permissionArray.push({
              "id" : 4,
              "description" : value,
            });
          } else if(value === 'can_search_machines') {
            permissionArray.push({
              "id" : 5,
              "description" : value,
            });
          } else if(value === 'can_start_machines') {
            permissionArray.push({
              "id" : 6,
              "description" : value,
            });
          } else if(value === 'can_stop_machines') {
            permissionArray.push({
              "id" : 7,
              "description" : value,
            });
          } else if(value === 'can_restart_machines') {
            permissionArray.push({
              "id" : 8,
              "description" : value,
            });
          } else if(value === 'can_create_machines') {
            permissionArray.push({
              "id" : 9,
              "description" : value,
            });
          } else if(value === 'can_destroy_machines') {
            permissionArray.push({
              "id" : 10,
              "description" : value,
            });
          }
        }
      }
    }

    this.backendService.updateUser(
      <string>this.route.snapshot.paramMap.get('id'),
      this.editUserForm.get('firstName')?.value,
      this.editUserForm.get('lastName')?.value,
      this.editUserForm.get('email')?.value,
      permissionArray
    ).subscribe(result => {
      alert("Successfully updated");
      this.router.navigate(['/users']);
    });


  }

  getById(){
    this.backendService.getUserById(parseInt(<string>this.route.snapshot.paramMap.get('id')))
      .subscribe(result => {
        this.user = result;
        this.permissions = result.permissions
      })
  }

  check(name: string){
    return this.permissions.filter(permission => permission.description === name).length > 0;
  }



}
