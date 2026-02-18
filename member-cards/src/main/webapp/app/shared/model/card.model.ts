import dayjs from 'dayjs';
import { IMember } from 'app/shared/model/member.model';

export interface ICard {
  id?: number;
  cardNumber?: string;
  expirationDate?: dayjs.Dayjs;
  cvv?: string;
  member?: IMember | null;
}

export const defaultValue: Readonly<ICard> = {};
