<template>
  <div class="container">
    <div class=" row mt-5 justify-content-center">
      <form class="col-7 align-self-center text-center" @submit.prevent="onSubmit">
        <div class="row my-5 vertical-align">
          <label class="col-4 my-auto" for="emailInput">Ihre Email-Adresse: </label>
          <input class="col-5" id="emailInput" v-model="email" placeholder="max@mustermann.de">
          <button class="col-2 ml-2">Absenden</button>
          <ul v-if="errors" class="error-messages">
            <li v-for="(v, k) in errors" :key="k">{{ k }} {{ v | error }}</li>
          </ul>
        </div>

      </form>
    </div>

  </div>
</template>

<script>
  import {mapState} from 'vuex'
  import {REGISTER} from '../../../constants/action.type'

  export default {
    name: 'Register',
    data () {
      return {
        email: ''
      }
    },
    computed: {
      ...mapState({
        errors: state => state.auth.errors
      })
    },
    methods: {
      validateEmail: function () {
        /* eslint-disable */
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return re.test(String(this.email).toLowerCase())
      },
      onSubmit () {
        if (this.validateEmail()) {
          this.$store.dispatch(REGISTER, {
            email: this.email,
          })
            .then((x) => console.log(x))
        } else {
        }
      }
    }
  }
</script>

<style scoped>
form {

  vertical-align: middle;
  border: 1px solid black;
}

input {
  border-radius: 3px;
  border: 1px solid black;
}


</style>
