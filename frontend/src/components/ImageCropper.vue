<template>
  <div class="mt-2">
    <input @change="setImage" accept="image/*" name="image"
           style="font-size: 1.2em; padding: 10px 0;"
           type="file"/>
    <br/>
    <div style="width: 400px; height:300px; border: 1px solid gray; display: inline-block;" v-if="isModifying">
      <vue-cropper

        :auto-crop-area="0.5"
        :background="true"
        :guides="true"
        :img-style="{ 'width': '400px', 'height': '300px' }"
        :min-container-height="180"
        :min-container-width="250"
        :rotatable="true"
        :src="imgSrc"
        :view-mode="2"
        alt="Source Image"
        drag-mode="crop"
        ref="cropper">
      </vue-cropper>

    </div>
    <img :src="cropImg" alt="Cropped Image" ref="croppedImag" style="border: 1px solid gray"
         v-show="imgSrc !== '' && cropImg !== ''"/>

    <button @click="cropImage" style="margin-right: 40px;" v-if="isModifying">Crop</button>

  </div>
</template>

<script>
  import VueCropper from 'vue-cropperjs'

  export default {
    name: 'ImageCropper',
    components: {
      VueCropper
    },
    data () {
      return {
        isModifying: false,
        imgSrc: '',
        cropImg: '',
      }
    },
    methods: {
      setImage (e) {
        this.isModifying = true
        const file = e.target.files[0]
        if (!file.type.includes('image/')) {
          alert('Please select an image file')
          return
        }
        if (typeof FileReader === 'function') {
          const reader = new FileReader()
          reader.onload = (event) => {
            this.imgSrc = event.target.result
            // rebuild cropperjs with the updated source
            this.$refs.cropper.replace(event.target.result)
          }
          reader.readAsDataURL(file)
        } else {
          alert('Sorry, FileReader API not supported')
        }
      },
      cropImage () {
        // get image data for post processing, e.g. upload or setting image src
        const canvas = this.$refs.cropper.getCroppedCanvas()
        this.$refs.croppedImag.style.maxHeight = 400 + 'px'
        this.$refs.croppedImag.style.maxWidth = 400 + 'px'
        this.$refs.croppedImag.style.width = (canvas.width / 2) + 'px'
        this.$refs.croppedImag.style.height = (canvas.height / 2) + 'px'

        this.cropImg = canvas.toDataURL()
        this.$emit('imageUploader', [new Blob([this.cropImg], {type: 'image/png'}), this.cropImg])

        this.isModifying = false
      },
    }
  }
</script>

<style scoped>

</style>
