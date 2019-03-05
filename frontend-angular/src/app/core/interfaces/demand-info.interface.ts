import {Info} from 'core/interfaces/info.interface';

export interface DemandInfo {
  id?: string;
  demandInfoList: Info[];
}

export const DefaultDemandInfo: DemandInfo = {
  demandInfoList: [
    {
      name: 'nickname',
      example: 'Customer',
      required: true
    } as Info,
    {
      name: 'email',
      example: 'customer@example',
      required: true
    } as Info,
    {
      name: 'tel',
      example: '040 111 111',
      required: false
    } as Info,
    {
      name: 'category',
      example: 'Chat_SR2',
      required: true
    } as Info
  ]
};
