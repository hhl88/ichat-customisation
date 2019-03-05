import {ChangeDetectorRef, Component, ComponentFactoryResolver, ComponentRef, Input, OnInit, ViewContainerRef} from '@angular/core';
import {Layout, LayoutDefault} from 'core/interfaces/layout.interface';
import {IChatSettingsService} from 'preview/services/ichat-settings.service';
import {environment} from 'environments/environment';
import {IChatService} from 'preview/services/ichat.service';
import {Frontend, FrontendDefault} from 'core/interfaces/frontend.interface';
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
  customer: any = {};
  agent: any = {};
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
      this.chatLayout = !!settings.layout ? settings.layout : LayoutDefault;
      this.chatFrontend = !!settings.frontend ? settings.frontend : FrontendDefault;
      if (this.chatLayout) {
        if (this.chatLayout.id) {
          this.chatLayout.backgroundImg = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/backgroundImg';
          this.chatLayout.logo = environment.iChatLayoutApi + '/' + this.chatLayout.id + '/logoImg';
        } else {
          this.chatLayout.logo = 'https://www.novomind.com/typo3conf/ext/extension-kwi/Resources/Public/Frontend/Img/novomind/images/Novomind_Logo_RGB/PNG/Novomind_Logo.png';
          this.chatLayout.backgroundImg = 'https://www.novomind.com/fileadmin/_processed_/7/2/csm_NM_Produktlogo_Symbol_iAGENT_RGB_ad39f4cbc6.png';
        }

        this.isPopUp = this.chatLayout.displayType === 2;
      }

      if (this.chatFrontend && this.chatFrontend.iAgentServer) {
        this.server = this.chatFrontend.iAgentServer.address;
        this.iChatService.setServer(this.chatFrontend.iAgentServer.address);
      }
      this.isLoadingSetting = false;
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

  initPopupChat(bubbleSettings) {
    this.windowReference = window.open('', '_blank', 'toolbar=0,width=800,height=450');
    setTimeout(() => {
      const factory = this.r.resolveComponentFactory(MessengerComponent);
      const comp: ComponentRef<MessengerComponent> = this.viewContainerRef.createComponent(factory);

      comp.instance.chatLayout = this.chatLayout;
      comp.instance.chatFrontend = this.chatFrontend;

      comp.instance.chatId = this.chatId;
      comp.instance.token = this.token;
      comp.instance.server = this.server;
      comp.instance.customer = this.customer;
      comp.instance.agent = this.agent;

      comp.instance.ref = this.windowReference;

      // add you freshly baked component on the windows
      const title = document.createElement('title');
      title.text = 'Chat';
      this.windowReference.document.head.appendChild(title);

      const style = document.createElement('style');
      style.innerHTML = cssMessenger;
      style.innerHTML += cssSingleMessage;
      style.innerHTML += cssButtonGroupChat;
      style.innerHTML += bubbleSettings;
      this.windowReference.document.body.appendChild(style);

      const script = document.createElement('script');

      const code =
        'if( document.readyState !== "loading" ) {' +
        '    loadWindow();' +
        '    resizeWindow();' +
        '} else {' +
        '    document.addEventListener("DOMContentLoaded", function () {' +
        '        loadWindow();' +
        '        resizeWindow();' +
        '    });' +
        '}' +
        '' +
        'function resizeWindow() {' +
        '    window.addEventListener("resize", function() {' +
        '       setHeight();' +
        '    });' +
        '}' +
        'function loadWindow() {' +
        '    window.addEventListener("load", function() {' +
        '       setHeight();' +
        '    });' +
        '}' +
        'function setHeight() {' +
        '     var wHeight = window.innerHeight;' +
        '     if(wHeight > 425) {' +
        '        document.getElementById("chatWindowWrapper").style.height = (wHeight - 20) + "px";' +
        '     }' +
        '     var logoHeight = document.getElementById("logoChat").clientHeight;' +
        '     var conversationFooter = document.getElementById("conversationFooter").clientHeight;' +
        '     var buttonGroupHeight = 0;' +
        '     var buttonGroupBelow = document.getElementById("buttonGroupBelow");' +
        '     if(buttonGroupBelow) {' +
        '         buttonGroupHeight = buttonGroupBelow.clientHeight;' +
        '     }' +
        '     document.getElementById("conversationBody").style.height = (document.getElementById("chatWindowWrapper").clientHeight - logoHeight - conversationFooter - buttonGroupHeight - 10) + "px";' +
        '}';

      try {
        script.appendChild(document.createTextNode(code));
        this.windowReference.document.body.appendChild(script);
      } catch (e) {
        script.text = code;
        this.windowReference.document.body.appendChild(script);
      }
      this.windowReference.document.body.appendChild(comp.location.nativeElement);

    });
    // create the component dynamically
  }

  private _handleMessageResponse(res) {
    if (!!res && !!res.type) {
      if (res.type === 'ChatChangeChatstep') {
        if (!!res.origin && res.origin.substr(0, res.origin.indexOf('.')) === 'agent') {
          this.agent.name = res.nickname;
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
        const bubbleSettings = this._getNewBubbleSettings(this.chatLayout.bubbleStyle);
        if (this.isPopUp) {
          this.initPopupChat(bubbleSettings);
        } else {
          const style = document.createElement('style');
          style.innerHTML += bubbleSettings;
          document.body.appendChild(style);

        }
      }
    });
  }

  private _getNewBubbleSettings(bubbleStyle: BubbleStyle) {
    return Object.keys(bubbleStyle).map(key => this._getBubbleSetting(key, bubbleStyle[key])).join('');
  }

  private _getBubbleSetting(className: string, bubble: Bubble) {
    const aq12 = Object.keys(bubble).map(key => {
      const newKey = key.replace(/([A-Z])/g, (v) => {
        return '-' + v.toLowerCase();
      });
      return newKey + ': ' + bubble[key];
    });
    return '.' + className + '{' + aq12.join(' !important;') + '}';
  }

}
