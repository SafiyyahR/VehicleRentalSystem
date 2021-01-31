import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveVehicleComponent } from './reserve-vehicle.component';

describe('ReserveVehicleComponent', () => {
  let component: ReserveVehicleComponent;
  let fixture: ComponentFixture<ReserveVehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReserveVehicleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
