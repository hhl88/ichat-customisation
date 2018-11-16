<template>
  <div class="row mx-0 mb-2 vertical-align name-wrapper" :id="id">
    <div v-show="!edit">
      <label @dblclick="edit = true" class="my-auto mx-4">
        {{ nameInfo.name }}
      </label>
    </div>
    <input
      v-if="edit === true"
      v-model="name"
      @blur="updateVal"
      @keyup.enter="updateVal">

  </div>
</template>

<script>
  export default {
    name: 'NameInfo',
    props: {
      nameInfo: {
        type: Object,
        default() {
          return {
            id: '',
            name: '',
          }
        }
      },
      id: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        edit: false,
        name: this.nameInfo.name
      };
    },
    methods: {
      updateVal() {
        this.edit = false;
        console.log('nbam', this.name);
        this.$emit('updateName', this.name);
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
