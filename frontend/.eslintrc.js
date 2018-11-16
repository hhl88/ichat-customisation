// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',
    'ecmaVersion': 2017,
    'sourceType': 'module'
  },
  env: {
    browser: true
  },
  extends: [
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    //'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    'standard',
    'eslint:recommended',
    'plugin:vue/recommended'
  ],
  // required to lint *.vue files
  plugins: [
    'vue',
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    //'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'curly': ['error', 'all'],
    'comma-dangle': ['error', 'only-multiline'],
    'camelcase': ['error', {'properties': 'never'}],
    'import-spacing': true,
    'indent': 0,
    'require-default-prop': false,
    'prefer-arrow-callback': 2,

    //'indent': ['error', 4, {'SwitchCase': 1}],
/*    'object-curly-spacing': ['error', 'never'],
    'space-before-function-paren': ['error', 'never'],
    'space-before-blocks': ['error', 'always'],
    'space-infix-ops': ['error', {'int32Hint': false}],
    'no-alert': 'error',
    'no-dupe-args': 'error',
    'no-duplicate-case': 'error',
    'no-duplicate-imports': 'error',
    'no-empty': 'error',*/
    'max-len': 'off',
    'vue/attributes-order': 'off',
    'vue/max-attributes-per-line': 'off',
    'no-console': 'off',
    'max-line-length': 'off',
    'vue/require-prop-types': 'off',
    'vue/no-dupe-keys': 'off',
    'vue/attribute-hyphenation' : 'off',
    'no-unused-vars': 0,
    'curly': 0

  }
}
