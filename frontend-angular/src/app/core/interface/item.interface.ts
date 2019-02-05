import {Layout} from 'core/interface/layout.interface';
import {Frontend} from 'core/interface/frontend.interface';
import {ItemType} from 'core/interface/item.type';

export interface Item extends Layout, Frontend {
  index: number;
  type: ItemType;
}
