<template>
  <div class="container-wrapper">
    <label>Ercheinungsform</label>
    <div class="display-type col-6 ml-3 border-option">
      <div class="my-2">
        <div class="option">
          <input type="radio" value="0" v-model="layout.displayType">
          <label>Layer auf Seite</label>
        </div>
        <div class="option">
          <input type="radio" value="1" v-model="layout.displayType">
          <label>Integriert</label>
        </div>
        <div class="option">
          <input type="radio" value="2" v-model="layout.displayType">
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
                <input type="radio" value="0" v-model="layout.textInputType">
                <label>über Dialog</label>
              </div>
              <div class="option">
                <input type="radio" value="1" v-model="layout.textInputType">
                <label>unter Dialog</label>
              </div>
            </div>
          </div>

          <div class="col-6">
            <label>Buttons</label>
            <div class="border-option">
              <div class="option mt-3">
                <input type="radio" value="0" v-model="layout.buttonType">
                <label>Neben Eingabelfeld</label>
              </div>
              <div class="option">
                <input type="radio" value="1" v-model="layout.buttonType">
                <label>unter Dialog</label>
              </div>
            </div>
          </div>
        </div>
        <label class="mt-2">Logo</label>
        <div class="col-11 border-option">
          <ImageUploader class="no-border my-2" @imageUploader="changeLogo"/>
          <img v-if="layout.logo" :src="layout.logo" style="width: 200px; height: 150px; border: 1px solid gray">

        </div>


        <label class="mt-2">Hintergrund</label>
        <div class="col-11 border-option">
          <div class="row mt-2">
            <div class="col-4">
              <input type="radio" value="0" v-model="layout.backgroundType">
              <label>Kacheln</label>
            </div>
            <div class="col-4">
              <input type="radio" value="1" v-model="layout.backgroundType">
              <label>ausfüllend</label>
            </div>
          </div>
          <ImageUploader class="no-border mb-2" @imageUploader="changeBackground"/>
          <img v-if="layout.backgroundImg" :src="layout.backgroundImg"
               style="width: 200px; height: 150px; border: 1px solid gray">

        </div>

        <label class="mt-2">Hintergrund</label>
        <div class="col-11 border-option">
          <div class="row">
            <div class="col-5 my-1 vertical-align">
              <label class="my-2 ">Schriftart</label>
              <select v-model="layout.font.fontFamily" class="ml-2 col-8">
                <option v-for="(v, k) in fontFamilies" :key="k" :value="v">
                  {{ v }}
                </option>
              </select>
            </div>

            <div class="col-3 my-1 vertical-align">
              <label class="my-2">Größe</label>
              <select v-model="layout.font.fontSize" class="ml-2 col-auto">
                <option v-for="(v, k) in sizes" :key="k" :value="v">
                  {{ v }} pt
                </option>
              </select>
            </div>
            <div class="col-4 my-1 text-center">
              <b-button-group class="my-2 button-group" size="sm">
                <b-button class="font-style" v-for="btn in buttons" :pressed.sync="btn.state">
                  {{ btn.caption }}
                </b-button>
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
  import VueCropper from 'vue-cropperjs';
  import {SET_CURRENT_LAYOUT} from "../../../../constants/mutation.type";
  import {CHAT_LAYOUT_CREATE, CHAT_LAYOUT_UPDATE} from "../../../../constants/action.type";

  export default {
    name: 'ChatLayout',
    components: {
      ImageUploader
    },
    data() {
      return {
        layout: {
          id: '',
          name: 'frontend',
          displayType: '0',
          textInputType: '0',
          buttonType: '0',
          logo: '',
          backgroundImg: '',
          backgroundType: '0',
          font: {
            font: {
              fontFamily: 'Arial',
              fontSize: 14,
              fontStyles: []
            }
          },
        },
        fontFamilies: ['Arial', 'Helvetica', 'Gill Sans'],
        sizes: [1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 24, 26, 30],
        buttons: [
          {caption: 'B', state: false},
          {caption: 'U', state: false},
          {caption: 'I', state: false}
        ]
      }
    },
    mounted() {
      this.getCurrentLayoutFromStore();
    },
    created() {

      this.$store.subscribe(((mutation, state) => {
        if (mutation.type === SET_CURRENT_LAYOUT) {
          this.getCurrentLayoutFromStore();
        }

      }));

    },
    methods: {

      getCurrentLayoutFromStore() {
        console.log('layout', this.$store.getters.currentChatLayout);
        console.log('frontend', this.$store.getters.currentChatFrontEnd);

        const currentLayout = this.$store.getters.currentChatLayout;
        if (currentLayout) {
          if (currentLayout.id){
            this.layout = this.$store.getters.currentChatLayout;
            this.layout.font.fontStyles.forEach(styles => this.buttons[parseInt(styles)].state = true)
          }
          else
            this.layout = {
              id: '',
              name: '',
              displayType: '0',
              textInputType: '0',
              buttonType: '0',
              logo: '',
              backgroundImg: '',
              backgroundType: '0',
              font: {
                fontFamily: 'Arial',
                fontSize: 14,
                fontStyles: []
              }
            };
        }
      },
      changeLogo(image) {
        this.layout.logo = image
        console.log('logo', this.layout.logo)
      },
      changeBackground(image) {
        this.layout.backgroundImg = image
      },
      saveLayout() {
        const fontStyles = []
        for (let i = 0; i < this.buttons.length; i++) {
          if (this.buttons[i].state)
            fontStyles.push(i);
        }
        this.layout.font.fontStyles = fontStyles;

        if (this.layout.id) {
          this.$store
            .dispatch(CHAT_LAYOUT_UPDATE, this.layout)
            .then(res => {
            })
        } else {
          this.$store
            .dispatch(CHAT_LAYOUT_CREATE, this.layout)
            .then(res => {
            })
        }

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
    position: relative;
    bottom: 0;
  }

  .no-border {
    border: none;
  }

  .font-style:not(:disabled):not(.disabled).active {
    background: #ccc;
  }

</style>
