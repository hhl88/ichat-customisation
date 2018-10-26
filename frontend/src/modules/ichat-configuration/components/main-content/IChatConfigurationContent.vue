<template>
  <div class="container-wrapper">
    <div v-if="view === 'frontend' ">
      <ChatFrontEnd/>
    </div>
    <div v-if="view === 'layout' ">
      <ChatLayout/>
    </div>
  </div>

</template>

<script>
  import ChatFrontEnd from '../chat-frontend/ChatFrontEnd'
  import ChatLayout from '../chat-layout/ChatLayout'

  export default {
    name: 'IChatConfigurationContent',
    components: {ChatFrontEnd, ChatLayout},
    props: {
      view: {
        type: String,
        default: 'frontend'
      }
    },
    data () {
      return {
       /* server: {},
        step: 0,
        urlPath: '',
        demandInfoList: [],*/
      }
    },
    methods: {
     /* getServer: function (server) {
        console.log('server', server)
        this.server = server
      },
      getDemandInfo: function (demandInfoList) {
        console.log('demand info', demandInfoList)
        this.demandInfoList = demandInfoList
      },
      changeStep (step) {
        this.step = step
      }*/

    }
  }
</script>

<style scoped>

</style>
