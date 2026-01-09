import { Component, numberAttribute } from '@angular/core';
import { Header } from "../../components/header/header";
import { SidebarMenu } from "../../components/sidebar-menu/sidebar-menu";
import { IReservation } from '../../interfaces/reservation/IReservation.interface';
import { TypeReservation } from '../../interfaces/enum/TypeReservation.enum';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Status } from '../../interfaces/enum/Status.enum copy';
import { Payment } from '../../interfaces/enum/Payment.enum';

@Component({
  selector: 'app-create-reservation',
  imports: [Header, SidebarMenu, CommonModule, FormsModule],
  templateUrl: './create-reservation.html',
  styleUrl: './create-reservation.scss',
})
export class CreateReservation {
  public showMenu: 'block' | 'none' = 'block';

  public reservation: IReservation = {
    room: null,
    quantGuests: null,
    checkin: null,
    checkout: null,
    typeReservation: null,
    status: null,
    entryValue: null,
    totalValue: null,
    payment: null,
    paid: null,
    adminEmail: null,
    responsible: {
      name: null,
      phoneNumber: null,
      email: null,
      cpf: null,
      address: {
        cep: null,
        state: null,
        city: null,
        neighborhood: null,
        street: null,
        number: null,
      },
    },
    parking: {
      carType: null,
      checkin: null,
      checkout: null,
    },
  };

  public typesReservation: TypeReservation[] = Object.values(TypeReservation);
  public statusReservation: Status[] = Object.values(Status);
  public payments: Payment[] = Object.values(Payment);

  public onShowSideMenu() {
    console.log(this.typesReservation);
    if (this.showMenu === 'block') {
      this.showMenu = 'none';
    } else {
      this.showMenu = 'block';
    }
  }

  public showRemaningValue() {
    if(this.reservation.totalValue !== null && this.reservation.entryValue !== null ) {
      return this.reservation.totalValue - this.reservation.entryValue;
    }

    return 0;
  }
}
