<template>
  <div class="chat-window-wrapper" v-if="!isLoading"
       ref="chatWindowWrapper">
    <div class="logo-chat"
         ref="logoChat">
    </div>
    <div class="messenger-wrapper">
      <div class="messenger-body pt-3"
           ref="messengerBody"
           :style="{'order': chatLayout.textInputType === '0' ? 2 : 1}">
        <SingleMessage v-for="(message, index) in messages"
                       :msg="message"
                       :key="index"/>
      </div>
      <div class="input-area row mx-0 vertical-align"
           :class="chatLayout.buttonType === '0' ? 'in-row' : 'in-column'"
           ref="messengerFooter"
           :style="{'order': chatLayout.textInputType === '1' ? 1 : 2}">
        <textarea class="input-text"
                  :class="chatLayout.buttonType === '0' ? 'by-row' : 'by-side'"
                  placeholder="Write something..."
                  type="text"
                  @keyup.enter="sendMessage">
        </textarea>
        <div class="button-group"
             :class="chatLayout.buttonType === '0' ? 'by-row' : 'by-side'">
          <button class="send-message-button"
                  :class="chatLayout.buttonType === '0' ? 'by-side' : 'by-row'"
                  @click="sendMessage">
            Send
          </button>
          <button class="send-message-button"
                  :class="chatLayout.buttonType === '0' ? 'by-side' : 'by-row'"
                  @click="closeChat">
            Close
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import SingleMessage from './SingleMessage'
  import {GET_CHAT_LAYOUT} from '../../../constants/action.type'
  import {SET_LAYOUT} from '../../../constants/mutation.type'

  export default {
    name: 'Chat',
    components: {
      SingleMessage
    },
    data() {
      return {
        messages: [],
        chatLayout: null,
        isLoading: true
      }
    },
    updated() {
      this.$nextTick(this.changeStyle)
    },
    mounted() {
      this.loadData()
    },
    created() {
      this.$store
        .dispatch(GET_CHAT_LAYOUT, 'HRqm0IlqKG')
        .then((res) => {
        })

      this.messages = []
      this.messages.push({
        message: 'Kunden ist dem chat beigetreten',
        date: '27.11.2018 16:18:41',
        isSystem: true,
        isAgent: false
      })
      this.messages.push({
        message: 'Agent A ist dem chat beigetreten',
        date: '27.11.2018 16:19:00',
        isSystem: true,
        isAgent: false
      })

      this.messages.push({
        email: 'agent@email.com',
        tel: '9876543210',
        name: 'Agent A',
        message: 'Willkommen Mein Name, wie kann ich Ihnen helfen? Willkommen Mein Name, wie kann ich Ihnen helfen?Willkommen Mein Name, wie kann ich Ihnen helfen ?Willkommen Mein Name, wie kann ich Ihnen helfen? Willkommen Mein Name, wie kann ich Ihnen helfen?',
        date: '27.11.2018 16:19:05',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        email: 'kunden@email.com',
        tel: '0123456789',
        name: 'Kunden A',
        message: 'hallo, mein Laptop funktioniert nicht mehr',
        date: '27.11.2018 16:19:45',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        email: 'kunden@email.com',
        tel: '0123456789',
        name: 'Kunden A',
        message: 'Der Bildschirm zeigt gar nichts',
        date: '27.11.2018 16:19:55',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        email: 'agent@email.com',
        tel: '9876543210',
        name: 'Agent A',
        message: 'Könnten Sie bitte Akku überprüfen?',
        date: '27.11.2018 16:20:10',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        email: 'kunden@email.com',
        tel: '0123456789',
        name: 'Kunden A',
        message: 'Moment bitte',
        date: '27.11.2018 16:20:30',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        email: 'kunden@email.com',
        tel: '0123456789',
        name: 'Kunden A',
        message: 'Oh, Kein Akku mehr. ich habe vergessen, kabel anzuschließen',
        date: '27.11.2018 16:21:00',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        email: 'agent@email.com',
        tel: '9876543210',
        name: 'Agent A',
        message: 'Dann funktioniert Ihr Laptop wieder.',
        date: '27.11.2018 16:21:30',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        email: 'kunden@email.com',
        tel: '0123456789',
        name: 'Kunden A',
        message: 'Danke, schönen Tag noch',
        date: '27.11.2018 16:22:00',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        email: 'agent@email.com',
        tel: '9876543210',
        name: 'Agent A',
        message: 'Danke, ebenfalls',
        date: '27.11.2018 16:22:20',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        message: 'Der Chat wurde von "Agent A" beendet',
        date: '27.11.2018 16:22:30',
        isSystem: true,
        isAgent: false
      })
      this.messages.push({
        message: 'Der Chat wurde von "Kunden" beendet',
        date: '27.11.2018 16:23:00',
        isSystem: true,
        isAgent: false
      })

    },
    methods: {
      loadData() {
        this.$store.subscribe(((mutation, state) => {
          if (mutation.type === SET_LAYOUT) {
            this.chatLayout = this.$store.getters.selectedChatLayout
            this.isLoading = false
          }
        }))
      },
      changeStyle() {
        this.setDimension()
        this.setImage(this.chatLayout.logo, 'logoChat')
        this.setImage(this.chatLayout.backgroundImg, 'messengerBody')
        this.setImageStyle(this.chatLayout.backgroundType, 'messengerBody')
        this.setFont(this.chatLayout.font, 'chatWindowWrapper')
      },
      sendMessage() {
      },
      closeChat() {
        this.$emit('closeChat', null)
      },
      setImage(img, refName) {
        this.$refs[refName].style.backgroundImage = 'url(' + img + ')'
      },
      convertStyle(numStyle) {
        switch (numStyle) {
          case '0':
            return 'bold'
          case '1':
            return 'italic'
          case '2':
            return 'underline'
          default:
            return ''
        }
      },
      convertImageStyle(numStyle) {
        switch (numStyle) {
          case '0':
            return 'cover'
          case '1':
            return 'contain'
          default:
            return 'auto'
        }
      },
      setImageStyle(numStyle, refName) {
        this.$refs[refName].style.backgroundSize = this.convertImageStyle(numStyle)
      },
      setFont(font, refName) {
        let fontStyle = ''
        let isUnderline = false
        for (let numStyle of font.fontStyles) {
          const style = this.convertStyle(numStyle)
          if (style !== 'underline') {
            fontStyle += style + ' '
          } else {
            isUnderline = true
          }
        }
        this.$refs[refName].style.font = fontStyle + font.fontSize + 'pt ' + font.fontFamily
        if (isUnderline) {
          this.$refs[refName].style.textDecoration = 'underline'
        }
      },
      setDimension() {
        let chatWindow = document.getElementById('chatWindow')
        let maxHeightWrapper = window.innerHeight;
        if (chatWindow) {
          chatWindow = window.getComputedStyle(chatWindow)
        }
        const logoChat = window.getComputedStyle(this.$refs['logoChat'])
        const messengerFooter = window.getComputedStyle(this.$refs['messengerFooter'])

        const heightLogo = +logoChat.height.substr(0, logoChat.height.indexOf('p'))
        const heightFooter = +messengerFooter.height.substr(0, messengerFooter.height.indexOf('p'))
        if (chatWindow) {
          maxHeightWrapper = +chatWindow.maxHeight.substr(0, chatWindow.maxHeight.indexOf('p'))
        }
        console.log('maxHeightWrapper', maxHeightWrapper, heightLogo, heightFooter)
        this.$refs.messengerBody.style.maxHeight = (maxHeightWrapper - heightLogo - heightFooter) + 'px'


      }
    }
  }
