<!-- ACTUALLY THE HEADER -->


<template>
    <header>
        <div id="site-logo">
        </div>
        <div id="site-title">
            <h1>Indie Theatre Bulletin</h1>
        </div>
        <nav id="header-nav">
            <router-link v-bind:to="{ name: 'home' }">Home   </router-link>
            <router-link v-bind:to="{ name: 'about' }"> About Us</router-link>
            
        </nav>
    </header>
</template>

<style scoped>
</style>