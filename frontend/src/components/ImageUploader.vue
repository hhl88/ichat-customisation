<template>
  <div>
    <b-form-file accept="image/*"
                 :placeholder="placeholder"
                 @change="onFileSelected">

    </b-form-file>
  </div>
</template>

<script>
  export default {
    name: 'ImageUploader',
    props: {
      maxWidth: {
        type: Number,
        default: 500,
      },
      maxHeight: {
        type: Number,
        default: 600,
      }
    },
    data() {
      return {
        selectedFile: null,
        placeholder: 'Choose a file...'
      }
    },
    methods: {
      onFileSelected(event) {
        const file = event.target.files[0]
        this.placeholder = file.name
        console.log(file)
        if (!file.type.includes('image/')) {
          alert('Please select an image file')
          return;
        }
        if (typeof FileReader === 'function') {
          var reader = new FileReader();
          reader.readAsArrayBuffer(file);

          reader.onload = (event) => {
            // blob stuff
            var blob = new Blob([event.target.result]); // create blob...
            window.URL = window.URL || window.webkitURL;
            var blobURL = window.URL.createObjectURL(blob); // and get it's URL
            // helper Image object
            var image = new Image();
            image.src = blobURL;
            //preview.appendChild(image); // preview commented out, I am using the canvas instead
            image.onload = () => {
              // have to wait till it's loaded

              // newinput.value = resized; // put result from canvas into new hidden input
              this.selectedFile = this.resizeMe(image);
              this.$emit('imageUploader', [file, this.selectedFile])
            }
          };


          // const reader = new FileReader();
          // reader.onload = (event) => {
          //   if (event.target.result) {
          //     this.selectedFile = event.target.result;
          //     console.log(this.selectedFile);
          //     this.$emit('imageUploader', this.selectedFile)
          //   }
          // };
          // reader.readAsDataURL(file);
        } else {
          alert('Sorry, FileReader API not supported');
        }
      },
      resizeMe(img) {

        var canvas = document.createElement('canvas');

        var width = img.width;
        var height = img.height;

        // calculate the width and height, constraining the proportions
        if (width > height) {
          if (width > this.maxWidth) {
            //height *= max_width / width;
            height = Math.round(height *= this.maxWidth / width);
            width = this.maxWidth;
          }
        } else {
          if (height > this.maxHeight) {
            //width *= max_height / height;
            width = Math.round(width *= this.maxHeight / height);
            height = this.maxHeight;
          }
        }

        // resize the canvas and draw the image data into it
        canvas.width = width;
        canvas.height = height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, width, height);

        return canvas.toDataURL("image/jpeg", 0.5); // get the data from canvas as 70% JPG (can be also PNG, etc.)

      }
    }
  }
</script>

<style scoped>
  input {
    border: none;
  }
</style>
