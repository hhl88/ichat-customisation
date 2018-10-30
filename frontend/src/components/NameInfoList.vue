<template>
  <div>
    <div v-if="!isLoading" class="demand-preview">
      <NameInfo
        v-for="(nameInfo, index) in nameInfoList"
        :nameInfo="nameInfo"
        :key="nameInfo.name + index"
        :id ="nameDefault + index"
        @click.native="onSelect(nameInfo)"
      />
    </div>
  </div>

</template>

<script>
  import NameInfo from './NameInfo'

  export default {
    name: 'NameInfoList',
    components: {
      NameInfo
    },
    props: {
      nameInfoList: {
        type: Array,
        default: () => []
      },
      nameDefault: {
        type: String,
        default: ''
      }
    },
    data () {
      return {
        isLoading: false,
      }
    },
    mounted () {
    },
    methods: {
      onSelect (nameInfo) {
        console.log('nameInfo')
        this.$emit('changeItem', nameInfo)
      }
    }
  }
</script>

<style scoped>

</style>
