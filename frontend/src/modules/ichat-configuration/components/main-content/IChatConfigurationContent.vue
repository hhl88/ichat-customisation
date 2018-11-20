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
  import {SET_CURRENT_FRONT_END, SET_CURRENT_LAYOUT} from "../../../../constants/mutation.type";

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

    }
  }
</script>

<style scoped>

</style>