</script>

<style scoped>
  .chat-window-wrapper {
    border: 1px solid black !important;
    border-radius: 5px;
  }

  .messenger-wrapper {
    display: flex;
    flex-direction: column;
  }

  .logo-chat {
    width: 100%;
    height: 100px;
    background-repeat: no-repeat;
    background-position: center;
    background-color: #FFF;
    border-bottom: 1px solid;
    position: sticky;
    z-index: 2;
    margin: 0 auto;
    right: 0;
    border-radius: 5px 5px 0 0;
  }

  .input-text {
    border-color: black !important;
    resize: none;
    border-width: 1px 0px 1px 0px !important;
    border-style: solid solid solid solid !important;
    padding: 9px 12px;
  }

  input-text:focus {
    overflow: auto;
    outline: none;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
  }

  textarea[class*="by-row"] {
    width: 100% !important;
  }

  textarea[class*="by-side"] {
    width: 70% !important;
    height: 95% !important;
    border-width: 1px 1px 0px 0px !important;
    border-style: solid solid solid solid !important;
    box-sizing: border-box;
    border-radius: 0 0 0 5px;
  }

  .messenger-body {
    position: relative;
    overflow-y: auto;
    background-repeat: no-repeat;
    background-position: center;
  }

  .send-message-button {
    border: none;
    border-radius: 0;
  }

  .send-message-button:hover {
    cursor: pointer;
  }

  .input-text {
    border: none;
  }

  .button-group {
    display: flex;
    justify-content: space-around;
    width: 100%;

  }

  div[class*="by-row"] {
    width: 100%;
    margin: 10px 0;
  }

  div[class*="by-side"] {
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 30%;
    align-items: center;
  }

  button {
    background-color: orangered;
    color: white;
  }

  button[class*="by-side"] {
    width: 100px;
    height: 35px;
  }

  button[class*="by-row"] {
    width: 100px;
    height: 27px;
  }

  .in-column {
    height: 80px;
    padding-top: 10px;
  }


</style>
