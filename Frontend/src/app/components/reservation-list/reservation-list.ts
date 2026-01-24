import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ReservationCard } from "../reservation-card/reservation-card";
import { IReservation } from '../../interfaces/reservation/IReservation.interface';

@Component({
  selector: 'app-reservation-list',
  imports: [ReservationCard],
  templateUrl: './reservation-list.html',
  styleUrl: './reservation-list.scss',
})
export class ReservationList {

  @Input({alias: 'reservations', required: true}) reservations: IReservation[] | undefined;

  @Output() selectReservation = new EventEmitter<IReservation>();

  onSelectReservation(reservation: IReservation) {
    alert('passou')
    this.selectReservation.emit(reservation);
  }
}
