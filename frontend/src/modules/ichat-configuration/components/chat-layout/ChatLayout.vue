<template>
  <div class="container-wrapper">
    <label>Ercheinungsform</label>
    <div class="display-type col-6 ml-3 border-option">
      <div class="my-2">
        <div class="option">
          <input type="radio" value="BESIDE_PAGE" v-model="layout.displayType">
          <label>Layer auf Seite</label>
        </div>
        <div class="option">
          <input type="radio" value="INTEGRATED" v-model="layout.displayType">
          <label>Integriert</label>
        </div>
        <div class="option">
          <input type="radio" value="POPUP" v-model="layout.displayType">
          <label class="mb-0">Popup</label>
        </div>
      </div>
    </div>


    <label class="mt-3">Anordnung</label>
    <div class="formation col-6 ml-3 border-option">
      <div class="my-3">
        <div class="row">
          <div class="col-5">
            <label>Text-Eingabefeld</label>
            <div class="border-option">
              <div class="option mt-3">
                <input type="radio" value="ABOVE_DIALOG" v-model="layout.textInputType">
                <label>über Dialog</label>
              </div>
              <div class="option">
                <input type="radio" value="BELOW_DIALOG" v-model="layout.textInputType">
                <label>unter Dialog</label>
              </div>
            </div>
          </div>

          <div class="col-6">
            <label>Buttons</label>
            <div class="border-option">
              <div class="option mt-3">
                <input type="radio" value="BESIDE_TEXT_AREA" v-model="layout.buttonType">
                <label>Neben Eingabelfeld</label>
              </div>
              <div class="option">
                <input type="radio" value="BELOW_DIALOG" v-model="layout.buttonType">
                <label>unter Dialog</label>
              </div>
            </div>
          </div>
        </div>
        <label class="mt-2">Logo</label>
        <div class="col-11 border-option">
          <ImageUploader class="no-border my-2" @imageUploader="changeLogo"/>
        </div>

        <label class="mt-2">Hintergrund</label>
        <div class="col-11 border-option">
          <div class="row mt-2">
            <div class="col-4">
              <input type="radio" value="TILES" v-model="layout.backgroundType">
              <label>Kacheln</label>
            </div>
            <div class="col-4">
              <input type="radio" value="FILLING" v-model="layout.backgroundType">
              <label>ausfüllend</label>
            </div>
          </div>
          <ImageUploader class="no-border mb-2" @imageUploader="changeBackground"/>
        </div>

        <label class="mt-2">Hintergrund</label>
        <div class="col-11 border-option">
          <div class="row">
            <div class="col-5 my-1 vertical-align">
              <label class="my-2 ">Schriftart</label>
              <select v-model="layout.fontFamily" class="ml-2 col-8">
                <option v-for="(v, k) in fontFamilies" :key="k" :value="v">
                  {{ v }}
                </option>
              </select>
            </div>

            <div class="col-3 my-1 vertical-align">
              <label class="my-2">Größe</label>
              <select v-model="layout.fontSize" class="ml-2 col-auto">
                <option v-for="(v, k) in sizes" :key="k" :value="v">
                  {{ v }} pt
                </option>
              </select>
            </div>
            <div class="col-4 my-1 text-center">
              <b-button-group class="my-2 " size="sm">
                <b-button>B</b-button>
                <b-button>U</b-button>
                <b-button>I</b-button>
              </b-button-group>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-group col-7 d-flex justify-content-around mt-4">
      <button class="">Abrechen</button>
      <button @click.prevent="saveLayout">Speichern</button>
    </div>
  </div>
</template>

<script>
  import ImageUploader from '../../../../components/ImageUploader'

  export default {
    name: 'ChatLayout',
    components: {
      ImageUploader
    },
    data () {
      return {
        layout: {
          id: '',
          displayType: 'BESIDE_PAGE',
          textInputType: 'BELOW_DIALOG',
          buttonType: 'BELOW_DIALOG',
          logo: '',
          backgroundImg: '',
          backgroundType: 'TILES',
          fontFamily: 'Arial',
          fontSize: 14,
          fontStyle: []
        },
        fontFamilies: ['Arial', 'Helvetica', 'Gill Sans'],
        sizes: [1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 24, 26, 30],
      }
    },
    methods: {
      changeLogo (image) {
        this.layout.logo = image
        console.log(image)
      },
      changeBackground (image) {
        this.layout.backgroundImg = image
      },
      printChange () {
        console.log('layout', this.layout)
      },
      saveLayout () {
        console.log('save')
        console.log('layout', this.layout)

      }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    height: 100% !important;
    margin-top: 0.75em;
  }

  .border-option {
    border: 1px solid black;
  }

  .option {
    margin-left: 1rem;
  }

  .button-group {
    position: absolute;
    bottom: 0;
  }

  .no-border {
    border: none;
  }

</style>
