import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistrictDensityMapComponent } from './district-density-map.component';

describe('DistrictDensityMapComponent', () => {
  let component: DistrictDensityMapComponent;
  let fixture: ComponentFixture<DistrictDensityMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistrictDensityMapComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistrictDensityMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
