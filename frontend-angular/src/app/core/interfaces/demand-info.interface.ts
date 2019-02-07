import {Info} from 'core/interfaces/info.interface';

export interface DemandInfo {
  id?: string;
  demandInfoList: Info[];
}

export const DefaultDemandInfo: DemandInfo = {
  demandInfoList: []
};
