import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FrontendContentComponent } from './frontend-content.component';

describe('FrontendContentComponent', () => {
  let component: FrontendContentComponent;
  let fixture: ComponentFixture<FrontendContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FrontendContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FrontendContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
