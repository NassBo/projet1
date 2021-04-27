import dayjs from 'dayjs';
import { ITag } from 'app/shared/model/tag.model';
import { IApplicationUser } from 'app/shared/model/application-user.model';

export interface IArticle {
  id?: number;
  userId?: number;
  title?: string;
  articleBody?: string;
  dateArticle?: string;
  tags?: ITag[];
  applicationUser?: IApplicationUser | null;
}

export const defaultValue: Readonly<IArticle> = {};
