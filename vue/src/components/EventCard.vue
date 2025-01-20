<template>
  <div class="event-card">
    <h2>{{ event.title }}</h2>
    <p>{{ event.description }}</p>
    <p><strong>Location:</strong> {{ event.location }}</p>
    <p><strong>Company:</strong> {{ event.companyName }}</p>
    <p><strong>Ticket Price:</strong> {{ event.ticketPrice }}</p>
    <a :href="event.eventLink" target="_blank" rel="noopener noreferrer" class="event-link">
      View Event
    </a>
    <div class="showtimes">
      <h4>Showtimes</h4>
      <ul>
        <li v-for="(showtime, index) in event.showtimes" :key="index">
          {{ formatDateTime(showtime.date, showtime.time) }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "EventCard",
  props: {
    event: {
      type: Object,
      required: true,
    },
  },
  methods: {
    formatDateTime(date, time) {
      return `${date} at ${time}`;
    },
  },
};
</script>

<style scoped>
.event-card {
  border: 1px solid #ddd;
  padding: 16px;
  border-radius: 8px;
  margin-bottom: 16px;
  background-color: #fff;
}

.event-link {
  color: #007bff;
  text-decoration: none;
}

.event-link:hover {
  text-decoration: underline;
}
</style>
