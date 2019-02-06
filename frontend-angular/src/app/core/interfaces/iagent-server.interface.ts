export interface IAgentServer {
  address: string;
  userAPI: string;
  password: string;
  clientId: string;
  secret: string;
}

export const DefaultIAgentServer: IAgentServer = {
  address: '',
  userAPI: '',
  password: '',
  clientId: '',
  secret: ''
};
