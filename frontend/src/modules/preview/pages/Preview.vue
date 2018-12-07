<template>
  <div>
    <div class="top-header">
      Header
    </div>
    <div class="main">
      <div class="button-group"
           v-if="!opened">
        <button class="open-button"
                @click.prevent="openChat()">
          Chat
        </button>

        <button class="popup-button"
                @click.prevent="openPopup()">
          Chat Popup
        </button>
      </div>
      <Chat class="chat-window"
            :class="isFixed ? 'chat-popup' : 'integrate'"
            id="chatWindow"
            ref="chatWindow"
            :is-pop-up="isPopUp"
            v-if="opened && !isPopUp"
            @closeChat="closeChat"/>
    </div>

    <div class="footer">
      <button @click.prevent="toggleChat">toggle chat</button>
    </div>

  </div>
</template>

<script>
  import Chat from '../components/Chat'

  export default {
    name: 'Preview',
    components: {
      Chat
    },
    data () {
      return {
        isPopUp: false,
        isFixed: true,
        opened: false,
        chatLayout: null
      }
    },
    methods: {
      openChat () {
        this.opened = true
        this.isPopUp = false
      },
      openPopup () {
        window.open('/chat', '', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=1024')
        this.isPopUp = true
      },
      closeChat () {
        this.opened = false
        this.isPopUp = false
      },
      toggleChat () {
        this.isFixed = !this.isFixed
        this.opened = !this.opened
      }
    }
  }
</script>

<style scoped>
  .top-header {
    position: fixed;
    top: 0;
  }

  .footer {
    position: fixed;
    bottom: 0;
  }

  .button-group {
    position: fixed;
    bottom: 0;
    right: 0;
    margin-right: 20px;
    margin-bottom: 20px;
  }


  .chat-window {
    margin-right: 20px;
    margin-bottom: 20px;
  }

  .chat-popup {
    z-index: 9;
    max-width: 450px !important;
    max-height: 600px !important;
    position: fixed;
    bottom: 0;
    right: 0;
  }

  .integrate {
    max-height: 900px !important;
    max-width: 1000px !important;
    resize: both;
  }

</style>
