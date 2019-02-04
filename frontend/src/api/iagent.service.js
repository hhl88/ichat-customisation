import axios from 'axios'
import qs from 'querystring'

export const IAgentService = {

  fetchIAgentServer (server) {
    let address = server.address
    address = address.split(/(https|http):\/\/|:|\//)[2];

    address = 'https://' + address;
    address += '/iMail/api/rest/token';
    const body = {
      grant_type: 'password',
      username: server.userAPI,
      password: server.password,
      redirect_uri: 'https://showroom2.novomind.com/freeboard/token.html'
    }

    const headers = {
      'Accept-Version': 1,
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/x-www-form-urlencoded',
      'Access-Control-Allow-Headers': 'Access-Control-Allow-Headers, Origin, Accept, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
      'Authorization': 'Basic ' + Buffer.from(server.clientId + ':' + server.secret).toString('base64'),
      'Access-Control-Allow-Credentials': true,
      'Vary': 'Accept-Encoding',
    }
    return axios.create({headers: headers}).post(address, qs.stringify(body))
  }


}

