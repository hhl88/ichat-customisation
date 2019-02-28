import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandInfoComponent } from './demand-info.component';

describe('DemandInfoComponent', () => {
  let component: DemandInfoComponent;
  let fixture: ComponentFixture<DemandInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DemandInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
