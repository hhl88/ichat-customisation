import {ChangeDetectorRef, Component, ComponentFactoryResolver, ComponentRef, Input, OnInit, ViewContainerRef} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {IChatSettingsService} from 'preview/services/ichat-settings.service';
import {environment} from 'environments/environment';
import {IChatService} from 'preview/services/ichat.service';
import {Frontend} from 'core/interfaces/frontend.interface';
import {Observable, Subscription} from 'rxjs';
import 'rxjs-compat/add/observable/interval';
import 'rxjs-compat/add/operator/takeWhile';
import {MessengerComponent} from 'preview/components/messenger/messenger.component';
import {BubbleStyle} from 'core/interfaces/bubble-style.interface';
import {Bubble} from 'core/interfaces/bubble.interface';

declare const require;

const cssMessenger = require('../messenger/messenger.component.css');
const cssSingleMessage = require('../single-message/single-message.component.css');
const cssButtonGroupChat = require('../button-group-chat/button-group-chat.component.css');

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
  customer: any;
  isLoading = false;
  isLoadingSetting = false;
  isConnected = false;
  isConnecting = false;

  chatId: number = null;

  subConnection: Subscription;
  subPolling: Subscription;

  chatStopped: boolean;
  server: string;
  windowReference: any;

  isFormChatValid = false;
  isPopUp = false;
  token: string;


  constructor(private iChatSettingsService: IChatSettingsService,
              private iChatService: IChatService,
              private cd: ChangeDetectorRef,
              private r: ComponentFactoryResolver,
              private viewContainerRef: ViewContainerRef) {
  }

  ngOnInit() {
    this.isLoadingSetting = true;
    this.isConnected = false;
    this.chatStopped = false;
    this.isLoading = true;
    this.isConnecting = false;
    this.getSettings();

  }

  private getSettings() {
    this.isLoadingSetting = true;
    this.iChatSettingsService.getSettings(this.id).subscribe(settings => {
      console.log('settings', settings);
      this.chatLayout = settings.layout;
      this.chatFrontend = settings.frontend;
      this.chatLayout.logo = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/logoImg';
      this.chatLayout.backgroundImg = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/backgroundImg';
      this.isPopUp = this.chatLayout.displayType === 2;
      this.server = this._findServer(this.chatFrontend.iAgentServer.address);
      this.iChatService.setServer(this._findServer(this.chatFrontend.iAgentServer.address));
      this.isLoadingSetting = false;
      this._saveNewBubbleSettings(this.chatLayout.bubbleStyle);

      this.cd.detectChanges();
    });
  }

  onFormChatChanged(rawValue) {
    this.customer = rawValue.data;
    this.isFormChatValid = rawValue.isFormValid;
  }

  startNewChat() {
    this._setSubConnection();

    this.isConnected = false;
    this.isLoading = true;
    this.customer['info'] = {
      ...this.customer
    };

    this.iChatService.createNewChat(this.customer).subscribe(res => {
      if (res) {
        this.isConnecting = true;
        this.isLoading = false;
        this.chatId = res.chatId;
        this._detectMessengerChanges();
      }
    });
  }

  onConversation(isChatting: boolean) {
    this.isConnected = isChatting;
    if (!isChatting) {
      this.isConnected = false;
      this.chatStopped = true;
      this.isLoading = true;
      this.isConnecting = false;
    }
  }

  initPopupChat() {
    this.windowReference = window.open('', '_blank', 'toolbar=0,width=800,height=400');
    setTimeout(() => {
      const factory = this.r.resolveComponentFactory(MessengerComponent);
      const comp: ComponentRef<MessengerComponent> = this.viewContainerRef.createComponent(factory);

      comp.instance.chatLayout = this.chatLayout;
      comp.instance.chatFrontend = this.chatFrontend;

      comp.instance.chatId = this.chatId;
      comp.instance.token = this.token;
      comp.instance.server = this.server;
      comp.instance.customer = this.customer;

      comp.instance.ref = this.windowReference;

      // add you freshly baked component on the windows
      const title = document.createElement('title');
      title.text = 'Chat';
      this.windowReference.document.head.appendChild(title);

      const style = document.createElement('style');
      style.innerHTML = cssMessenger;
      style.innerHTML += cssSingleMessage;
      style.innerHTML += cssButtonGroupChat;

      this.windowReference.document.body.appendChild(style);
      this.windowReference.document.body.appendChild(comp.location.nativeElement);

    });
    // create the component dynamically
  }

  private _handleMessageResponse(res) {
    if (!!res && !!res.type) {
      if (res.type === 'ChatChangeChatstep') {
        if (!!res.origin && res.origin.substr(0, res.origin.indexOf('.')) === 'agent') {
          this.iChatService.setConnection(true);
        }
      }
    }
  }

  private _detectMessengerChanges() {
    if (this.chatId) {
      this.subPolling = Observable
        .interval(1000)
        .takeWhile(() => true)
        .subscribe(() => {
            setTimeout(() => {
              this.iChatService.pollingMessenger(this.chatId).subscribe(res => {
                if (res && res.changes) {
                  for (const change of res.changes) {
                    if (change.chatId && change.chatId > 0) {
                      this.chatId = change.chatId;
                      this.iChatService.setToken(change.token);
                      this.token = change.token;
                    }
                    this._handleMessageResponse(change);
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

  private _setSubConnection() {
    this.subConnection = this.iChatService.checkConnection().subscribe(isConnected => {
      if (isConnected) {
        this.subPolling.unsubscribe();
        this.subConnection.unsubscribe();
        this.isConnected = true;
        if (this.isPopUp) {
          this.initPopupChat();
        }
      }
    });
  }

  private _findServer(serverAddress: string) {
    if (!this._isShowroom(serverAddress)) {
      return serverAddress;
    }
    const addressArr = serverAddress.split(/(https|http):\/\/|:|\//);
    const address = addressArr[2];
    return 'https://showroom-bridges.novomind.com/sr' + this._whichServer(address) + '_iagent_chat_p01';

  }

  private _whichServer(address) {
    if (address.includes('showroom2')) {
      return '03';
    } else if (address.includes('showroom3')) {
      return '04';
    }
    return '01';
  }

  private _isShowroom(address) {
    return address.includes('showroom');
  }


  private _saveNewBubbleSettings(bubbleStyle: BubbleStyle) {
    Object.keys(bubbleStyle).forEach(key => this._convertToJson(key, bubbleStyle[key]));
  }

  private _convertToJson(className: string, bubble: Bubble) {
    const newSetting = {};
    newSetting['.' + className] = Object.keys(bubble).forEach(key => {
      const newKey = key.replace(/([A-Z])/g, (v) => {
        return '-' + v.toLowerCase();
      });
      return {newKey: bubble[key]};
    });
    console.log('newSetting', newSetting);
  }

}
