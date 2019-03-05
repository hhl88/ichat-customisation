import {Layout} from 'core/interfaces/layout.interface';
import {Frontend} from 'core/interfaces/frontend.interface';
import {ItemType} from 'core/enum/item-type.enum';

export interface Item extends Layout, Frontend {
  type: ItemType;
  _uid: any;
  editing: boolean;
}
