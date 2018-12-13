// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const serverAddr = 'localhost';

const serverUrl = 'http://' + serverAddr + ':9990';
const apiServerUrl = serverUrl + '/api/v1';
export const environment: any = {
  production: false,
  serverUrl: serverUrl,
  authApi: apiServerUrl + '/auth/me',
  tokenApi: serverUrl + '/oauth/token',
  userApi: apiServerUrl + '/users',
  resourceApi: serverUrl + '/r/u',
  apiServerUrl: apiServerUrl,
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
