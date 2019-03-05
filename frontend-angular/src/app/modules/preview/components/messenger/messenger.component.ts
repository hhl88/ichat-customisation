import {AfterViewInit, Component, EventEmitter, HostListener, Input, OnDestroy, OnInit, Output, Renderer2} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable, Subscription} from 'rxjs';
import {IChatService} from 'preview/services/ichat.service';


@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css'],
  providers: [IChatService]
})
export class MessengerComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() chatLayout: any;
  @Input() chatFrontend: any;
  @Input() token: string;

  @Input() chatId: number;
  @Input() customer: any;
  @Input() agent: any;
  @Input() server: string;
  @Output() onConversation = new EventEmitter();
  messages: any[] = [];
  formMessage: FormGroup;
  sub: Subscription;
  mouseMoveListener: Function;

  chatStopped: boolean;
  isLoading: boolean;
  ref: any;


  constructor(private iChatService: IChatService,
              private renderer: Renderer2) {

  }

  ngOnInit() {
    this.isLoading = true;
    this.chatStopped = false;
    if (this.token) {
      this.iChatService.setToken(this.token);
    }
    if (this.server) {
      this.iChatService.setServer(this.server);

    }
    this.formMessage = new FormGroup({
      message: new FormControl('', [Validators.required])
    });
    this.isLoading = false;
  }

  ngAfterViewInit(): void {
    this._setLogo('logoChat');

    this._setBackground('conversationBody');
    // this._setButtonGroupStyle();
    const ele = this._getEle('conversationBody');

    this._setFont(ele);
    this._setImageStyle(this.chatLayout.backgroundType, ele);
    this._setHeightMessengerBody(this._getEle('chatWindowWrapper').offsetHeight);
    this.chatStopped = false;

    if (this.chatLayout.displayType === 2) {
      this._getEle('chatWindowWrapper').style.resize = 'none';

      window.addEventListener('resize', () => {
        const width = window.innerWidth;
        const height = window.innerHeight;
        this._setHeightMessengerBody(ele.offsetHeight);
        console.log('Size win: ' + width + 'x' + height);
      });
    }

    this._detectMessengerChanges();

  }

  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }


  sendMessage() {
    const message = this.formMessage.getRawValue().message;
    this.messages.push({
      message: message.replace(/\r?\n/g, '<br />'),
      name: this.customer.nickname,
      date: this._convertTimeStamp(new Date().valueOf()),
      isAgent: false
    });
    this.formMessage.reset();
    this.iChatService.sendNewMessage(this.chatId, {message: message}).subscribe(res => {
    });
  }

  closeChat() {
    const message = {
      message: 'you have closed the conversation',
      date: this._convertTimeStamp(new Date().valueOf()),
      isSystem: true
    };
    this._closeChat(message);
  }

  private _closeChat(message) {
    this.iChatService.stopCurrentChat(this.chatId).subscribe(res => {
      this.messages.push(message);
      this.formMessage.get('message').disable();
      this.sub.unsubscribe();
      this.iChatService.setToken('');
      this.iChatService.setConnection(false);
      this.chatStopped = true;

    });
  }

  saveConversation() {

  }

  closeConversation() {
    this.customer = {};
    this.agent = {};
    this.messages = [];
    this.onConversation.emit(false);
    this._resetForm();
  }


  private _setLogo(id: string) {
    (this._getEle(id) as HTMLImageElement).src = this.chatLayout.logo;
  }

  private _getEle(id) {
    if (this.ref) {
      return this.ref.document.getElementById(id);
    }
    return document.getElementById(id);
  }

  private _setBackground(id: string) {
    const ele = this._getEle(id);
    ele.style.backgroundImage = 'url(' + this.chatLayout.backgroundImg + ')';
  }

  private _detectMessengerChanges() {
    if (!!this.chatId) {
      this.sub = Observable
        .interval(1000)
        .takeWhile(() => true)
        .subscribe(() => {
            setTimeout(() => {
              this.iChatService.pollingMessenger(this.chatId).subscribe(res => {
                if (res) {
                  if (res.changes) {
                    if (res.changes.length > 0) {
                      for (const change of res.changes) {
                        if (change.hatId && change.chatId > 0) {
                          this.chatId = change.chatId;
                          this.iChatService.setToken(change.token);
                        }
                        this._handleMessageResponse(change);
                      }
                    }
                  }
                }
              }, error => {
                console.log('polling error', error);
              });
            }, 500);
          }
        );
    }

  }


  private _handleMessageResponse(res) {
    if (!!res && !!res.type) {
      if (res.type === 'ChatChangeChatstep') {
        console.log('chatChangeChatStep', res);
        if (!!res.origin) {
          const origin = res.origin.substr(0, res.origin.indexOf('.'));
          if (origin === 'agent') {
            this.agent.name = res.nickname;
          }
        }
        if (!!res.message) {
          this.messages.push({
            message: res.message,
            date: this._convertTimeStamp(+res.timestamp),
            name: res.nickname,
            isAgent: true
          });
        }
      } else if (res.type === 'ChatChangeStop') {
        const message = {
          message: this.agent.name + ' has closed the conversation',
          date: this._convertTimeStamp(new Date().valueOf()),
          isSystem: true
        };
        this._closeChat(message);
      }
    }
  }


  private _convertTimeStamp(timestamp): string {
    const date = new Date(timestamp);
    return [
        date.getDate(), date.getMonth() + 1, date.getFullYear()].join('.')
      + ' ' +
      date.toLocaleTimeString();
  }

  private _convertStyle(numStyle) {
    if (numStyle === 0) {
      return 'bold';
    } else if (numStyle === 1) {
      return 'italic';
    } else if (numStyle === 2) {
      return 'underline';
    }
    return '';

  }

  private _convertImageStyle(numStyle) {
    if (numStyle === 0) {
      return 'cover';
    } else if (numStyle === 1) {
      return 'contain';
    }
    return 'auto';
  }

  private _setImageStyle(numStyle, ele) {
    ele.style.backgroundSize = this._convertImageStyle(numStyle);
  }

  private _setFont(ele) {
    let fontStyle = '';
    let isUnderline = false;
    const font = this.chatLayout.font;
    for (const numStyle of font.fontStyles) {
      const style = this._convertStyle(numStyle);
      if (style !== 'underline') {
        fontStyle += style + ' ';
      } else {
        isUnderline = true;
      }
    }
    ele.style.fontFamily = font.fontFamily;
    ele.style.fontSize = font.fontSize;
    ele.style.fontStyle = fontStyle;

    if (isUnderline) {
      ele.style.textDecoration = 'underline';
    }
  }


  @HostListener('mousedown', ['$event.target'])
  onMouseDown(chatWindowWrapper) {
    console.log('onMouseDown');
    if (this.chatLayout.displayType !== 2) {

      const ele = this._getEle('chatWindowWrapper');
      if (!!ele) {
        const width = ele.offsetWidth;
        const height = ele.offsetHeight;

        this.mouseMoveListener = this.renderer.listen('document', 'mousemove', () => {
          if (width !== ele.offsetWidth || height !== ele.offsetHeight) {
            this._setHeightMessengerBody(ele.offsetHeight);
          }
        });
      }
    }
  }

  @HostListener('document:mouseup')
  onMouseUp(el) {
    this.destroy();
  }


  destroy() {
    if (this.mouseMoveListener) {
      this.mouseMoveListener();
    }
  }

  private _setHeightMessengerBody(chatWindowWrapperHeight: number) {
    const logoHeight = this._getEle('logoChat').clientHeight;
    const conversationFooter = this._getEle('conversationFooter').clientHeight;
    let buttonGroupHeight = 0;
    if (this.chatLayout.buttonType === 1) {
      buttonGroupHeight = this._getEle('buttonGroupBelow').clientHeight;
    }
    this._getEle('conversationBody').style.height = (chatWindowWrapperHeight - logoHeight - conversationFooter - buttonGroupHeight) + 'px';
  }


  private _resetForm() {
    this.formMessage.reset();
    this.formMessage.get('message').enable();
  }


}
