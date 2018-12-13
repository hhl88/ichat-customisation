<template>
  <form :change="$emit('onDemandInfoChanged', demandInfo)">
    <div class="row ml-3 mt-3 vertical-align">
      <input class="col-2 my-auto mx-4" type="text" v-model="demandInfo.name">
      <input class="col-3" type="text" v-model="demandInfo.example">
      <input class="mx-3" type="checkbox" id="checkbox" v-model="demandInfo.isRequired">
      <label class="my-auto" for="checkbox">Pflicht</label>
    </div>
  </form>
</template>

<script>
  export default {
    name: 'DemandInfo',
    props: {
      demandInfo: {
        type: Object,
        default () {
          return {
            name: '',
            example: '',
            isRequired: false
          }
        }
      },
    },
    mounted() {
    }

  }
</script>

<style scoped>

</style>
