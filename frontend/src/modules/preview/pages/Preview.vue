<template>

  <div class="messenger-wrapper">
    <div class="logo-chat"/>
    <div class="messenger-scroll">
      <div class="messenger-body">
        <div class="messenger-content pt-3">
          <div class="text-center request-created-info">
            CHAT
          </div>
          <SingleMessage v-for="(message, index) in messages"
                         :msg="message"
                         :key="index"/>

        </div>
      </div>
    </div>
    <div class="input-area row no-gutters mx-0 vertical-align">
      <textarea class="input-text w-100 h-100"
                placeholder="Write something..."
                type="text"
                @keyup.enter="sendMessage"/>
      <a class="send-message-button col-auto px-3 text-center" @click="sendMessage">
        <div>Send</div>
      </a>
    </div>
  </div>

</template>

<script>
  import SingleMessage from '../components/single-message'

  export default {
    name: 'Preview',
    components: {
      SingleMessage
    },
    data () {
      return {
        messages: []
      }
    },
    created () {
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
        message: 'Willkommen Mein Name, wie kann ich Ihnen helfen?Willkommen Mein Name, wie kann ich Ihnen helfen?Willkommen Mein Name, wie kann ich Ihnen helfen?Willkommen Mein Name, wie kann ich Ihnen helfen?Willkommen Mein Name, wie kann ich Ihnen helfen?',
        date: '27.11.2018 16:19:05',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        message: 'hallo, mein Laptop funktioniert nicht mehr',
        date: '27.11.2018 16:19:45',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        message: 'Der Bildschirm zeigt gar nichts',
        date: '27.11.2018 16:19:55',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({
        message: 'Könnten Sie bitte Akku überprüfen?',
        date: '27.11.2018 16:20:10',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({message: 'Moment bitte', date: '27.11.2018 16:20:30', isSystem: false, isAgent: false})
      this.messages.push({
        message: 'Oh, Kein Akku mehr. ich habe vergessen, kabel anzuschließen',
        date: '27.11.2018 16:21:00',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        message: 'Dann funktioniert Ihr Laptop wieder.',
        date: '27.11.2018 16:21:30',
        isSystem: false,
        isAgent: true
      })
      this.messages.push({
        message: 'Danke, schönen Tag noch',
        date: '27.11.2018 16:22:00',
        isSystem: false,
        isAgent: false
      })
      this.messages.push({message: 'Danke, ebenfalls', date: '27.11.2018 16:22:20', isSystem: false, isAgent: true})
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
      sendMessage () {
        console.log('asdad')
      }
    }
  }
</script>

<style scoped>
  .messenger-wrapper {
  }

  .logo-chat {
    width: 100%;
    height: 100px;
    background-image: url(../../../assets/novomind_logo.png);
    background-repeat: no-repeat;
    background-position: center;
    background-color: #FFF;
    border-bottom: 1px solid;
    position: absolute;
    left: -20px;
    right: 0;
    margin: 0 auto;
  }

  .input-area {
    border: 1px solid rgba(0, 0, 0, .10);
    height: 80px;
  }

  textarea {
    padding: 9px 12px;
  }

  textarea:focus {
    border: none;
    overflow: auto;
    outline: none;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
  }

  .messenger-scroll {
    margin-right: -30px;
    padding-right: 30px;
  }

  .messenger-body {
    position: relative;
    height: calc(100vh - 80px);
    overflow-y: auto;
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
  / / overflow-: break-word;
  }

  .request-created-info {
    font-style: italic;
    font-size: .8rem;
    color: #ccc;
  }

  .select-request-hint {
    color: #ddd;
  }
</style>
