<template>
  <div class="container-wrapper">
    <div v-if="view === 'frontend' && selectedItem">
      <ChatFrontEnd/>
    </div>
    <div v-if="view === 'layout' && selectedItem">
      <ChatLayout/>
    </div>
  </div>

</template>

<script>
  import ChatFrontEnd from '../chat-frontend/ChatFrontEnd'
  import ChatLayout from '../chat-layout/ChatLayout'
  import {SET_CURRENT_FRONT_END, SET_CURRENT_LAYOUT, SET_FRONT_END_LIST} from "../../../../constants/mutation.type";

  export default {
    name: 'IChatConfigurationContent',
    components: {ChatFrontEnd, ChatLayout},
    props: {
      view: {
        type: String,
        default: 'frontend'
      },
    },
    data() {
      return {
        selectedItem: null
      }
    },
    created() {
      this.$store.subscribe(((mutation, state) => {
        if (mutation.type === SET_CURRENT_FRONT_END) {
          this.selectedItem = this.$store.getters.currentChatFrontEnd;
        } else if (mutation.type === SET_CURRENT_LAYOUT) {
          this.selectedItem = this.$store.getters.currentChatLayout;
        }

      }));

    },


    // data () {
    //   return {
    //    /* server: {},
    //     step: 0,
    //     urlPath: '',
    //     demandInfoList: [],*/
    //   }
    // },
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
