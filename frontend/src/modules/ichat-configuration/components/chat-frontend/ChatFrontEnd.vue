<template>
  <div class="container-wrapper">
    <div v-if="step === 0">
      <label class="server-label header-label">iAgent System</label>
      <div class="server-setting settings">
        <server-setting :server="server" @getServer="getServer"/>
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
        <demand-info-form :demand-list="demandInfoList" @getDemandInfo="getDemandInfo"/>
      </div>
    </div>

    <div class="button-group col-7 d-flex justify-content-around">
      <button class="">Abrechen</button>
      <button @click="changeStep(0)" v-if="step === 1">Zurück</button>
      <button @click="changeStep(1)" v-if="step === 0">Weiter</button>
      <button>Speichern</button>
    </div>
  </div>
</template>

<script>
  import ServerSetting from './server/ServerSetting'
  import DemandInfoForm from './deman-info/DemandInfoForm'

  export default {
    name: 'ChatFrontEnd',
    components: {DemandInfoForm, ServerSetting},
    props: {
      view: {
        type: String,
        default: 'frontend'
      }
    },
    data () {
      return {
        server: {},
        step: 0,
        urlPath: '',
        demandInfoList: [],
      }
    },
    methods: {
      getServer: function (server) {
        console.log('server', server)
        this.server = server
      },
      getDemandInfo: function (demandInfoList) {
        console.log('demand info', demandInfoList)
        this.demandInfoList = demandInfoList
      },
      changeStep (step) {
        this.step = step
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
    position: absolute;
    bottom: 0;
  }

  .path-form {
    margin: 1em 0.5em 1em 1.5em;

  }
</style>
