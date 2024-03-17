import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistrictViceComponent } from './district-vice.component';

describe('DistrictViceComponent', () => {
  let component: DistrictViceComponent;
  let fixture: ComponentFixture<DistrictViceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistrictViceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistrictViceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
