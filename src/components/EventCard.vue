<template>
    <div class="event-card" @click="$router.push(`/event/${event.id}`)">
      <h2>{{ event.title }}</h2>
      <p>{{ event.location }}</p>
      <ul>
        <li v-for="showtime in event.showtimes" :key="showtime.date + showtime.time">
          {{ showtime.date }} - {{ showtime.time }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'EventCard',
    props: {
      event: Object,
    },
  };
  </script>
  
  <style>
  .event-card {
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 16px;
    cursor: pointer;
    transition: transform 0.2s;
  }
  
  .event-card:hover {
    transform: scale(1.02);
  }
  </style>
  