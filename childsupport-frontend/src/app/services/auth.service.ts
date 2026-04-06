import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiUrl}/auth/login`,
      {username, password}).pipe(
        tap((response: any) => {
          localStorage.setItem('token', response.token);
        })
    );
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post(`${environment.apiUrl}/auth/register`,
      { username, password});
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
