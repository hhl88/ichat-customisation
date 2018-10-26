<template>
  <div>
    <div v-if="!isLoading" class="demand-preview">
      <DemandInfo
        v-for="(demandInfo, index) in demandInfoList"
        :demandInfo="demandInfo"
        :key="demandInfo.field + index"
      />
    </div>
  </div>

</template>

<script>
  import DemandInfo from './DemandInfo';

  export default {
    name: 'DemandInfoList',
    components: {
      DemandInfo
    },
    props: {
      demandInfoList: {
        type: Array,
        default: () => []
      }
    },
    data() {
      return {
        isLoading: false,
      }
    },
    mounted() {
      //this.fetchDemandInfoList();
    },
    methods: {
      // fetchDemandInfoList() {
      //   this.$store.dispatch(FETCH_ARTICLES, this.listConfig);
      // },
      resetPagination() {
        this.listConfig.offset = 0;
        this.currentPage = 1;
      },
      onDemandInfoListChanged(demandInfoList) {
        this.demandInfoList = demandInfoList
        this.$emit('onDemandInfoListChanged', demandInfoList)
      }
    }
  }
</script>

<style scoped>

</style>
