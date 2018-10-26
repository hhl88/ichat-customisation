<template>
  <div>
    <input type="file"
           accept="image/*"
           @change="onFileSelected">
  </div>
</template>

<script>
  export default {
    name: 'ImageUploader',
    data(){
     return {
       selectedFile: null,
     }
    },
    methods: {
      onFileSelected(event) {
        this.selectedFile = event.target.files[0]
        this.$emit('imageUploader', this.selectedFile)
      }
    }
  }
</script>

<style scoped>
  input {
    border: none;
  }
</style>
