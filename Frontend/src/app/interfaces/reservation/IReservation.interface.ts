import { IParking } from "./IParking.interface";
import { IResponsible } from "./IResponsible.interface";

export interface IReservation {
  room: number;
  quantGuests: number;
  checkin: string;
  checkout: string;
  typeReservation: string;
  status: string;
  entryValue: number;
  totalValue: number;
  payment: string;
  paid: boolean;
  adminEmail: string;
  responsible: IResponsible;
  parking: IParking;
}
