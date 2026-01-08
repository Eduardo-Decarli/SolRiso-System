import { Component, inject, OnInit } from '@angular/core';
import { DashboardRooms } from "../../components/dashboard-rooms/dashboard-rooms";
import { Header } from "../../components/header/header";
import { SidebarMenu } from "../../components/sidebar-menu/sidebar-menu";
import { ReservationService } from '../../services/reservation.service';
import { IReservation } from '../../interfaces/reservation/IReservation.interface';
import { environment } from '../../../environments/environment';
import { IRoom } from '../../interfaces/reservation/IRoom.interface';
import { ReservationList } from "../../components/reservation-list/reservation-list";

@Component({
  selector: 'app-admin-dashboard',
  imports: [DashboardRooms, Header, SidebarMenu, ReservationList],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.scss',
})
export class AdminDashboard implements OnInit {
  private readonly reservationService = inject(ReservationService);

  showMenu: 'block' | 'none' = 'block';

  public reservationsToday: IReservation[] | undefined;

  ngOnInit(): void {
    this.reservationService.getReservationsToday().subscribe({
      next: (data) => (this.reservationsToday = data),
      error: (err) => console.log(err),
    });
  }

  public onShowSideMenu() {
    if (this.showMenu === 'block') {
      this.showMenu = 'none';
    } else {
      this.showMenu = 'block';
    }
  }

  public showRooms(): IRoom[] {
    let rooms: IRoom[] = [];
    for (let index = 1; index <= environment.QUANT_ROOMS; index++) {
      if(!this.reservationsToday?.find(reservation => reservation.room == index)) {
        const room: IRoom = {
          numero: index,
          status: 'livre'
        }
        rooms.push(room);
      } else {
        const room: IRoom = {
          numero: index,
          status: 'ocupado'
        }
        rooms.push(room);
      }
    }
    return rooms;
  }
}
