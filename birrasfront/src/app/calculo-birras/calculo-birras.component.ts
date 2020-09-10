import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../service/weather.service';
import { Weather } from '../models/weather';
import { BirrasServiceService } from '../service/birras-service.service';
import { Birras } from '../models/Birras';

@Component({
  selector: 'app-calculo-birras',
  templateUrl: './calculo-birras.component.html',
  styleUrls: ['./calculo-birras.component.css']
})
export class CalculoBirrasComponent implements OnInit {
  todaysWeather
  birras
  numberOfpeople= 0
  constructor(private weatherService: WeatherService,private birrasService: BirrasServiceService) { }

  ngOnInit(): void {
    this.getTodaysWeather()
  }

  //obtiene toda la informacion del clima de hoy en buenos aires
  getTodaysWeather(){
    this.weatherService.today().subscribe(res => {this.todaysWeather = new Weather().deserialize(res)

       },
       error => {
         
         console.log(error)
       }
      )

}
  calcularBirras(){
  this.getTodaysWeather()
  var temp = this.todaysWeather?.main.temp
  this.birrasService.calcularBirras(this.numberOfpeople,temp).subscribe(res => {this.birras = new Birras().deserialize(res)
     console.log(this.birras)
  },
  error => {
    
    console.log(error)
  }
  )

  }

}
