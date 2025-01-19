<template>
    <div class="event-form">
      <h1>Create Event</h1>
      <form @submit.prevent="submitForm">
        <input v-model="title" placeholder="Event Title" required />
        <input v-model="location" placeholder="Location" required />
        <textarea v-model="description" placeholder="Description"></textarea>
        <div>
          <h3>Showtimes</h3>
          <div v-for="(showtime, index) in showtimes" :key="index" class="showtime-input">
            <input v-model="showtime.date" placeholder="Date (YYYY-MM-DD)" required />
            <input v-model="showtime.time" placeholder="Time (HH:mm)" required />
            <button type="button" @click="removeShowtime(index)">Remove</button>
          </div>
          <button type="button" @click="addShowtime">Add Showtime</button>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'EventForm',
    data() {
      return {
        title: '',
        location: '',
        description: '',
        showtimes: [{ date: '', time: '' }],
      };
    },
    methods: {
      addShowtime() {
        this.showtimes.push({ date: '', time: '' });
      },
      removeShowtime(index) {
        this.showtimes.splice(index, 1);
      },
      submitForm() {
        console.log({ title: this.title, location: this.location, description: this.description, showtimes: this.showtimes });
        this.$router.push('/');
      },
    },
  };
  </script>
  
  <style>
  .event-form {
    padding: 16px;
  }
  
  .showtime-input {
    display: flex;
    gap: 8px;
    margin-bottom: 8px;
  }
  </style>
  