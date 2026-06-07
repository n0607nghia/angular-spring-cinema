import { ScreeningModel } from './screening-model';
import { UserModel } from './user-model';

export interface BookingModel {
  id: number;
  seats: number;
  date: string;
  user: UserModel;
  screening: ScreeningModel;
}
