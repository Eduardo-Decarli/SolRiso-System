import { IAddress } from "./IAddress.interface";

export interface IResponsible {
  name: string;
  phoneNumber: string;
  email: string;
  cpf: string;
  address: IAddress;
}
