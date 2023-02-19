import { Component, OnInit } from '@angular/core';
import {Machine} from "../../model";

import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DatePipe} from "@angular/common";
import { BackendService } from 'src/app/service/backendService';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  machine: Machine
  scheduleStartMachineForm: FormGroup
  scheduleStopMachineForm: FormGroup
  scheduleRestartMachineForm: FormGroup
  pipe = new DatePipe('en-US');

  constructor(private backendService: BackendService, private router: Router, private route: ActivatedRoute, private formBuilder: FormBuilder) {
    this.scheduleStartMachineForm = this.formBuilder.group({
      date: Date
    });

    this.scheduleStopMachineForm = this.formBuilder.group({
      date: Date
    });

    this.scheduleRestartMachineForm = this.formBuilder.group({
      date: Date
    });
  }

  ngOnInit(): void {
    this.getById()
  }

  getById(){
    this.backendService.getMachineById(parseInt(<string>this.route.snapshot.paramMap.get('id')))
      .subscribe(result => {
        this.machine = result;
        console.log(result)
      })
  }

  scheduleStartMachine() {

    let date = this.scheduleStartMachineForm.get('date')?.value;
    let dateString = this.pipe.transform(date, "dd.MM.yyyy HH:mm");
    if(dateString === null){
      alert("Select a date")
      return
    }
    this.backendService.scheduleStart(this.machine.id, dateString).subscribe( result => {
 
    })

  }

  scheduleStopMachine() {
    console.log("Stop")
    let date = this.scheduleStopMachineForm.get('date')?.value;
    let dateString = this.pipe.transform(date, "dd.MM.yyyy HH:mm");
    if(dateString === null){
      alert("Select a date")
      return
    }
    this.backendService.scheduleStop(this.machine.id, dateString).subscribe( result => {
    
    })
  }

  scheduleRestartMachine() {
    let date = this.scheduleRestartMachineForm.get('date')?.value;
    let dateString = this.pipe.transform(date, "dd.MM.yyyy HH:mm");
    if(dateString === null){
      alert("Select a date")
      return
    }
    this.backendService.scheduleRestart(this.machine.id, dateString).subscribe( result => {
 
    })
  }
}
