import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IResponsible } from '../interfaces/reservation/IResponsible.interface';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GuestService {
  private readonly http = inject(HttpClient);

  public getAllGuestsByName(name: string): Observable<IResponsible[]> {
    return this.http.get<IResponsible[]>(`${environment.GUEST_URL}/guestsByName`, {
      params: {
        name: name,
      },
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
