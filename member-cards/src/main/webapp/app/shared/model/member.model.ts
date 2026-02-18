export interface IMember {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
}

export const defaultValue: Readonly<IMember> = {};
