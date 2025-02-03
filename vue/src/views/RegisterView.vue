<template>
  <div id="splash-page">
    <div class="overlay">
      <div class="register-form-container">
        <form @submit.prevent="register" class="register-form">
          <h1>Create Account</h1>
          <div role="alert" v-if="registrationErrors">
            {{ registrationErrorMsg }}
          </div>
          <div class="form-input-group">
            <label for="username">Email</label>
            <input
              type="text"
              id="username"
              v-model="user.username"
              required
              autofocus
              @input="clearErrors"
            />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              v-model="user.password"
              required
              @input="clearErrors"
            />
          </div>
          <div class="form-input-group">
            <label for="confirmPassword">Confirm Password</label>
            <input
              type="password"
              id="confirmPassword"
              v-model="user.confirmPassword"
              required
              @input="clearErrors"
            />
          </div>
          <button type="submit" class="btn-signin">Create Account</button>
          <p>
            <router-link class="register-link" :to="{ name: 'login' }">
              Already have an account? Log in.
            </router-link>
          </p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "Register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user"
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user."
    };
  },
  methods: {
    register() {
      if (this.user.password !== this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then(response => {
            if (response.status === 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" }
              });
            }
          })
          .catch(error => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    }
  }
};
</script>

<style scoped>

#splash-page {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-image: url('@/assets/pexels-micheile-8366815.jpg'); */
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
}


.overlay {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}


.register-form-container {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 40px;
  
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  max-width: 500px;
  min-width: 400px;
  width: 100%;
}


.register-form h1 {
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
}


.form-input-group {
  margin-bottom: 15px;
  text-align: left;
}

.form-input-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
}

.form-input-group input {
  width: 100%;
  padding: 10px;
  
  border: 1px solid #ddd;
}


.btn-signin {
  width: 100%;
  padding: 10px;
  background-color: #333;
  color: white;
  border: none;
  
  cursor: pointer;
  font-size: 16px;
  margin-top: 10px;
}

.btn-signin:hover {
  background-color: #000;
}


.register-link {
  color: #003566;
}

.register-link:hover {
  color: #0077b6;
}
</style>
