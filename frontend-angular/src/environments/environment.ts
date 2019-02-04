// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

//const serverAddr = '62.158.55.204';
const serverAddr = 'api.sheeporo.com';
// const serverAddr = 'sheeporo.test:8000';

const serverUrl = 'https://' + serverAddr;
const mqttBroker = 'wss://' + serverAddr + ':3001';
const resourceServerUrl = serverUrl;
const apiServerUrl = serverUrl + '/api/v1';
export const environment: any = {
  production: false,
  // apiServerUrl: 'http://ec2-18-217-130-50.us-east-2.compute.amazonaws.com:8090/api/v1/'
  serverUrl: serverUrl,
  tripApi: apiServerUrl + '/trips',
  authApi: apiServerUrl + '/auth/me',
  settingApi: apiServerUrl + '/my_settings',
  tokenApi: apiServerUrl + '/auth',
  searchApi: apiServerUrl + '/search-trip',
  userApi: apiServerUrl + '/users',
  googleApi: apiServerUrl + '/geo/autocompletes',
  resourceApi: serverUrl + '/r/u',
  apiServerUrl: apiServerUrl,
  supportedServicesApi: apiServerUrl + '/supported_services',
  parcelApi: apiServerUrl + '/parcels',
  mqttBroker: mqttBroker,
  chatServer: serverUrl,
  chatApi: serverUrl + '/api/v1/requests'
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
