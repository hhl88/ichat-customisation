<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data () {
      return {
        view: '',
      }
    },
    mounted () {
    },

  }
</script>

<style>
  html {
    padding-bottom: 0;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    /*height: 100%;*/
  }

  .vertical-align {
    display: flex;
    align-items: center;
  }

  button {
    border-radius: 3px;
    border: 1px solid black;
    box-shadow: 0 0 0.75rem #e4e4e4;
  }

  input {
    border-radius: 3px;
    border: 1px solid black;
    transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
  }
</style>
