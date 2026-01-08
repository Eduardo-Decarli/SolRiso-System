import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { IReservation } from '../interfaces/reservation/IReservation.interface';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private readonly http = inject(HttpClient);

  public getReservationsToday(): Observable<IReservation[]> {
    return this.http.get<IReservation[]>(environment.RESERVATION_URL, {
      responseType: 'json',
      headers: {
        Authorization: `Bearer ${this.getJwtToken()}`,
        'Content-Type': 'application/json',
      },
    });
  }

  private getJwtToken() {
    return localStorage.getItem('jwt');
  }
}
