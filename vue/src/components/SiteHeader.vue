<template>
    <header class="site-header">
      <div id="site-title">
        <router-link v-bind:to="{ name: 'home' }">
          <h1>INDIE THEATRE BULLETIN</h1>
        </router-link>
      </div>
      <div id="nav">
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp; -->
      <!-- <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> -->
      </div>
      <!-- <nav id="header-nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-bind:to="{ name: 'about' }">About</router-link>
      </nav> -->
    </header>
  </template>
  
  <style scoped>
  .site-header {
    padding: 8px 16px; 
    display: flex;
    align-items: center; 
    justify-content: space-between; 
    width: 100%; 
    box-sizing: border-box; 
    background-color: rgb(255, 255, 255); 
    /* background-image: url('@/assets/images/paper-texture.jpg');  */
    background-size: cover; 
    background-repeat: no-repeat; 
    background-position: center; 
    border-bottom: 1px solid #ddd; 
  }
  
  #site-title {
    margin: 0; 
  }
  
  #site-title h1 {
    margin: 0; 
  }
  
  #site-title a {
    text-decoration: none;
    color: inherit; 
  }
  
  #site-title a:hover {
    text-decoration: underline; 
  }
  
  #header-nav {
    display: flex;
    gap: 16px;
  }
  
  #header-nav a {
    text-decoration: none;
    color: inherit; 
    font-weight: bold;
  }
  
  #header-nav a:hover {
    text-decoration: underline; 
  }
  </style>
  