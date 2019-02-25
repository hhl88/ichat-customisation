import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-single-message',
  templateUrl: './single-message.component.html',
  styleUrls: ['./single-message.component.scss']
})
export class SingleMessageComponent implements OnInit {
  @Input() msg: any;
  @Input() partnerName: string;
  subject: string;

  constructor() { }

  ngOnInit() {
    this.subject = this.msg.from_me ? 'You' : this.partnerName;

  }

}
