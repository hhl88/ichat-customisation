import { TestBed } from '@angular/core/testing';

import { IchatService } from './ichat.service';

describe('IchatService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IchatService = TestBed.get(IchatService);
    expect(service).toBeTruthy();
  });
});
