import { Component, OnInit } from '@angular/core';
import { ErrorMessage } from 'src/app/model';
import { BackendService } from 'src/app/service/backendService';



@Component({
  selector: 'app-errors',
  templateUrl: './errors.component.html',
  styleUrls: ['./errors.component.css']
})
export class ErrorsComponent implements OnInit {

  errors: ErrorMessage[];

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
    this.getAllErrors();
  }

  getAllErrors(){
    this.backendService.getAllErrors()
      .subscribe(result => {
        this.errors = result;
        console.log(result);
      })
  }

}
