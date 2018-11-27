<template>
  <div class="row mx-0 my-1"
       :class="{'justify-content-start' : !msg.isSystem && !msg.isAgent, 'justify-content-end': !msg.isSystem && msg.isAgent, 'justify-content-center': msg.isSystem }">
    <div class="col-auto msg-bubble"
         :class="{'my-msg' : !msg.isSystem && msg.isAgent, 'their-msg': !msg.isSystem && !msg.isAgent, 'system-msg': msg.isSystem}">
      <div class="message-date text-right">{{ msg.date }}</div>
      <div class="message">
        <span :v-if="msg.isSystem">
          <img src="../../../assets/info6.png">
        </span>
        {{ msg.message }} {{ msg.isSystem }}
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'SingleMessage',
    props: {
      msg: {
        type: Object,
        default: null
      }
    },
  }

</script>

<style scoped>
  .msg-bubble {
    padding: 8px 16px;
    border-radius: .8rem;
    max-width: 70%;
    word-wrap: break-word;
    margin-bottom: 5px;
  }

  .my-msg {
    background-color: #0084ff;
    margin-right: 10px;
    color: #fff;
  }

  .their-msg {
    background-color: #f1f0f0;
    margin-left: 10px;
  }

  .system-msg {
    background-color: #f1f0f0;
    font-style: italic;
  }

  .message-date {
    font-size: 8pt;
  }

</style>
