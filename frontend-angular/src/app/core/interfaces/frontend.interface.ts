import {ConnectionType} from 'core/enum/connection-type.enum';
import {DefaultIAgentServer, IAgentServer} from 'core/interfaces/iagent-server.interface';

export interface Frontend {
  id?: string;
  index?: number;
  name: string;
  connectionType: ConnectionType;
  urlPath: string;
  demandInfoList: any[];
  iAgentServer?: IAgentServer;
  cloud?: any;
}

export const FrontendDefault: Frontend = {
  name: 'FrontEnd',
  connectionType: ConnectionType.IAGENT_SERVER,
  urlPath: '',
  demandInfoList: [],
  iAgentServer: DefaultIAgentServer,
};


