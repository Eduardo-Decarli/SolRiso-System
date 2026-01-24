import { Component, Input } from '@angular/core';
import { IReservation } from '../../interfaces/reservation/IReservation.interface';

@Component({
  selector: 'app-reservation-modal',
  imports: [],
  templateUrl: './reservation-modal.html',
  styleUrl: './reservation-modal.scss',
})
export class ReservationModal {
  @Input({ alias: 'reservation', required: true }) reservation!: IReservation;
}
