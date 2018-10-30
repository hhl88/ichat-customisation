<template>
  <div class="row mx-0 mb-2 vertical-align name-wrapper" :id="id">
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
      },
      id: {
        type: String,
        default: ''
      }
    }

  }
</script>

<style scoped>
  .name-wrapper:hover {
    cursor: pointer;
    background-color: darkorange;
    color: white;
  }
  .name-wrapper:focus {
    cursor: pointer;
    background-color: darkorange;
    color: white;
  }
</style>
