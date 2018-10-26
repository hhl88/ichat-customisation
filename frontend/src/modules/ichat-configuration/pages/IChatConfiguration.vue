<template>
  <div class="container-wrapper row mt-5 mx-auto">
    <div class="col-2 sidebar">
      <Sidebar @changeView="changeView"/>
    </div>
    <div class="col-9 main-content text-left">
      <IChatConfigurationContent :view="view"/>
    </div>
  </div>
</template>

<script>
  import Sidebar from '../components/sidebar/Sidebar'
  import IChatConfigurationContent from '../components/main-content/IChatConfigurationContent'

  export default {
    name: 'Home',
    components: {Sidebar, IChatConfigurationContent},
    data() {
      return {
        view: ''
      }
    },
    mounted() {
      console.log(this.$parent.view)
    },
    methods: {
      changeView(params) {
        console.log(params)
        this.view = params
        history.pushState(null, '', '/ichat/'+params);
      }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    height: 90vh;
  }

  .sidebar {
    border-right: 1px solid black;
  }
</style>
