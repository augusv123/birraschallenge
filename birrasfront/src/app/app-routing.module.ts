import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
import { TemperatureComponent } from './temperature/temperature.component';
import { CalculoBirrasComponent } from './calculo-birras/calculo-birras.component';
import { GestionMeetupsComponent } from './gestion-meetups/gestion-meetups.component';



const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'temperature', component: TemperatureComponent },
  { path: 'calculoBirras', component: CalculoBirrasComponent ,canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'meetups', component: GestionMeetupsComponent ,canActivate: [guard], data: { expectedRol: ['admin','user'] } },
  
  { path: 'registro', component: RegistroComponent },

  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
