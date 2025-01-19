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
</style>