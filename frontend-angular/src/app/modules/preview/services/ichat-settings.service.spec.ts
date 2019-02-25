import { TestBed } from '@angular/core/testing';

import { IchatSettingsService } from './ichat-settings.service';

describe('IchatSettingsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IchatSettingsService = TestBed.get(IchatSettingsService);
    expect(service).toBeTruthy();
  });
});
