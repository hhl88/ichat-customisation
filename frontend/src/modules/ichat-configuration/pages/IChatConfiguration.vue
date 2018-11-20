<template>
  <div class="container-wrapper row mt-5 mx-auto" v-if="!isLoading">
    <div class="col-2 sidebar">
      <Sidebar @changeItem="changeItem" :chat-front-end-list="chatFrontEndList" :chat-layout-list="chatLayoutList"/>
    </div>
    <div class="col-9 main-content text-left">
      <IChatConfigurationContent :view="view"/>
    </div>
  </div>
</template>

<script>
  import Sidebar from '../components/sidebar/Sidebar'
  import IChatConfigurationContent from '../components/main-content/IChatConfigurationContent'
  import {FETCH_CHAT_FRONTEND, FETCH_CHAT_LAYOUT} from '../../../constants/action.type'
  import {
    ADD_ITEM_TO_FRONT_END_LIST, ADD_ITEM_TO_LAYOUT_LIST,
    SET_FRONT_END_LIST, SET_LAYOUT_LIST,
    UPDATE_ITEM_FRONT_END_LIST, UPDATE_ITEM_LAYOUT_LIST
  } from "../../../constants/mutation.type";


  export default {
    name: 'Home',
    components: {Sidebar, IChatConfigurationContent},
    data() {
      return {
        view: 'frontend',
        selectedItem: null,
        chatFrontEndList: [],
        chatLayoutList: [],
        isLoading: true
      }
    },
    mounted() {
      this.fetchIChatFromStore();
    },
    created() {
      this.fetchIChatFromServer();
    },

    methods: {
      changeView(params) {
        this.view = params;
        history.pushState(null, '', '/ichat/' + params);
      },
      changeItem(data) {
        this.changeView(data.view);
      },
      fetchIChatFromServer() {
        this.isLoading = true;
        this.$store
          .dispatch(FETCH_CHAT_FRONTEND)
          .then((res) => {
            this.chatFrontEndList = res;
            this.$store
              .dispatch(FETCH_CHAT_LAYOUT)
              .then((res) => {
                this.chatLayoutList = res;
                this.isLoading = false;
              });
          });


      },
      fetchIChatFromStore() {
        this.$store.subscribe(((mutation, state) => {
          if (mutation.type === SET_FRONT_END_LIST || mutation.type === ADD_ITEM_TO_FRONT_END_LIST || mutation.type === UPDATE_ITEM_FRONT_END_LIST) {
            this.chatFrontEndList = this.$store.getters.frontEndList;
          } else if (mutation.type === SET_LAYOUT_LIST || mutation.type === ADD_ITEM_TO_LAYOUT_LIST || mutation.type === UPDATE_ITEM_LAYOUT_LIST) {
            this.chatLayoutList = this.$store.getters.layoutList;
          }
        }));
      }
    }
  }
</script>

<style scoped>
  .container-wrapper {
    height: 90vh;
  }

  .sidebar {
    border-right: 1px solid black;
  }
</style>
