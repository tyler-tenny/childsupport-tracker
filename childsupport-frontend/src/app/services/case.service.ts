import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Case {
  id?: number;
  caseNumber: string;
  status: string;
  caseType: string;
  openedDate: string;
}

export interface Payment {
  id?: number;
  amount: number;
  paymentDate: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class CaseService {

  constructor(private http: HttpClient) { }

  getAllCases(): Observable<Case[]> {
    return this.http.get<Case[]>(`${environment.apiUrl}/cases`);
  }

  getCaseById(id: number): Observable<Case> {
    return this.http.get<Case>(`${environment.apiUrl}/cases/${id}`);
  }

  createCase(c: Case): Observable<Case> {
    return this.http.post<Case>(`${environment.apiUrl}/case`, c);
  }

  updateCase(id: number, c: Case): Observable<Case> {
    return this.http.put<Case>(`${environment.apiUrl}/cases/${id}`, c);
  }

  getPayments(caseId: number): Observable<Payment[]> {
    return this.http.get<Payment[]>(`${environment.apiUrl}/cases/${caseId}/payments`);
  }

  createPayment(caseId: number, payment: Payment): Observable<Payment> {
    return this.http.post<Payment>(`${environment.apiUrl}/cases/${caseId}/payments`, payment);
  }
}
