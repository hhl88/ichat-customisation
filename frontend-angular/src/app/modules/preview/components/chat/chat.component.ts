import {Component, Input, OnInit} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {IChatService} from 'core/services/ichat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  providers: [IChatService]
})
export class ChatComponent implements OnInit {
  @Input() id: string;

  chatLayout: Layout;
  form: FormGroup;
  isLoading = false;

  constructor(private iChatService: IChatService) {
  }

  ngOnInit() {
    this.isLoading = true;
    this.form = new FormGroup({
      message: new FormControl('', [Validators.required])
    });
    this.iChatService.getChatLayouts().subscribe(layouts => {
      console.log('layouts', layouts);
    });
  }


  sendMessage() {

  }

  closeChat() {

  }

}
