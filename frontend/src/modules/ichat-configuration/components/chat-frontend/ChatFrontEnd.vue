<template>
  <div class="container-wrapper">
    <div v-if="step === 0">
      <label class="server-label header-label">iAgent System</label>
      <div class="server-setting settings">
        <server-setting @getServerSetting="getServerSetting"/>
      </div>
    </div>

    <div v-if="step === 0">
      <label class="path-label header-label mt-3 ">Frontend Settings</label>
      <div class="setting-path settings">
        <div class=" path-form ml-5 mx-0 mt-3 vertical-align">
          <div class="col-xl-2 col-lg-4 col-md-5 col-sm-5 col-xs-6">
            <label class="my-auto" for="urlPath">Name</label>
          </div>
          <div class="col">
            <input class="col-5" id="urlPath" type="text" v-model="urlPath"
                   placeholder="kundenservice">
          </div>
        </div>
      </div>
    </div>

    <div v-if="step === 1">
      <label class="demand-info-list header-label">Eröffnungsdialog</label>
      <div class="demand-info-form settings">
        <demand-info-form @getDemandInfo="getDemandInfo"/>
      </div>
    </div>

    <div class="button-group col-7  justify-content-around align-self-end">
      <button class="">Abrechen</button>
      <button @click="changeStep(0)" v-if="step === 1">Zurück</button>
      <button @click="changeStep(1)" v-if="step === 0">Weiter</button>
      <button @click.prevent="saveSetting()">Speichern</button>
    </div>

  </div>
</template>

<script>
  import ServerSetting from './server/ServerSetting'
  import DemandInfoForm from './deman-info/DemandInfoForm'
  import {CHAT_FRONTEND_CREATE, CHAT_FRONTEND_UPDATE} from "../../../../constants/action.type";
  import {
    ADD_ITEM_TO_FRONT_END_LIST,
    SET_CURRENT_FRONT_END,
    UPDATE_ITEM_FRONT_END_LIST
  } from "../../../../constants/mutation.type";

  export default {
    name: 'ChatFrontEnd',
    components: {DemandInfoForm, ServerSetting},
    data() {
      return {
        server: {},
        connectionType: '0',
        step: 0,
        urlPath: '',
        demandInfoList: [],
        frontEndSetting: null
      }
    },
    mounted() {
      this.getCurrentChatFrontEnd();
    },
    created() {
      this.$store.subscribe((mutation, state) => {
        if (mutation.type === SET_CURRENT_FRONT_END) {
          this.urlPath = '';
          this.getCurrentChatFrontEnd();
        }
      })
    },
    methods: {
      getCurrentChatFrontEnd() {
        this.frontEndSetting = this.$store.getters.currentChatFrontEnd;
        if (this.frontEndSetting) {
          this.urlPath = this.frontEndSetting.urlPath;
          this.step = 0;
        }
      },
      getServerSetting(data) {
        // console.log('data', data);
        this.connectionType = data.type;
        this.server = data.server;
      }
      ,
      getDemandInfo(demandInfoList) {
        this.demandInfoList = demandInfoList;
      }
      ,
      changeStep(step) {
        this.step = step
      }
      ,
      saveSetting() {
        const item = this.$store.getters.currentChatFrontEnd;

        item.iAgentServer = this.server;
        item.connectionType = this.connectionType;
        item.urlPath = this.urlPath;
        item.demandInfo = {
          demandInfoItems: this.demandInfoList
        };
        if (item.id) {
          this.$store
            .dispatch(CHAT_FRONTEND_UPDATE, item)
            .then(res => {
            })
        }
        else {
          this.$store
            .dispatch(CHAT_FRONTEND_CREATE, item)
            .then(res => {
              item.id = res.id;
              this.$store.commit(ADD_ITEM_TO_FRONT_END_LIST, item);
            })
        }
      }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    height: 100% !important;
    margin-top: 0.75em;
  }

  .settings {
    border: 1px solid black;
  }

  .header-label {
    margin-left: 1em;
    font-weight: bold;
  }

  .button-group {
    position: relative;
    bottom: 0;
  }

  .path-form {
    margin: 1em 0.5em 1em 1.5em;

  }
</style>
