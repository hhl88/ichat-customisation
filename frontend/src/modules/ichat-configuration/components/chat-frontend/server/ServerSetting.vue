<template>
  <div class="container-wrapper">
    <div>
      <input type="radio" id="agentServer" value="0" v-model="server.picked" :change="$emit('getServer', server)">
      <label for="agentServer">Eigenes iAgent System</label>
    </div>
    <div class="ml-4" v-if="server.picked === '0'">
      <form @submit.prevent="testIAgentServer(server)" :change="$emit('getServer', server)">
        <div class="row mx-0 mt-3 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="agentUrl">iAgent URL (Chat API)</label>
          </div>
          <div class="col">
            <input class="col-5" id="agentUrl" type="text" v-model="server.agentUrl"
                   placeholder="http://chat.novomind.com:5830">
          </div>
        </div>

        <div class="row mx-0 mt-2 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="userAPI">API Benutzer</label>
          </div>
          <div class="col">
            <input class="col-5" id="userAPI" type="text" v-model="server.userAPI" placeholder="chatuser">
          </div>
        </div>

        <div class="row mx-0 mt-2 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="password">Kennwort</label>
          </div>
          <div class="col">
            <input class="col-5" id="password" type="password" v-model="server.password" placeholder="Password">
          </div>
        </div>

        <div class="row mx-0 mt-2 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="clientID">Client ID</label>
          </div>
          <div class="col">
            <input class="col-5" id="clientID" type="text" v-model="server.clientID" placeholder="CHAT">
          </div>
        </div>

        <div class="row mx-0 mt-2 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="secret">Secret</label>
          </div>
          <div class="col">
            <input class="col-5" id="secret" type="text" v-model="server.secret" placeholder="CHAT">
          </div>
        </div>
        <button class="ml-3 mt-3 mb-5">Testen</button>
      </form>
    </div>
    <div>
      <input type="radio" id="cloudSystem" value="1" v-model="server.picked" :change="$emit('getServer', server)">
      <label for="cloudSystem">Cloud System</label>
    </div>
  </div>


</template>

<script>
  export default {
    name: 'ServerSetting',
    data () {
      return {
        server: {
          picked: '0',
          agentUrl: '',
          userAPI: '',
          password: '',
          clientID: '',
          secret: '',
        }
      }
    },
    methods: {
      testIAgentServer (server) {
        this.getServer(server)
      },
      getServer(server) {
        this.server = server
        this.$emit('getServer', server)
      }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    margin: 1em 0.5em 1em 1.5em;

  }
</style>
