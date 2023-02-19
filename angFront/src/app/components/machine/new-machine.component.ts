import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import { BackendService } from 'src/app/service/backendService';


@Component({
  selector: 'app-newMachine',
  templateUrl: './new-machine.component.html',
  styleUrls: ['./new-machine.component.css']
})
export class NewMachineComponent implements OnInit {

  newMachineForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private backendService: BackendService) {
    this.newMachineForm = this.formBuilder.group({
      machineName: ''
    });
  }

  addMachine(){
    this.backendService.addMachine(
      this.newMachineForm.get('machineName')?.value
    ).subscribe( {
      next: res => {
        alert("Machine successfully created.")
      },
      error: err => {
        alert(err);
      }
    });
  }

  ngOnInit(): void {
  }

}
