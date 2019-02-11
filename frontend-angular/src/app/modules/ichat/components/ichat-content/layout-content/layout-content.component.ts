import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {DisplayType} from 'core/enum/display-type.enum';
import {TextInputType} from 'core/enum/text-input-type.enum';
import {ButtonType} from 'core/enum/button-type.enum';
import {FontType} from 'core/enum/font-type.enum';
import {BackgroundType} from 'core/enum/background-type.enum';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ImageCroppedEvent} from 'ngx-image-cropper/src/image-cropper.component';

@Component({
  selector: 'app-layout-content',
  templateUrl: './layout-content.component.html',
  styleUrls: ['./layout-content.component.scss']
})
export class LayoutContentComponent implements OnInit, OnChanges {
  @Input() layout: Layout;
  selectedLayout: Layout = null;

  form: FormGroup;

  supportedLayouts: any[];
  supportedTextInputs: any[];
  supportedButtonType: any[];
  supportedBackgroundType: any[];
  sampleFontFamily: string[];
  sampleFontSizes: number[];
  supportedFontStyles: any[];

  imageChangedEvent: any[] = [];
  croppedImage: any[] = new Array(2) as any[];

  isSelected: boolean[] = [false, false];

  constructor() {
    this._createSupportedChatLayout();
    this._createSupportedTextInputs();
    this._createSupportedButtonType();
    this._createSupportedBackgroundType();
    this._createSampleFontFamily();
    this._createSampleFontSizes();
    this._createSupportedFontStyles();
  }

  ngOnInit() {
    this._setLayout();
    this._reloadForm();
  }

  ngOnChanges() {
    if (this.selectedLayout === null || this.layout.index !== this.selectedLayout.index) {
      this._setLayout();
    }
  }

  private _setLayout() {
    this.selectedLayout = JSON.parse(JSON.stringify(this.layout));
  }

  private _initForm() {
    this.form = new FormGroup({});
    Object.keys(this.selectedLayout).forEach(key => {
      if (key !== 'font') {
        this.form.addControl(key, new FormControl(this.selectedLayout[key], [Validators.required]));
      } else {
        const font = new FormGroup({});
        Object.keys(this.selectedLayout.font).forEach(subKey => {
          font.addControl(subKey, new FormControl(this.selectedLayout[key][subKey], [Validators.required]));
        });
        this.form.addControl(key, font);
      }
    });
    this.form.valueChanges.subscribe(data => {
      this.selectedLayout = data;
    });
  }

  private _reloadForm() {
    if (this.form) {
      this.form.reset();
    } else {
      this._initForm();
    }

    if (this.selectedLayout && this.selectedLayout.hasOwnProperty('name')) {

      Object.keys(this.selectedLayout).forEach(key => {
        if (this.form.get(key) instanceof FormControl) {
          this.form.controls[key].setValue(this.selectedLayout[key]);
        } else if (this.form.get(key) instanceof FormGroup) {
          Object.keys(this.selectedLayout[key]).forEach(subKey => {
            (<FormGroup>this.form.controls[key]).controls[subKey].setValue(this.selectedLayout[key][subKey]);
          });
        }
      });
    }
  }

  onFileChange(event, index: number) {
    this.imageChangedEvent[index] = event;
    this.isSelected[index] = true;
  }

  imageCropped(event: ImageCroppedEvent, index: number) {
    this.croppedImage[index] = event;
  }

  imageLoaded(index: number) {
    this.isSelected[index] = true;
  }

  loadImageFailed() {
  }

  cancelCurrent() {

  }

  submitCurrent() {

  }

  cropImage(event, index: number) {
    if (index === 1) {
      this.form.controls['backgroundImg'].setValue(this.croppedImage[1].file);
      this.selectedLayout.backgroundImg = this.croppedImage[1].base64;
      this.isSelected[1] = false;

    } else if (index === 0) {
      this.form.controls['logo'].setValue(this.croppedImage[0].file);
      this.selectedLayout.logo = this.croppedImage[0].base64;
      this.isSelected[0] = false;

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
      this.sampleFontSizes.push(i);
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
