import { IAddress } from "./IAddress.interface";

export interface IResponsible {
  name: string | null;
  phoneNumber: string | null;
  email: string | null;
  cpf: string | null;
  address: IAddress | null;
}
