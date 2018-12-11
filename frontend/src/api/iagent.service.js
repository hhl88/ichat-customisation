import axios from 'axios'
import qs from 'querystring'

export const IAgentService = {

  fetchIAgentServer (server) {
    // const address = 'https://showroom2.novomind.com/iMail/api/rest/token'
    let address = server.address
    address = address.split(/(https|http):\/\/|:|\//)[2];

    address = 'https://' + address ;
    address += '/iMail/api/rest/token';
    const body = {
      grant_type: 'password',
      username: server.userAPI,
      password: 'test',
      redirect_uri: 'https://showroom2.novomind.com/freeboard/token.html'
    }
    console.log()
    const headers = {
      'Accept-Version': 1,
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/x-www-form-urlencoded',
      'Access-Control-Allow-Headers': 'origin, content-type, accept, authorization',
      'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
      'Authorization': 'Basic ' + Buffer.from(server.clientId +':' +server.secret).toString('base64'),
      'Access-Control-Allow-Credentials': true
    }
    console.log('body', body)
    console.log('headers', headers)

    return axios.create({headers: headers}).post(address, qs.stringify(body))
  }

}

