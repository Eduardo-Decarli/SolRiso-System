import { Component, inject, numberAttribute } from '@angular/core';
import { Header } from "../../components/header/header";
import { SidebarMenu } from "../../components/sidebar-menu/sidebar-menu";
import { IReservation } from '../../interfaces/reservation/IReservation.interface';
import { TypeReservation } from '../../interfaces/enum/TypeReservation.enum';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Status } from '../../interfaces/enum/Status.enum copy';
import { Payment } from '../../interfaces/enum/Payment.enum';
import { IResponsible } from '../../interfaces/reservation/IResponsible.interface';
import { GuestService } from '../../services/guest.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-reservation',
  imports: [Header, SidebarMenu, CommonModule, FormsModule],
  templateUrl: './create-reservation.html',
  styleUrl: './create-reservation.scss',
})
export class CreateReservation {
  public guestsService = inject(GuestService);

  public showMenu = false;
  public guestsAutocompleteList: IResponsible[] | undefined;
  private autocompleteTimeout?: any;

  public typesReservation: TypeReservation[] = Object.values(TypeReservation);
  public statusReservation: Status[] = Object.values(Status);
  public payments: Payment[] = Object.values(Payment);

  public reservation: IReservation = {
    id: null,
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
    adminEmail: '',
    responsible: {
      name: '',
      phoneNumber: '',
      email: '',
      cpf: '',
      address: {
        cep: '',
        state: '',
        city: '',
        neighborhood: '',
        street: '',
        number: null,
      },
    },
    parking: {
      carType: null,
      checkin: null,
      checkout: null,
    },
  };

  public autocompleteGuests(name: string) {
    if (!name || name.length < 3) {
      this.guestsAutocompleteList = [];
      return;
    }

    clearTimeout(this.autocompleteTimeout);

    this.autocompleteTimeout = setTimeout(() => {
      this.guestsService.getAllGuestsByName(name).subscribe({
        next: (data) => {
          console.log(data);
          this.guestsAutocompleteList = data;
        },
        error: (err: HttpErrorResponse) => {
          console.log(err);

          if (err.status === 404) {
            this.guestsAutocompleteList = [];
          }
        },
      });
    }, 300);
  }

  public onGuestSelected() {
    if (!this.guestsAutocompleteList?.length) return;

    const selected = this.guestsAutocompleteList.find(
      (g) => g.name === this.reservation.responsible!.name
    );

    if (!selected) return;

    this.reservation.responsible = {
      ...selected,
    };
  }

  public toggleMenu() {
    this.showMenu = !this.showMenu;
  }

  public showRemaningValue() {
    if (this.reservation.totalValue !== null && this.reservation.entryValue !== null) {
      return this.reservation.totalValue - this.reservation.entryValue;
    }

    return 0;
  }
}
