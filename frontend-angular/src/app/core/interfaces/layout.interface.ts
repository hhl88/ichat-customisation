import {DisplayType} from 'core/enum/display-type.enum';
import {TextInputType} from 'core/enum/text-input-type.enum';
import {ButtonType} from 'core/enum/button-type.enum';
import {BackgroundType} from 'core/enum/background-type.enum';
import {Font, FontDefault} from 'core/interfaces/font.interface';

export interface Layout {
  id?: string;
  index?: number;
  name: string;
  displayType?: DisplayType;
  textInputType?: TextInputType;
  buttonType?: ButtonType;
  logo?: string;
  backgroundImg?: string;
  backgroundType?: BackgroundType;
  font?: Font;
}

export const LayoutDefault: Layout = {
  name: 'Layout',
  displayType: DisplayType.BESIDE_PAGE,
  textInputType: TextInputType.ABOVE_DIALOG,
  buttonType: ButtonType.BELOW_DIALOG,
  logo: '',
  backgroundImg: '',
  backgroundType: BackgroundType.FILLING,
  font: FontDefault,
};
