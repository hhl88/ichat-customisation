<template>
  <div class="container-wrapper">
    <div>
      <input type="radio" id="agentServer" value="0" v-model="type"
             :change="serverSettingChange(type, server)">
      <label for="agentServer">Eigenes iAgent System</label>
    </div>
    <div class="ml-4" v-if="type === '0'">
      <form :change="serverSettingChange(type, server)">
        <div class="row mx-0 mt-3 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6  align-self-center">
            <label class="my-auto" for="address">iAgent URL (Chat API)</label>
          </div>
          <div class="col">
            <input class="col-5" id="address" type="text" v-model="server.address"
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
            <input class="col-5" id="clientID" type="text" v-model="server.clientId" placeholder="CHAT">
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
        <button class="ml-3 mt-3 mb-5" @click.prevent="testIAgentServer">
          <span>Testen</span>



        </button>
        <img v-show="isClicked && isConnected" src="../../../../../assets/not_ok.svg">

        <img v-show="isClicked && !isConnected" src="../../../../../assets/not_ok.svg">
        <span v-if="isClicked">If Clicked</span>
        <span v-if="!isClicked">If not Clicked</span>

      </form>
    </div>
    <div>
      <input type="radio" id="cloudSystem" value="1" v-model="type"
             :change="serverSettingChange(type, server)">
      <label for="cloudSystem">Cloud System</label>
    </div>
  </div>


</template>

<script>
  import {SET_CURRENT_FRONT_END} from "../../../../../constants/mutation.type";
  import {FETCH_IAGENT_SERVER} from '../../../../../constants/action.type'

  export default {
    name: 'ServerSetting',
    data() {
      return {
        server: {
          address: '',
          userAPI: '',
          password: '',
          clientId: '',
          secret: ''
        },
        type: '0',
        isConnected: false,
        isClicked: false
      }
    },

    mounted() {
      this.getCurrentSettingServer();
    },
    updated () {

      this.$nextTick(this.updateValue)
      console.log('updated run', 'isConnected', this.isConnected, 'isClicked', this.isClicked)

    },
    created() {
      this.$store.subscribe((mutation, state) => {
        if (mutation.type === SET_CURRENT_FRONT_END) {
          this.type = '0';
          this.server = {
            address: '',
            userAPI: '',
            password: '',
            clientId: '',
            secret: ''
          };
          this.getCurrentSettingServer()
        }
      })
    },
    methods: {
      updateValue() {
        this.isConnected = this.isConnected
        this.isClicked = this.isClicked
        console.log('updateValue ', 'isConnected', this.isConnected, 'isClicked', this.isClicked)
      },
      serverSettingChange(connectionType, server) {
        this.isClicked = false
        this.isConnected = false;
        this.$emit('getServerSetting', {connectionType, server});
      },
      getCurrentSettingServer() {
        const selectedChatFrontEnd = this.$store.getters.currentChatFrontEnd;
        if (selectedChatFrontEnd.id) {
          this.server = selectedChatFrontEnd.iAgentServer;
          this.type = selectedChatFrontEnd.connectionType;
        }
      },
      testIAgentServer() {
        this.isClicked = true;
        this.isConnected = false
        this.$store.dispatch(FETCH_IAGENT_SERVER, this.server).then(res => {
            if(res && res.hasOwnProperty('access_token')) {
              this.isConnected = true
            } else {
              this.isConnected = false
            }
            console.log('isConnected', this.isConnected, 'isClicked', this.isClicked)
        });
      },
      // getServerSetting({connectionType, server}) {
      //   this.connectionType = connectionType;
      //   this.iAgentServer = server;
      //   this.$emit('getServerSetting', {connectionType, server});
      // }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    margin: 1em 0.5em 1em 1.5em;
  }
</style>
