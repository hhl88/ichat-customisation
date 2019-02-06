import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IchatComponent } from './ichat.component';

describe('IchatComponent', () => {
  let component: IchatComponent;
  let fixture: ComponentFixture<IchatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IchatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
