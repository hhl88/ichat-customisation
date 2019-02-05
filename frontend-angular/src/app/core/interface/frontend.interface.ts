import {ConnectionType} from 'core/enum/connection-type.enum';
import {IAgentServer} from 'core/interface/iagent-server.interface';

export interface Frontend {
  id?: string;
  name: string;
  connectionType?: ConnectionType;
  urlPath?: string;
  demandInfoList?: any[];
  iAgentServer?: IAgentServer;
  cloud?: any;
}
