<template>
  <header class="site-header">
    <div id="site-title">
      <router-link v-bind:to="{ name: 'home' }">
        <h1>Indie Theatre Bulletin</h1>
      </router-link>
    </div>
    <nav id="header-nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'about' }">About</router-link>
    </nav>
  </header>
</template>

<style scoped>
.site-header {
  padding: 8px 16px; /* Padding around the header */
  display: flex;
  align-items: center; /* Vertically aligns items */
  justify-content: space-between; /* Pushes title and nav to opposite ends */
  width: 100%; /* Ensures the header spans the full width of the viewport */
  box-sizing: border-box; /* Ensures padding is included in width */
  /* background-color: rgb(255, 255, 255); Fallback color */
  background-image: url('@/assets/images/paper-texture.jpg'); /* Path to your paper texture image */
  background-size: cover; /* Ensures the texture covers the header area */
  background-repeat: no-repeat; /* Prevents tiling */
  background-position: center; /* Centers the texture */
  border-bottom: 1px solid #ddd; /* Optional: Add a subtle border for better separation */
}

#site-title {
  margin: 0; /* Removes default margin */
}

#site-title h1 {
  margin: 0; /* Removes any extra margin around the heading */
}

#site-title a {
  text-decoration: none;
  color: inherit; /* Use the current text color */
}

#site-title a:hover {
  text-decoration: underline; /* Adds underline on hover */
}

#header-nav {
  display: flex;
  gap: 16px; /* Space between navigation links */
}

#header-nav a {
  text-decoration: none;
  color: inherit; /* Uses the current text color */
  font-weight: bold;
}

#header-nav a:hover {
  text-decoration: underline; /* Adds underline on hover */
}
</style>
