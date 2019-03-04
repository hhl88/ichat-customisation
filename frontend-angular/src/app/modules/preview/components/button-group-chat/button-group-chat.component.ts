import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-button-group-chat',
  templateUrl: './button-group-chat.component.html',
  styleUrls: ['./button-group-chat.component.css']
})
export class ButtonGroupChatComponent implements OnInit {
  @Input() isBySide: boolean;
  @Input() chatStopped: boolean;
  @Output() onSendMessage = new EventEmitter();
  @Output() onCloseChat = new EventEmitter();
  @Output() onSaveConversation = new EventEmitter();
  @Output() onCloseConversation = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  sendMessage() {
    this.onSendMessage.emit();
  }

  closeChat() {
    this.onCloseChat.emit();
  }

  saveConversation() {
    this.onSaveConversation.emit();
  }

  closeConversation() {
    this.onCloseConversation.emit();
  }
}
