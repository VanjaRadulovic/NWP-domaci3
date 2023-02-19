import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import { Machine } from 'src/app/model';
import { BackendService } from 'src/app/service/backendService';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  machines: Machine[];
  searchMachinesForm: FormGroup;


  constructor(private formBuilder: FormBuilder, private backendService: BackendService) {
    this.searchMachinesForm = this.formBuilder.group({
      machineName: '',
      dateFrom: '',
      dateTo: '',
    });
  }

  ngOnInit(): void {
    this.getAllMachines();
  }

  getAllMachines(){
    this.backendService.searchMachines(null, null, null, null)
      .subscribe(result => {
        this.machines = result;
      });
  }

  searchMachines() {
    let statusArray = []

    let elements = (<HTMLInputElement[]><any>document.getElementsByClassName("form-check-input"));

    for (let i = 0; i < elements.length; i++) {
      if (elements[i].type == "checkbox") {
        if (elements[i].checked){
          let value = elements[i].value;
          if(value === 'STOPPED') {
            statusArray.push("STOPPED");
          } else {
            statusArray.push("RUNNING");
          }
        }
      }
    }


    this.backendService.searchMachines(
      this.searchMachinesForm.get('machineName')?.value,
      statusArray.length === 0 ? null : statusArray,
      this.searchMachinesForm.get('dateFrom')?.value,
      this.searchMachinesForm.get('dateTo')?.value
    ).subscribe( {
      next: value => {
        this.machines = value;
      },
      error: err => {
        alert(err);
      }
    });
  }

  clearForm() {
    this.searchMachinesForm.reset();
    let elements = (<HTMLInputElement[]><any>document.getElementsByClassName("form-check-input"));
    for (let i = 0; i < elements.length; i++) {
      elements[i].checked = false;
    }
  }

  startMachine(id: number) {
    this.backendService.startMachine(id)
      .subscribe({
        next: value => {
          console.log(value)
        },
        error: err => {
          alert(err?.body);
        }
      });
    setTimeout(function() {
      window.location.reload()
    }, 12001);
  }

  stopMachine(id: number) {
    this.backendService.stopMachine(id)
      .subscribe({
        next: value => {
          console.log(value)
        },
        error: err => {
          alert(err?.body);
        }
      });
    setTimeout(function() {
      window.location.reload()
    }, 12001);
  }

  restartMachine(id: number) {
    this.backendService.restartMachine(id)
      .subscribe({
        next: value => {
          console.log(value)
        },
        error: err => {
          alert(err?.body);
        }
      });
    setTimeout(function() {
      window.location.reload()
    }, 12001);
  }
}
