import {Component, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {DisplayType} from 'core/enum/display-type.enum';
import {TextInputType} from 'core/enum/text-input-type.enum';
import {ButtonType} from 'core/enum/button-type.enum';
import {FontType} from 'core/enum/font-type.enum';
import {BackgroundType} from 'core/enum/background-type.enum';
import {IChatService} from 'ichat/services/ichat.service';
import {environment} from 'environments/environment';
import {BubbleStyleDefault} from 'core/interfaces/bubble-style.interface';
import {SwalComponent} from '@toverux/ngx-sweetalert2';
import {Store} from '@ngrx/store';
import * as fromRoot from 'store/reducers';
import {CurrentItemSelectedAction, LayoutListAddAction, LayoutListUpdateItemAction} from 'store/actions/ichat';

@Component({
  selector: 'app-layout-content',
  templateUrl: './layout-content.component.html',
  styleUrls: ['./layout-content.component.scss']
})
export class LayoutContentComponent implements OnInit, OnChanges {
  @Input() layout: Layout;
  selectedLayout: Layout = null;

  supportedLayouts: any[];
  supportedTextInputs: any[];
  supportedButtonType: any[];
  supportedBackgroundType: any[];
  sampleFontFamily: string[];
  sampleFontSizes: string[];
  supportedFontStyles: any[];

  logoImg: string;
  backgroundImg: string;
  isLoading = true;


  @ViewChild('layoutCreatedSwal') private layoutCreatedSwal: SwalComponent;
  @ViewChild('layoutUpdatedSwal') private layoutUpdatedSwal: SwalComponent;
  @ViewChild('layoutFailedSwal') private layoutFailedSwal: SwalComponent;

  constructor(private iChatService: IChatService, private store: Store<fromRoot.State>) {
    this._createSupportedChatLayout();
    this._createSupportedTextInputs();
    this._createSupportedButtonType();
    this._createSupportedBackgroundType();
    this._createSampleFontFamily();
    this._createSampleFontSizes();
    this._createSupportedFontStyles();
  }

  ngOnInit() {
    this.isLoading = true;
    this._setLayout();
  }

  ngOnChanges() {
    if (this.selectedLayout === null || this.layout._uid !== this.selectedLayout._uid) {
      this._setLayout();
    }
  }

  private _setLayout() {
    this.selectedLayout = JSON.parse(JSON.stringify(this.layout));
    if (!this.selectedLayout.bubbleStyle) {
      this.selectedLayout.bubbleStyle = BubbleStyleDefault;
    }
    this._setImages(this.selectedLayout);

  }

  private _setImages(data) {

    if (data.id !== '') {
      if (data.logo && data.logo !== '') {
        this.logoImg = environment.iChatLayoutApi + '/' + this.selectedLayout.id + '/logoImg';
      } else {
        this.logoImg = '';
      }
      if (data.backgroundImg && data.backgroundImg !== '') {
        this.backgroundImg = environment.iChatLayoutApi + '/' + this.selectedLayout.id + '/backgroundImg';
      } else {
        this.backgroundImg = '';
      }
    }
  }


  onLogoChanged(rawValue) {
    this.logoImg = rawValue.src;
    this.selectedLayout.logo = rawValue.image;

  }

  onBackgroundChanged(rawValue) {
    this.backgroundImg = rawValue.src;

    this.selectedLayout.backgroundImg = rawValue.image;

  }

  cancelCurrent() {

  }

  onDisplayTypeChanged(rawValue) {
    this.selectedLayout.displayType = rawValue.newSelection;
  }

  onTextInputTypeChanged(rawValue) {
    this.selectedLayout.textInputType = rawValue.newSelection;
  }

  onButtonTypeChanged(rawValue) {
    this.selectedLayout.buttonType = rawValue.newSelection;
  }

  onBackgroundTypeChanged(rawValue) {
    this.selectedLayout.backgroundType = rawValue.newSelection;
  }

  onFontFamilyChanged(rawValue) {
    this.selectedLayout.font.fontFamily = rawValue.newSelection;
  }

  onFontSizeChanged(rawValue) {
    this.selectedLayout.font.fontSize = rawValue.newSelection;
  }

  onFontStyleChanged(rawValue) {
    this.selectedLayout.font.fontStyles = rawValue.selectedItems;

  }

  onMyBubbleStyleChanged(rawValue) {
    this.selectedLayout.bubbleStyle.myBubble = rawValue.data;
  }

  onTheirBubbleStyleChanged(rawValue) {
    this.selectedLayout.bubbleStyle.theirBubble = rawValue.data;
  }

  submitCurrent() {
    if (!(this.selectedLayout.logo instanceof Blob)) {
      this.selectedLayout.logo = '';
    }

    if (!(this.selectedLayout.backgroundImg instanceof Blob)) {
      this.selectedLayout.backgroundImg = '';
    }
    if (this.selectedLayout.id) {
      this.iChatService.updateLayout(this.selectedLayout.id, this.selectedLayout).subscribe(res => {
        const newLayout = {...this.selectedLayout, ...JSON.parse(res.body)};
        this.store.dispatch(new LayoutListAddAction(newLayout));
        this.store.dispatch(new CurrentItemSelectedAction(newLayout));

        this.layoutUpdatedSwal.show().then(() => {
        });

      }, error1 => {
        this.layoutFailedSwal.show().then(() => {
        });
        console.log('error update', error1);
      });
    } else {
      this.iChatService.createLayout(this.selectedLayout).subscribe(res => {
        const newLayout = {...this.selectedLayout, ...JSON.parse(res.body)};
        this.store.dispatch(new LayoutListUpdateItemAction(newLayout));
        this.store.dispatch(new CurrentItemSelectedAction(newLayout));

        this.layoutCreatedSwal.show().then(() => {
        });
      }, error1 => {
        this.layoutFailedSwal.show().then(() => {
        });
        console.log('error create layout', error1);
      });
    }
  }

  setAsDefault() {
    if (this.selectedLayout.id) {
      this.iChatService.setAsDefaultLayout(this.selectedLayout.id).subscribe(res => {
        // console.log('res', res);
      });
    }
  }

  private _createSupportedChatLayout() {

    this.supportedLayouts = [
      {
        id: DisplayType.BESIDE_PAGE,
        title: 'Layer auf Seite',
      },
      {
        id: DisplayType.INTEGRATED,
        title: 'Integriert',
      },
      {
        id: DisplayType.POPUP,
        title: 'Popup',
      }
    ];

  }

  private _createSupportedTextInputs() {
    this.supportedTextInputs = [
      {
        id: TextInputType.ABOVE_DIALOG,
        title: 'über Dialog',
      },
      {
        id: TextInputType.BELOW_DIALOG,
        title: 'unter Dialog',
      },
    ];
  }

  private _createSupportedButtonType() {
    this.supportedButtonType = [
      {
        id: ButtonType.BESIDE_TEXT_AREA,
        title: 'Neben Eingabelfeld',
      },
      {
        id: ButtonType.BELOW_DIALOG,
        title: 'unter Dialog',
      },
    ];
  }

  private _createSupportedBackgroundType() {
    this.supportedBackgroundType = [
      {
        id: BackgroundType.TILES,
        title: 'Kacheln'
      },
      {
        id: BackgroundType.FILLING,
        title: 'ausfüllend'
      }
    ];
  }

  private _createSampleFontFamily() {
    this.sampleFontFamily = [];

    this.sampleFontFamily.push('Arial');
    this.sampleFontFamily.push('Gill Sans');
    this.sampleFontFamily.push('Helvetica');
  }

  private _createSampleFontSizes() {
    this.sampleFontSizes = [];
    for (let i = 4; i < 20; i = i + 2) {
      this.sampleFontSizes.push(i + 'px');
    }
  }

  private _createSupportedFontStyles() {
    this.supportedFontStyles = [
      {
        id: FontType.BOLD,
        icon: 'format_bold',
        checked: false
      },
      {
        id: FontType.UNDERLINE,
        icon: 'format_underline',
        checked: false
      },
      {
        id: FontType.ITALIC,
        icon: 'format_italic',
        checked: false
      },
    ];
  }

}
