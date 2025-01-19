<template>
  <div class="home">
    <h1>Theater Events</h1>
    <button @click="$router.push('/create-event')">Create New Event</button>
    <div class="event-list">
      <EventCard v-for="event in events" :key="event.id" :event="event" />
    </div>
  </div>
</template>

<script>
import EventCard from '../components/EventCard.vue';

export default {
  name: 'Home',
  components: { EventCard },
  data() {
    return {
      events: [], // Replace with API call later
    };
  },
  mounted() {
    // Simulate fetching events
    this.events = [
      {
        id: 1,
        title: 'Hamlet',
        location: 'Shakespeare Theatre',
        showtimes: [{ date: '2025-02-01', time: '19:00' }],
      },
    ];
  },
};
</script>

<style>
.home {
  padding: 16px;
}

.event-list {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}
</style>
