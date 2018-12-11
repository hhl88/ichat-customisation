<template>
  <div>
    <div class="top-header">
      Header
    </div>
    <div class="main mt-4">
      <div class="row">
        <div class="col-4">
          asdas
        </div>
        <div class="col-8">
          <Chat class="chat-window"
                :class="isFixed ? 'chat-popup' : 'integrate'"
                id="chatWindow"
                ref="chatWindow"
                :is-pop-up="isPopUp"
                v-if="opened && !isPopUp"
                @closeChat="closeChat"/>
        </div>
      </div>
      <div class="button-group"
           v-if="!opened">
        <button class="popup-button"
                v-if="displayType === '2'"
                @click.prevent="openPopup()">
          Chat Popup
        </button>
      </div>

    </div>

    <div class="footer">
    </div>

  </div>
</template>

<script>
  import Chat from '../components/Chat'
  import {GET_CHAT_LAYOUT} from '../../../constants/action.type'


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
        displayType: 0
      }
    },
    mounted () {
    },
    created() {
      this.getChatLayout()
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
      },
      getChatLayout() {
        this.$store
          .dispatch(GET_CHAT_LAYOUT, 'kzJ3mf0ABY')
          .then((res) => {
            console.log(res)
            this.displayType = res.displayType
            if(this.displayType === '0') {
              this.opened = true
              this.isFixed = false

            } else if (this.displayType === '1') {
              this.isFixed = true
              this.opened = true

            }
          })
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
