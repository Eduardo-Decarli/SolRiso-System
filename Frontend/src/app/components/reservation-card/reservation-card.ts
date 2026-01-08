import { Component, Input } from '@angular/core';
import { IReservation } from '../../interfaces/reservation/IReservation.interface';

@Component({
  selector: 'app-reservation-card',
  imports: [],
  templateUrl: './reservation-card.html',
  styleUrl: './reservation-card.scss',
})
export class ReservationCard {

  @Input({alias: 'reservation', required: true}) reservation!: IReservation;
}
