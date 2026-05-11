import { Component, inject, OnInit } from '@angular/core';
import { DashboardRooms } from "../../components/dashboard-rooms/dashboard-rooms";
import { Header } from "../../components/header/header";
import { SidebarMenu } from "../../components/sidebar-menu/sidebar-menu";
import { ReservationService } from '../../services/reservation.service';
import { IReservation } from '../../interfaces/reservation/IReservation.interface';
import { environment } from '../../../environments/environment';
import { IRoom } from '../../interfaces/reservation/IRoom.interface';
import { ReservationList } from "../../components/reservation-list/reservation-list";
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ReservationModal } from "../../components/reservation-modal/reservation-modal";

@Component({
  selector: 'app-admin-dashboard',
  imports: [DashboardRooms, Header, SidebarMenu, ReservationList, ReservationModal],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.scss',
})
export class AdminDashboard implements OnInit {
  private readonly reservationService = inject(ReservationService);
  private router: Router = new Router();
  public reservationsToday: IReservation[] | undefined;
  public showMenu = true;
  public selectedReservation: IReservation | undefined;
  public showModal: 'flex' | 'none' = 'none';

  ngOnInit(): void {
    if (!localStorage.getItem('jwt')) {
      this.router.navigate(['login']);
      // Fazer rota de validação de token
    }
    this.reservationService.getReservationsToday().subscribe({
      next: (data) => (this.reservationsToday = data),
      error: (err: HttpErrorResponse) => {
        switch (err.status) {
          case 0:
            alert('Erro ao realizar a comunicação com o servidor');
            break;

          case 403:
            this.router.navigate(['login']);
            break;

          case 404:
            this.reservationsToday = undefined;

            break;

          default:
            console.log(err.error);
            break;
        }
      },
    });
  }

  public toggleMenu() {
    this.showMenu = !this.showMenu;
  }

  public showRooms(): IRoom[] {
    let rooms: IRoom[] = [];
    for (let index = 1; index <= environment.QUANT_ROOMS; index++) {
      if (!this.reservationsToday?.find((reservation) => reservation.room == index)) {
        const room: IRoom = {
          numero: index,
          status: 'livre',
        };
        rooms.push(room);
      } else {
        const room: IRoom = {
          numero: index,
          status: 'ocupado',
        };
        rooms.push(room);
      }
    }
    return rooms;
  }

  public isReservationsToday(reservations: IReservation[] | undefined): boolean {
    if (reservations === undefined || reservations.length === 0) {
      return false;
    }
    return true;
  }

  public selectReservation(reservation: IReservation) {
    this.selectedReservation = reservation;
    this.showModal = 'flex';
    console.log(reservation)
  }
}
