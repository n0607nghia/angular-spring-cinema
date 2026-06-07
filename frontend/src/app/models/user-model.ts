import { BookingModel } from './booking-model';

export interface UserModel {
  id: number;
  username: string;
  email: string;
  phone: string;
  role: string;
  address: string;
  booking: BookingModel[];
}
