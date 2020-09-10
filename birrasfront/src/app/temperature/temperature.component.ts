import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../service/weather.service';
import { Weather } from '../models/weather';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-temperature',
  templateUrl: './temperature.component.html',
  styleUrls: ['./temperature.component.css']
})
export class TemperatureComponent implements OnInit {
  todaysWeather:Weather
  constructor(private weatherService: WeatherService) { }
  maxDate:any
  minDate:Date
  ngOnInit() {
    var maxNewDate = new Date();
    this.minDate = new Date()
    this.maxDate = maxNewDate.setDate(maxNewDate.getDate() + 10)
    // var date =  new Date(maxNewDate.getDate()+"-"+maxNewDate.getMonth()+"-"+maxNewDate.getFullYear())
     
    // console.log(this.minDate )
    
    this.getTodaysWeather()
  }
   getTodaysWeather(){
      this.weatherService.today().subscribe(res => {this.todaysWeather = new Weather().deserialize(res)

         },
         error => {
           
           console.log(error)
         }
        )

  }


}
