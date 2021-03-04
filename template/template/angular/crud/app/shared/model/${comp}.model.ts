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

export class ${ucomp} implements I${ucomp} {
  constructor(
    public id?: number,
    public address1?: string,
    public address2?: string,
    public city?: string,
    public postcode?: string,
    public country?: string,
    public customer?: ICustomer
  ) {}
}
