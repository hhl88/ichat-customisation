<template>
  <div class="demand-info-wrapper">
    <div class="header">
      Die folgenden Felder werden dem Kunden vor dem Chat-Begin zum Ausfüllen angezeigt
    </div>
    <div class="deman-info-list">
      <demand-info-list :demandInfoList="demandList" @onDemandInfoListChanged="getDemandInfo"/>
    </div>
    <button class="ml-3 my-5" @click="addNewDemandInfo">Feld hinzufügen</button>
  </div>
</template>

<script>

  import DemandInfoList from './DemandInfoList'

  export default {
    name: 'DemandInfoForm',
    components: {DemandInfoList},
    props: {
      demandList: {
        type: Array,
        default: () => []
      }
    },
    methods: {
      getDemandInfo: function (demandInfoList) {
        console.log('demand from List', demandInfoList)
        this.demandList = demandInfoList
        this.$emit('getDemandInfo', demandInfoList)
      },
      addNewDemandInfo () {
        this.demandList.push({field: '', explanation: '', required: false})
      }
    },

  }
</script>

<style scoped>

</style>

