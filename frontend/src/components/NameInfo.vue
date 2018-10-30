<template>
  <div class="row ml-3 mb-2 vertical-align">
    <div class="my-auto mx-4">
      {{ nameInfo.name }}
    </div>
  </div>
</template>

<script>
  export default {
    name: 'NameInfo',
    props: {
      nameInfo: {
        type: Object,
        default () {
          return {
            id: '',
            name: ''
          }
        }
      }
    }

  }
</script>

<style scoped>

</style>
