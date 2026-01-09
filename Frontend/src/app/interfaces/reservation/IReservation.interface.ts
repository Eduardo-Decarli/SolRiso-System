import { Payment } from "../enum/Payment.enum";
import { Status } from "../enum/Status.enum copy";
import { TypeReservation } from "../enum/TypeReservation.enum";
import { IParking } from "./IParking.interface";
import { IResponsible } from "./IResponsible.interface";

export interface IReservation {
  room: number | null;
  quantGuests: number | null;
  checkin: string | null;
  checkout: string | null;
  typeReservation: TypeReservation | null;
  status: Status | null;
  entryValue: number | null;
  totalValue: number | null;
  payment: Payment | null;
  paid: boolean | null;
  adminEmail: string | null;
  responsible: IResponsible | null;
  parking: IParking | null;
}
