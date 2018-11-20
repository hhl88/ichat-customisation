<template>
  <div class="row my-2 mx-0 mb-2 vertical-align name-wrapper" :id="id">
    <div v-show="!edit">
      <label @dblclick="edit = true" class="my-auto ml-4">
        {{ nameInfo.name }}
      </label>
    </div>
    <input class="my-auto ml-4 w-100  edit-name"
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
        default () {
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
    data () {
      return {
        edit: false,
        name: this.nameInfo.name
      }
    },
    methods: {
      updateVal () {
        this.edit = false
        this.$emit('updateName', this.name)
      }
    }

  }
</script>

<style scoped>
  .name-wrapper:hover {
    cursor: pointer;
  }

  .name-wrapper:focus {
    cursor: pointer;
  }

  .edit-name {

  }
</style>
