import { Component, Input } from '@angular/core';
import { Room } from "../room/room";
import { IRoom } from '../../interfaces/reservation/IRoom.interface';

@Component({
  selector: 'app-dashboard-rooms',
  imports: [Room],
  templateUrl: './dashboard-rooms.html',
  styleUrl: './dashboard-rooms.scss',
})
export class DashboardRooms {

  @Input({alias: 'RoomsToday', required: true}) roomsToday!: IRoom[];
}
