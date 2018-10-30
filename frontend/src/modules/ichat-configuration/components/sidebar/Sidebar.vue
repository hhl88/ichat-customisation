<template>
  <div class="sidebar-wrapper mt-3">
    <div class="sidebar-header">
      <span>Benutzerverwaltung</span>
    </div>
    <div class="body">
      <div class="chat-layout list-header row mx-0 vertical-align">
        <div class="header col-10" @click="onSelectLayout">
          Layout
        </div>
        <div class="col-2 px-0 text-center">
          <button type="button" class="add-button" @click="addFrontEnd">
            +
          </button>
        </div>
      </div>
      <NameInfoList :name-info-list="chatLayoutList"/>

      <div class="chat-frontend list-header row mx-0 vertical-align">
        <div class="header col-10" @click="onSelectFrontend">
          Chat-Frontends
        </div>
        <div class="col-2 px-0 text-center">
          <button class="add-button" @click="addFrontEnd">
            +
          </button>
        </div>
      </div>
      <NameInfoList :name-info-list="chatFrontEndList"/>


      <div class="chat-layout list-header">
        <div class="header col">
          <span>Passwort ändern</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import NameInfoList from '../../../../components/NameInfoList'

  export default {
    name: 'Sidebar',
    components: {
      NameInfoList
    },
    props: {
      chatFrontEndList: {
        type: Array,
        default: () => []
      },
      chatLayoutList: {
        type: Array,
        default: () => []
      }
    },
    methods: {
      onSelectLayout () {
        this.$emit('changeView', 'layout')
      },
      onSelectFrontend () {
        console.log('changeview')
        this.$emit('changeView', 'frontend')
      },
      addFrontEnd () {
        console.log('chatFrontend', this.chatFrontEndList, this.chatFrontEndList == null, typeof this.chatFrontEndList === 'undefined');
        console.log('chatFrontend', this.chatFrontEndList);

        if (this.chatFrontEndList == null) {
          this.chatFrontEndList = []
        }
        this.chatFrontEndList.push({id: null, name: 'FrontEnd'})
      },
      onChangeView (type, index) {
      }
    }
  }
</script>

<style scoped>
  .sidebar-wrapper {
    text-align: left;
  }

  .sidebar-header {
    border: 1px solid black;
    line-height: 2em;
  }

  .sidebar-header span {
    margin-left: 0.5em;

  }

  .list-header {
    border: 1px solid black;
  }

  .header {
    vertical-align: middle;
    align-items: center;
    line-height: 2em;
  }

  .header:hover {
    background-color: aqua;
    cursor: pointer;
  }

  .header:active {
    background-color: red;
  }

  .add-button {
    width: 30px;
    height: 30px;
    border-radius: 15px;
    font-size: 18px;
    line-height: 1.42857;
  }

  .add-button:focus {
    outline: 0;
  }

  .add-button:active {
    background-color: darkorange;
    outline: 0;
  }


</style>
