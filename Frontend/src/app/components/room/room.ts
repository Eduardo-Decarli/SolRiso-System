import { Component, Input } from '@angular/core';
import { IRoom } from '../../interfaces/reservation/IRoom.interface';

@Component({
  selector: 'app-room',
  imports: [],
  templateUrl: './room.html',
  styleUrl: './room.scss',
})
export class Room {

  @Input({alias: 'room', required: true}) room!: IRoom;

  public isRoomBusy(): boolean {
    if(this.room.status === 'ocupado') {
      return true;
    } else {
      return false;
    }
  }
}
