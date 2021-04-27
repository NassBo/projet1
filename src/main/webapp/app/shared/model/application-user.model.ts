import { IUser } from 'app/shared/model/user.model';
import { IArticle } from 'app/shared/model/article.model';

export interface IApplicationUser {
  id?: number;
  profilBanner?: string | null;
  profilBio?: string | null;
  internalUser?: IUser;
  articles?: IArticle[] | null;
  friends?: IApplicationUser[] | null;
  followers?: IApplicationUser[] | null;
}

export const defaultValue: Readonly<IApplicationUser> = {};
