import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-single-message',
  templateUrl: './single-message.component.html',
  styleUrls: ['./single-message.component.css']
})
export class SingleMessageComponent implements OnInit {
  @Input() msg: any;

  constructor() {
  }

  ngOnInit() {
  }

}
