<template>
  <div class="sidebar-wrapper mt-3">
    <div class="sidebar-header">
      <span>Benutzerverwaltung</span>
    </div>
    <div class="body">
      <div class="chat-layout list-header">
        <a class="header" @click="onSelectLayout()">
          <span>Layout</span>
        </a>

      </div>

      <div class="chat-frontend list-header">
        <a class="header" @click="onSelectFrontend()">
          <span>Chat-Frontends</span>
        </a>

      </div>

      <div class="chat-layout list-header">
        <a class="header">
          <span>Passwort ändern</span>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Sidebar',
    methods: {
      onSelectLayout() {
        this.$emit('changeView', 'layout')
      },
      onSelectFrontend() {
        this.$emit('changeView', 'frontend')

      },
      onChangeView(type, index) {

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

  a[class="header"] {
    width: 100%;
    display: block;
    vertical-align: middle;
    align-items: center;
    line-height: 2em;
  }
  a[class="header"] span {
    margin-left: 0.5em;
  }

  a:hover {
    background-color: aqua;
  }

  a:active {
    background-color: red;
  }
</style>
