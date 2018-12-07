<template>
  <div class="row mx-0 my-1"
       :class="{
       'justify-content-start' : !msg.isSystem && !msg.isAgent,
       'justify-content-end': !msg.isSystem && msg.isAgent,
       'justify-content-center': msg.isSystem
       }">
    <div class="col-auto msg-bubble"
         :class="{
         'my-msg' : !msg.isSystem && msg.isAgent,
         'their-msg': !msg.isSystem && !msg.isAgent,
         'system-msg': msg.isSystem}">
      <div class="row" v-if="!msg.isSystem">
        <div class="col-auto">{{ msg.name }}</div>
        <div class="col text-right">{{ msg.email }}</div>

      </div>
      <div class="message my-2 vertical-align">
        <span v-if="msg.isSystem">
          <img src="../../../assets/info6.png">
        </span>
        {{ msg.message }} {{ msg.isSystem }}
      </div>
      <div class="row">
        <div class="col-auto" v-if="!msg.isSystem">{{ msg.tel }}</div>
        <div class="col message-date text-right">{{ msg.date }}</div>
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
