<template>
  <div class="container">
    <div class="row  mt-4 justify-content-center">
      <form class="col-5 justify-content-center" @submit.prevent="onSubmit(email,password)">
        <div class="row mx-0 mt-5 vertical-align">
          <div class="col-3 align-self-center">
            <label class="my-auto" for="email">Email</label>
          </div>
          <div class="col-4">
            <input class="col-auto" id="email" type="text" v-model="email" placeholder="Email">
          </div>
        </div>

        <div class="row mx-0 mt-3 mb-3 ">
          <div class="col-3">
            <label class="my-auto" for="password">Password</label>
          </div>
          <div class="col-4">
            <input class="col-auto" id="password" type="password" v-model="password" placeholder="Password">
          </div>
        </div>
        <button class="ml-3">Anmelden</button>
        <div class="ml-3 my-2">
          <span>Kein Konto. </span> <a>Sign up</a>
        </div>
      </form>
    </div>
  </div>

</template>

<script>
  import {mapState} from 'vuex'
  import {LOGIN} from '../../../constants/action.type'

  export default {
    name: 'Login',
    data () {
      return {
        email: null,
        password: null
      }
    },
    methods: {
      validateEmail: function (email) {
        /* eslint-disable */
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        return re.test(String(email).toLowerCase())
      },
      onSubmit (email, password) {
        if (this.validateEmail(email)) {
          console.log('valid email', email)
          this.$store
            .dispatch(LOGIN, {email, password})
            .then(() => {
              console.log('asdsad')
              this.$router.push({name: 'ichat'})
            })
        }
      }
    },
    computed: {
      ...mapState({
        errors: state => state.auth.errors
      })
    }
  }
</script>

<style scoped>
  input {
    width: 15em;
  }

  form {
    border: 1px solid black;
  }
</style>
