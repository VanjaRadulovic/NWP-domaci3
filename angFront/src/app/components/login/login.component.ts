import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {NavigationEnd, Router} from "@angular/router";
import { BackendService } from 'src/app/service/backendService';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginForm: FormGroup;

  constructor(private backendService: BackendService, private formBuilder: FormBuilder, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: '',
      password: ''
    });
  }

  ngOnInit(): void {
  }

  login() {
    this.backendService.login(this.loginForm.get('email')?.value, this.loginForm.get('password')?.value
    ).subscribe({
      next: res => {
        console.log(res.permissionm)
        localStorage.setItem('token', res.jwt)
        localStorage.setItem('permissions', JSON.stringify(res.permissionm))
        this.router.navigate(["/users"])

      },
      error: err => {
           alert(`Error ${err.status}. Wrong login.`)
      }
    }

    )
  }
}
