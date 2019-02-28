import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-form-chat',
  templateUrl: './form-chat.component.html',
  styleUrls: ['./form-chat.component.scss']
})
export class FormChatComponent implements OnInit, OnChanges, OnDestroy {
  @Input() demandInfoList: any[] = [];
  @Output() onFormChatChanged = new EventEmitter();
  supportedCategories: string[] = [];
  formChat: FormGroup;
  isLoading = true;
  sub: Subscription;
  keys: string[] = [];

  @Input() isConnecting: boolean;

  constructor() {
  }

  ngOnInit() {
    this.isLoading = true;
    this.supportedCategories.push('Chat_SR2');
    this.resetFormChat();
  }

  resetFormChat() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
    this.formChat = new FormGroup({});
    for (const demandInfo of this.demandInfoList) {
      this.keys.push(demandInfo.name);
      if (demandInfo.required) {
        this.formChat.addControl(demandInfo.name, new FormControl(demandInfo.example, [Validators.required]));
      } else {
        this.formChat.addControl(demandInfo.name, new FormControl(demandInfo.example));
      }
    }
    this.onFormChatChanged.emit({
      data: this.formChat.getRawValue(),
      isFormValid: !this.formChat.invalid
    });
    this.sub = this.formChat.valueChanges.subscribe(data => {
      this.onFormChatChanged.emit({
        data: data,
        isFormValid: !this.formChat.invalid
      });
    });
    this.isLoading = false;
  }

  ngOnChanges(): void {
    if (!this.isLoading && this.formChat) {
      const values = this.formChat.getRawValue();
      if (this.isConnecting) {
        Object.keys(values).forEach(key => this.formChat.get(key).disable());
      } else {
        Object.keys(values).forEach(key => this.formChat.get(key).enable());
      }
    }
  }

  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

}
