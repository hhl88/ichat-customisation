<template>
  <div>
    <b-form-file v-model="file"
                 accept="image/*"
                 placeholder="Choose a file..."
                 @change="onFileSelected"/>
    <!--<input type="file"-->
           <!--accept="image/*"-->
           <!--@change="onFileSelected">-->
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
        const file = event.target.files[0];
        if (!file.type.includes('image/')) {
          alert('Please select an image file');
          return;
        }
        if (typeof FileReader === 'function') {
          const reader = new FileReader();
          reader.onload = (event) => {
            if(event.target.result) {
              this.selectedFile = event.target.result;
              this.$emit('imageUploader', this.selectedFile)
            }
          };
          reader.readAsDataURL(file);
        } else {
          alert('Sorry, FileReader API not supported');
        }
      }
    }
  }
</script>

<style scoped>
  input {
    border: none;
  }
</style>
