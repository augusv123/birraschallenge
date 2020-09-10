import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NewUser } from '../models/new-user';
import { Observable } from 'rxjs';
import { LoginUsuario } from '../models/login-usuario';
import { JwtDTO } from '../models/jwt-dto';
import { config } from '../config';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authURL = 'auth/';

  constructor(private httpClient: HttpClient) { }

  public nuevo(newUser: NewUser): Observable<any> {
    
    return this.httpClient.post<any>(`${config.apiUrl}`+this.authURL + 'createUser', newUser);
  }

  public login(loginUsuario: LoginUsuario): Observable<JwtDTO> {
    console.log(`${config.apiUrl}asd`)

    return this.httpClient.post<JwtDTO>(`${config.apiUrl}`+this.authURL + 'loggin', loginUsuario);
  }
}
