<template>
  <div class="event-card">
    <h2>{{ event.title }}</h2>
    <p>{{ event.description }}</p>
    <p><strong>Location:</strong> {{ event.location }}</p>
    <p><strong>Company:</strong> {{ event.companyName }}</p>
    <p><strong>Ticket Price:</strong> {{ event.ticketPrice }}</p>

    <!-- Event Link -->
    <p v-if="event.eventLink">
      <strong>Link: </strong>
      <a :href="event.eventLink" target="_blank" rel="noopener noreferrer">{{ event.eventLink }}</a>
    </p>

    <!-- Showtimes -->
    <div>
      <strong>Showtimes:</strong>
      <ul>
        <li v-for="(showtime, index) in event.showtimes" :key="index">
          {{ formatShowtime(showtime.date, showtime.time) }}
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
    formatShowtime(date, time) {
  // Parse the date string without applying time zone adjustments
  const dateParts = date.split("-");
  const localDate = new Date(dateParts[0], dateParts[1] - 1, dateParts[2]); // year, month (0-indexed), day

  // Format the date as "25 January 2025"
  const options = { year: "numeric", month: "long", day: "numeric" };
  const formattedDate = localDate.toLocaleDateString("en-US", options);

  // Format the time as "hh:mm am/pm"
  const formattedTime = new Date(`1970-01-01T${time}`).toLocaleTimeString("en-US", {
    hour: "2-digit",
    minute: "2-digit",
    hour12: true,
  });

  return `${formattedDate} at ${formattedTime}`;
}

  },
};
</script>

<style scoped>
.event-card {
  padding: 16px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 16px;
  max-width: 400px;
}

.event-card h2 {
  margin-top: 0;
  font-size: 1.5em;
  color: #000000;
}

.event-card p {
  margin: 8px 0;
  font-size: 1em;
  color: #000000;
}

.event-card a {
  color: #007bff;
  text-decoration: none;
}

.event-card a:hover {
  text-decoration: underline;
}

.event-card ul {
  padding-left: 20px;
  margin: 0;
}

.event-card ul li {
  list-style-type: disc;
  margin-bottom: 4px;
}
</style>
