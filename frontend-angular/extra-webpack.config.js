const path = require('path');
// This helper function is not strictly necessary.
// I just don't like repeating the path.join a dozen times.
function srcPath(subdir) {
  return path.join(__dirname, "src", subdir);
}
module.exports = {
  resolve: {
    alias: {
      actions: srcPath('app/actions'),
      selectors: srcPath('app/selectors'),
      ui: srcPath('app/ui'),
      logger: srcPath('util/logger'),
    },
    // ...
  },
  // ...
};
