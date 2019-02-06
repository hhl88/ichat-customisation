import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IchatContentComponent } from './ichat-content.component';

describe('IchatContentComponent', () => {
  let component: IchatContentComponent;
  let fixture: ComponentFixture<IchatContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IchatContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IchatContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
