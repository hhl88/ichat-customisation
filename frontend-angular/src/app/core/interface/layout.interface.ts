import {DisplayType} from 'core/enum/display-type.enum';
import {TextInputType} from 'core/enum/text-input-type.enum';
import {ButtonType} from 'core/enum/button-type.enum';
import {BackgroundType} from 'core/enum/background-type.enum';
import {FontType} from 'core/enum/font-type.enum';

export interface Layout {
  id?: string;
  name: string;
  displayType?: DisplayType;
  textInputType?: TextInputType;
  buttonType?: ButtonType;
  logo?: string;
  backgroundImg?: string;
  backgroundType?: BackgroundType;
  font?: FontType;
}
