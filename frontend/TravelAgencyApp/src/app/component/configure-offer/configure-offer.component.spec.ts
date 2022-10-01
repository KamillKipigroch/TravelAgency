import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigureOfferComponent } from './configure-offer.component';

describe('ConfigureOfferComponent', () => {
  let component: ConfigureOfferComponent;
  let fixture: ComponentFixture<ConfigureOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfigureOfferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigureOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
