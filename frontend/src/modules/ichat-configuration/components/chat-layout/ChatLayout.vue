<template>
  <div class="container-wrapper">
    <label>Ercheinungsform</label>
    <div class="display-type col-6 ml-3 border-option">
      <div class="my-2">
        <div class="option">
          <input type="radio" value="Front" v-model="displayType">
          <label>Layer auf Seite</label>
        </div>
        <div class="option">
          <input type="radio" value="Integrate" v-model="displayType">
          <label>Integriert</label>
        </div>
        <div class="option">
          <input type="radio" value="Popup" v-model="displayType">
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
                <input type="radio" value="above" v-model="textInputType">
                <label>über Dialog</label>
              </div>
              <div class="option">
                <input type="radio" value="below" v-model="textInputType">
                <label>unter Dialog</label>
              </div>
            </div>
          </div>

          <div class="col-6">
            <label>Buttons</label>
            <div class="border-option">
              <div class="option mt-3">
                <input type="radio" value="above" v-model="buttonType">
                <label>Neben Eingabelfeld</label>
              </div>
              <div class="option">
                <input type="radio" value="below" v-model="buttonType">
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
              <input type="radio" value="TILES" v-model="backgroundType">
              <label>Kacheln</label>
            </div>
            <div class="col-4">
              <input type="radio" value="FILLING" v-model="backgroundType">
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
              <select v-model="fontFamily" class="ml-2 col-8">
                <option v-for="(v, k) in fontFamilies" :key="k" :value="v">
                  {{ v }}
                </option>
              </select>
            </div>

            <div class="col-3 my-1 vertical-align">
              <label class="my-2">Größe</label>
              <select v-model="fontFamily" class="ml-2">
                <option v-for="(v, k) in sizes" :key="k" :value="v">
                  {{ v }} pt
                </option>
              </select>
            </div>
            <div class="col-4 my-1">
              <b-button-group class="my-2" size="sm">
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
      <button>Speichern</button>
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
        id: '',
        displayType: 'Popup',
        textInputType: 'below',
        buttonType: '',
        logo: '',
        backgroundImg: '',
        backgroundType: '',
        fontFamily: '',
        fontSize: 13,
        fontStyle: {
          type: Array,
          default: () => []
        },
        fontFamilies: ['Arial', 'Helvetica', 'Gill Sans'],
        sizes: [1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 24, 26, 30],
      }
    },
    methods: {
      changeLogo (image) {
        this.logo = image
        console.log(image)
      },
      changeBackground (image) {
        this.backgroundImg = image
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
