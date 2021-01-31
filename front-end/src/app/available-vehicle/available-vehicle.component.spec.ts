import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableVehicleComponent } from './available-vehicle.component';

describe('AvailableVehicleComponent', () => {
  let component: AvailableVehicleComponent;
  let fixture: ComponentFixture<AvailableVehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvailableVehicleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
