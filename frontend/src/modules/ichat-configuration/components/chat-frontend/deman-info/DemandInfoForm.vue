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
  import {SET_CURRENT_FRONT_END} from "../../../../../constants/mutation.type";

  export default {
    name: 'DemandInfoForm',
    components: {DemandInfoList},
    data() {
      return {
        demandList: []
      }
    },
    mounted() {
      this.getCurrentDemandList();
    },
    created() {
      this.$store.subscribe((mutation, state) => {
        if (mutation.type === SET_CURRENT_FRONT_END) {
          this.getCurrentDemandList();
        }
      })
    },
    methods: {
      getCurrentDemandList() {
        const selectedChatFrontEnd = JSON.parse(JSON.stringify(this.$store.getters.currentChatFrontEnd));
        if (selectedChatFrontEnd) {
          if (selectedChatFrontEnd.demandInfo) {
            this.demandList = selectedChatFrontEnd.demandInfo.demandInfoItems;
          }
        }
      },
      getDemandInfo: function (demandInfoList) {
        console.log('demand from List', demandInfoList)
        this.demandList = demandInfoList
        this.$emit('getDemandInfo', demandInfoList)
      },
      addNewDemandInfo() {
        this.demandList.push({field: '', explanation: '', required: false})
      }
    },

  }
</script>

<style scoped>

</style>

