<template>

</template>

<script>
  import Login from 'login/Login';
  import Registration from 'registration/Registration';

  export default {
    name: 'Entry',
    components: {
      'login': Login,
      'registration': Registration,
    },
    methods: {
      swapComponent: function(component) {
        this.currentComponent = component;
      }
    }
  }
</script>

<style scoped>

</style>
