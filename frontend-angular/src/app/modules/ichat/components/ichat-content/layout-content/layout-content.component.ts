import {Component, Input, OnInit} from '@angular/core';
import {Layout} from 'core/interfaces/layout.interface';

@Component({
  selector: 'app-layout-content',
  templateUrl: './layout-content.component.html',
  styleUrls: ['./layout-content.component.scss']
})
export class LayoutContentComponent implements OnInit {
  @Input() layout: Layout;

  constructor() {
  }

  ngOnInit() {
  }

}
