<template>
  <div>
    <div v-if="!isLoading" class="demand-preview">
      <NameInfo
        v-for="(nameInfo, index) in nameInfoList"
        :nameInfo="nameInfo"
        :key="nameInfo.name + index"
        :id="nameDefault + index"
        @click.native="onSelect(nameInfo)"
        @updateName="updateName"
      />
    </div>
  </div>

</template>

<script>
  import NameInfo from './NameInfo'
  import {
    SET_CURRENT_FRONT_END,
    SET_CURRENT_LAYOUT,
    UPDATE_ITEM_FRONT_END_LIST,
    UPDATE_ITEM_LAYOUT_LIST
  } from "../constants/mutation.type";

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
    data() {
      return {
        isLoading: false,
      }
    },
    mounted() {
    },
    methods: {
      onSelect(nameInfo) {
        this.$emit('changeItem', nameInfo)
      },
      updateName(name) {
        let item = this.$store.getters.getCurrentChatFrontEnd;
        console.log('updateName', item);

        if (item) {
          item.name = name;
          this.$store.commit(SET_CURRENT_FRONT_END, item);
          this.$store.commit(UPDATE_ITEM_FRONT_END_LIST, item);

        } else {
          item = this.$store.getters.getCurrentLayout;
          console.log('updateName', item);

          if (item) {
            console.log('update');
            item.name = name;
            this.$store.commit(SET_CURRENT_LAYOUT, item);
            this.$store.commit(UPDATE_ITEM_LAYOUT_LIST, item);
          }

        }
      }
    }
  }
</script>

<style scoped>

</style>
