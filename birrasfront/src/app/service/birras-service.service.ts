import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { config } from '../config';

@Injectable({
  providedIn: 'root'
})
export class BirrasServiceService {

  birrasURL = 'birras/';

  constructor(private httpClient: HttpClient) { }

  public calcularBirras(numberOfPeople:number, temperature:number): Observable<any> {
     
    return this.httpClient.post<any>(`${config.apiUrl}`+this.birrasURL + 'calcular',{numberOfPeople,temperature});
  }

}
