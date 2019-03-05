import {ConnectionType} from 'core/enum/connection-type.enum';
import {DefaultIAgentServer, IAgentServer} from 'core/interfaces/iagent-server.interface';
import {DefaultDemandInfo, DemandInfo} from 'core/interfaces/demand-info.interface';

export interface Frontend {
  id?: string;
  _uid?: any;
  name: string;
  connectionType: ConnectionType;
  urlPath: string;
  demandInfo: DemandInfo;
  iAgentServer?: IAgentServer;
  cloud?: any;
}

export const FrontendDefault: Frontend = {
  name: 'FrontEnd',
  connectionType: ConnectionType.IAGENT_SERVER,
  urlPath: '',
  demandInfo: DefaultDemandInfo,
  iAgentServer: DefaultIAgentServer,
};


