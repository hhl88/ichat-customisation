import {AfterViewInit, ChangeDetectorRef, Component, ElementRef, HostListener, Input, OnInit, Renderer2, ViewChild} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IChatSettingsService} from 'preview/services/ichat-settings.service';
import {environment} from 'environments/environment';
import {IChatService} from 'preview/services/ichat.service';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Observable, Subscription} from 'rxjs';
import 'rxjs-compat/add/observable/interval';
import 'rxjs-compat/add/operator/takeWhile';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  providers: [IChatSettingsService, IChatService]
})
export class ChatComponent implements OnInit {
  @Input() id: string;

  chatLayout: Layout;
  chatFrontend: Frontend;
  form: FormGroup;
  isLoading = false;
  messages: any[] = [];
  mouseMoveListener: Function;

  chatId: number = null;

  sub: Subscription;

  constructor(private iChatSettingsService: IChatSettingsService,
              private iChatService: IChatService,
              private cd: ChangeDetectorRef,
              private renderer: Renderer2) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.form = new FormGroup({
      message: new FormControl('', [Validators.required])
    });
    this.getSettings();
  }

  private getSettings() {
    this.iChatSettingsService.getSettings(this.id).subscribe(settings => {
      this.chatLayout = settings.layout;
      this.chatFrontend = settings.frontend;
      this.chatLayout.logo = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/logoImg';
      this.chatLayout.backgroundImg = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/backgroundImg';
      this.isLoading = false;


      this.cd.detectChanges();


      console.log('https', this._getServerIChat(this.chatFrontend.iAgentServer.address));
      this.iChatService.setServer('https://showroom-bridges.novomind.com/sr04_iagent_chat_p01');
    });


  }

  startNewChat() {

    const body = {
      category: 'Zahlungsart',
      nickname: 'John Doe'
    };
    this.iChatService.setServer('https://showroom-bridges.novomind.com/sr04_iagent_chat_p01');

    this.iChatService.createNewChat(body).subscribe(res => {
      console.log('res', res);
      if (res) {
        this.chatId = res.chatId;
        this.cd.detectChanges();

        this.setLogo('logoChat');
        this.setBackground('messengerBody');
        this._setHeightMessengerBody(document.getElementById('chatWindowWrapper').offsetHeight);
        // this.detectMessengerChanges();
      }

    });
  }

  sendMessage() {

  }

  closeChat() {

  }


  setLogo(id: string) {
    (document.getElementById(id) as HTMLImageElement).src = this.chatLayout.logo;
  }

  setBackground(id: string) {
    const ele = document.getElementById(id);
    ele.style.backgroundImage = 'url(' + this.chatLayout.backgroundImg + ')';
  }

  @HostListener('mousedown', ['$event.target'])
  onMouseDown(chatWindowWrapper) {
    if (chatWindowWrapper.id && chatWindowWrapper.id === 'chatWindowWrapper') {
      console.log('id', chatWindowWrapper.id);
      const width = chatWindowWrapper.offsetWidth;
      const height = chatWindowWrapper.offsetHeight;

      this.mouseMoveListener = this.renderer.listen('document', 'mousemove', () => {
        if (width !== chatWindowWrapper.offsetWidth || height !== chatWindowWrapper.offsetHeight) {
          this._setHeightMessengerBody(chatWindowWrapper.offsetHeight);
        }
      });
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
    const logoHeight = document.getElementById('logoChat').clientHeight;
    const messengerFooter = document.getElementById('messengerFooter').clientHeight;
    document.getElementById('messengerBody').style.height = (chatWindowWrapperHeight - logoHeight - messengerFooter) + 'px';
  }

  private _getPaddingTopMessengerBody() {
    const style = window.getComputedStyle(document.getElementById('messengerBody'));
    return +style.paddingTop.replace('px', '');
  }

  detectMessengerChanges() {
    if (this.chatId) {
      // this.sub = Observable
      //   .interval(1000)
      //   .takeWhile(() => true)
      //   .subscribe(() => {
      //       setTimeout(() => {
              this.iChatService.pollingMessenger(this.chatId).subscribe(changes => {
                console.log('changes', changes);
                if (changes) {
                  if (changes[0].chatId && changes[0].chatId > 0) {
                    this.chatId = changes[0].chatId;
                    console.log('chatId', changes[0].chatId > 0);
                    this.iChatService.setToken(changes[0].token);
                  }
                }
              }, error => {
                console.log('polling error', error);
              });
        //     }, 500);
        //   }
        // );
    }

  }

  private _getServerIChat(iAgentServerAddress: string) {
    const isHttps = iAgentServerAddress.indexOf('https');
    return isHttps;

  }


}
