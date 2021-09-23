interface Customer {
  id: number;
  age: number;
  canton: String;
  sex: String;
}

export interface CalculateResponse{
  customer: Customer;
  risk: string;
}
