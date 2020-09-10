import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NewUser } from '../models/new-user';
import { Observable } from 'rxjs';
import { config } from '../config';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  weatherURL = 'weather/';

  constructor(private httpClient: HttpClient) { }

  public today(): Observable<any> {
    
    return this.httpClient.get<any>(`${config.apiUrl}`+this.weatherURL + 'today');
  }
  public getDatesWeather(date:any){
    
    return this.httpClient.post<any>(`${config.apiUrl}`+this.weatherURL + 'getDatesWeather',{date});

  }


}
