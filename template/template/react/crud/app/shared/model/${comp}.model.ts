import { ICustomer } from 'app/shared/model/customer.model';

export interface I${ucomp} {
  id?: number;
  address1?: string;
  address2?: string;
  city?: string;
  postcode?: string;
  country?: string;
  customer?: ICustomer;
}

export const defaultValue: Readonly<I${ucomp}> = {};
