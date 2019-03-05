export interface IAgentServer {
  address: string;
  userAPI: string;
  password: string;
  clientId: string;
  secret: string;
}

export const DefaultIAgentServer: IAgentServer = {
  address: 'https://showroom-bridges.novomind.com/sr04_iagent_chat_p01',
  userAPI: '',
  password: '',
  clientId: '',
  secret: ''
};
