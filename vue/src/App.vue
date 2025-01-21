<template>
  <div id="app">
    <site-header />
    <router-view />
    <site-footer />
  </div>
</template>

<script>
  import SiteHeader from './components/SiteFooter.vue';
  import SiteFooter from './components/SiteHeader.vue';
  export default {
    components: {
      SiteHeader,
      SiteFooter
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
</style>