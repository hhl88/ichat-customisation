import { TestBed } from '@angular/core/testing';

import { IagentServerService } from './iagent-server.service';

describe('IagentServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IagentServerService = TestBed.get(IagentServerService);
    expect(service).toBeTruthy();
  });
});
