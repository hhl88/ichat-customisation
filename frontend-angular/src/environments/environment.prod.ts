// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.



// const serverUrl = 'http://141.22.75.70:8090';
const serverUrl = 'http://localhost:8090';
// const serverUrl = 'http://141.22.65.139:8090';
const resourceServerUrl = serverUrl;
const apiServerUrl = serverUrl + '/api/v1';
export const environment: any = {
  production: false,
  // apiServerUrl: 'http://ec2-18-217-130-50.us-east-2.compute.amazonaws.com:8090/api/v1/'
  serverUrl: serverUrl,
  tripApi: apiServerUrl + '/trips',
  authApi: apiServerUrl + '/auth',
  tokenApi: apiServerUrl + '/auth',
  searchApi: apiServerUrl + '/search',
  userApi: apiServerUrl + '/users',
  googleApi: apiServerUrl + '/geo/autocompletes',
  resourceApi: serverUrl + '/r/u',
  requestedTripApi: apiServerUrl + '/request',
  supportedServicesApi: apiServerUrl + '/supported_services',
  parcelApi: apiServerUrl + '/parcels'

};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
