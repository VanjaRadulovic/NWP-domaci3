import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../model';



@Injectable({
  providedIn: 'root'
})
export class BackendService{


  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem("token")}`
  })

  constructor(private httpClient: HttpClient) { 

  }

  login(email: string, password : string):Observable<any>{
    return this.httpClient.post<LoginRequest>('http://localhost:8080/auth/login',{email:email, password:password})
  }

  getAllUsers():Observable<any>{
    return this.httpClient.get('http://localhost:8080/api/users/get', {headers:this.headers})
  }

  deleteUser(id: string):Observable<any>{
    return this.httpClient.delete(`http://localhost:8080/api/users/del/${id}`, {headers:this.headers})
  }

  updateUser(id: string, firstName: string, lastName: string, email: string, permissions: any):Observable<any>{
    return this.httpClient.put<HttpResponse<any>>(`http://localhost:8080/api/users/update/${id}`, {email: email, firstName: firstName, lastName: lastName, permissions: permissions }, {headers:this.headers})
  }

  getUserById(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/users/get/${id}`, {headers: this.headers});
  }

  addMachine(name: string): Observable<any> {
    return this.httpClient.post<HttpResponse<any>>('http://localhost:8080/api/machines/new', {name: name}, {headers: this.headers});
  }

  searchMachines(machineName: string | null, status: string[] | null, dateFrom: string | null, dateTo: string | null): Observable<any>{
    return this.httpClient.put<HttpResponse<any>>('http://localhost:8080/api/machines/search', {machineName: machineName, status: status, dateFrom: dateFrom, dateTo: dateTo}, {headers: this.headers})
  }

  startMachine(id: number): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/api/machines/start/${id}`, {headers: this.headers})
  }

  stopMachine(id: number): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/api/machines/stop/${id}`, {headers: this.headers})
  }

  restartMachine(id: number): Observable<any> {
    return this.httpClient.put(`http://localhost:8080/api/machines/restart/${id}`, {headers: this.headers})
  }

  getMachineById(id: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/machines/get/${id}`, {headers: this.headers});
  }

  scheduleStart(id: number, date: string) {
    return this.httpClient.get(`http://localhost:8080/api/machines/scheduleStart/${id}?date=${date}`, {headers: this.headers})
  }

  scheduleStop(id: number, date: string) {
    return this.httpClient.get(`http://localhost:8080/api/machines/scheduleStop/${id}?date=${date}`, {headers: this.headers})
  }

  scheduleRestart(id: number, date: string) {
    return this.httpClient.get(`http://localhost:8080/api/machines/scheduleRestart/${id}?date=${date}`,{headers: this.headers})
  }

  getAllErrors(): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/errors/get`, {headers: this.headers})
  }

}