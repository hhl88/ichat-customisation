import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {DisplayType} from 'core/enum/display-type.enum';
import {TextInputType} from 'core/enum/text-input-type.enum';
import {ButtonType} from 'core/enum/button-type.enum';
import {FontType} from 'core/enum/font-type.enum';
import {BackgroundType} from 'core/enum/background-type.enum';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ImageCroppedEvent} from 'ngx-image-cropper/src/image-cropper.component';
import {IChatService} from 'ichat/services/ichat.service';
import {environment} from 'environments/environment';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-layout-content',
  templateUrl: './layout-content.component.html',
  styleUrls: ['./layout-content.component.scss']
})
export class LayoutContentComponent implements OnInit, OnChanges {
  @Input() layout: Layout;
  @Output() onUpdateLayout = new EventEmitter();
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
  fileName = ['', ''];
  isSelected: boolean[] = [false, false];

  logoImg = '';
  backgroundImg = '';

  constructor(private iChatService: IChatService) {
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
      this._reloadForm();
    }
  }

  private _setLayout() {
    this.selectedLayout = JSON.parse(JSON.stringify(this.layout));
    this._setImages(this.selectedLayout);
  }

  private _setImages(data) {
    this.logoImg = '';
    this.backgroundImg = '';

    if (data.id !== '') {
      if (data.logo && data.logo !== '') {
        this.logoImg = environment.iChatLayoutApi + '/' + this.selectedLayout.id + '/logoImg';
      }
      if (data.backgroundImg && data.backgroundImg !== '') {
        this.backgroundImg = environment.iChatLayoutApi + '/' + this.selectedLayout.id + '/backgroundImg';
      }
    }
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
    console.log('reload 1', this.selectedLayout);
    if (this.form) {
      this.form.reset();
    } else {
      this._initForm();
    }
    const originalLayout = JSON.parse(JSON.stringify(this.layout));
    if (originalLayout && originalLayout.hasOwnProperty('name')) {
      console.log('123');
      Object.keys(originalLayout).forEach(key => {
        if (this.form.get(key) instanceof FormControl) {
          this.form.controls[key].setValue(originalLayout[key]);
        } else if (this.form.get(key) instanceof FormGroup) {
          Object.keys(originalLayout[key]).forEach(subKey => {
            (<FormGroup>this.form.controls[key]).controls[subKey].setValue(originalLayout[key][subKey]);
          });
        }
      });
    }

    console.log('reload 2', this.selectedLayout);

  }

  onFileChange(event, index: number) {
    this.imageChangedEvent[index] = event;
    this.fileName[index] = event.target.files[0].name;
    this.isSelected[index] = true;
  }

  imageCropped(event: ImageCroppedEvent, index: number) {
    this.croppedImage[index] = event;
    // console.log('event', event);
  }

  imageLoaded(index: number) {
    this.isSelected[index] = true;
  }

  loadImageFailed() {
  }

  cancelCurrent() {

  }

  submitCurrent() {
    console.log(this.selectedLayout);
    if (!(this.selectedLayout.logo instanceof Blob)) {
      this.selectedLayout.logo = '';
    }

    if (!(this.selectedLayout.backgroundImg instanceof Blob)) {
      this.selectedLayout.backgroundImg = '';
    }
    if (this.selectedLayout.id) {
      this.iChatService.updateLayout(this.selectedLayout.id, this.selectedLayout).subscribe(res => {
        const newLayout = JSON.parse(res.body);
        this._setImages(newLayout);
        this.onUpdateLayout.emit({old: this.selectedLayout, new: newLayout});

      }, error1 => {
        console.log('error update', error1);
      });
    } else {
      this.iChatService.createLayout(this.selectedLayout).subscribe(res => {
        const newLayout = JSON.parse(res.body);
        this._setImages(newLayout);
        this.onUpdateLayout.emit({old: this.selectedLayout, new: newLayout});
        this.onUpdateLayout.emit({old: this.selectedLayout, new: newLayout});
      }, error1 => {
        console.log('error', error1);
      });
    }
  }

  cropImage(event, index: number) {
    if (index === 1) {
      this.form.controls['backgroundImg'].setValue(this.croppedImage[1].file);
      this.backgroundImg = this.croppedImage[1].base64;
      this.isSelected[1] = false;
    } else {
      this.form.controls['logo'].setValue(this.croppedImage[0].file);
      this.logoImg = this.croppedImage[0].base64;
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
