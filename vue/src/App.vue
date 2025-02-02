
<template>
  <div id="capstone-app">
    <div id="nav-wrapper">
      <SiteHeader />
      <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'about' }">About</router-link>&nbsp;|&nbsp;

        <!-- Show Login when user is NOT logged in, Logout when logged in -->
        <router-link v-bind:to="{ name: 'login' }" v-if="!isLoggedIn">Login</router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="isLoggedIn" @click="logout">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
import SiteHeader from "./components/SiteHeader.vue";

export default {
  components: {
    SiteHeader,
  },
  setup() {
    const store = useStore();

    // Computed property to check if user is logged in
    const isLoggedIn = computed(() => store.state.token !== "");

    // Logout function (clear token from Vuex)
    const logout = () => {
      store.commit("setToken", ""); // Clear the token
    };

    return {
      isLoggedIn,
      logout,
    };
  },
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  background-image: url('@/assets/images/corkboard.jpg'); /* Background image */
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  font-family: 'Arial', sans-serif;
}

/* Wrapper for Header & Nav */
#nav-wrapper {
  position: relative; /* Ensures navbar stays inside header */
}

/* Navbar styles */
#nav {
  position: absolute;
  top: 8px; /* Move up */
  right: 16px; /* Move to right */
  display: flex;
  gap: 12px;
  font-weight: bold;
  /* background: rgba(255, 255, 255, 0.8); Slight transparency */
  padding: 6px 12px;
  border-radius: 6px;
}

#nav a {
  text-decoration: none;
  color: black;
}

#nav a:hover {
  text-decoration: underline;
}
</style>



<!-- <template>
  <div id="capstone-app">
    <div id="nav-wrapper">
      <SiteHeader />
      <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'about' }">About</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token !== ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
import SiteHeader from './components/SiteHeader.vue';

export default {
  components: {
    SiteHeader,
  }
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  background-image: url('@/assets/images/corkboard.jpg'); /* Background image */
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  font-family: 'Arial', sans-serif;
}

/* Wrapper for Header & Nav */
#nav-wrapper {
  position: relative; /* Ensures navbar stays inside header */
}

/* Navbar styles */
#nav {
  position: absolute;
  top: 8px; /* Move up */
  right: 16px; /* Move to right */
  display: flex;
  gap: 12px;
  font-weight: bold;
  /* background: rgba(255, 255, 255, 0.8); Slight transparency */
  padding: 6px 12px;
  border-radius: 6px;
}

#nav a {
  text-decoration: none;
  color: black;
}

#nav a:hover {
  text-decoration: underline;
}
</style> -->





<!-- <template>
  <div id="capstone-app">
    <SiteHeader />
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
  import SiteHeader from './components/SiteHeader.vue';
  export default{
    components: {
      SiteHeader
    }
  }
</script>

<style>

body {
  margin: 0;
  padding: 0;
  background-image: url('@/assets/images/corkboard.jpg'); /* Path to the image */
  background-size: cover; /* Ensures the image covers the entire viewport */
  background-repeat: no-repeat; /* Prevents tiling */
  background-attachment: fixed; /* Keeps the image fixed while scrolling */
  background-position: center; /* Centers the image */
  font-family: 'Arial', sans-serif; /* Ensure a clean font pairing */
}


</style> -->