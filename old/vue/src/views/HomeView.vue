<template>
  <div class="home">
    <h1>Upcoming Events</h1> 
    <button @click="$router.push('/create-event')">Submit New Event</button>
    <div v-if="loading" class="loading">Loading events...</div>
    <div v-if="!loading && events.length === 0" class="no-events">No events found.</div>
    <div v-if="!loading" class="event-list">
      <EventCard v-for="event in events" :key="event.id" :event="event" />
    </div>
  </div>
</template>

<script>
import EventCard from "@/components/EventCard.vue";
import axios from "axios";

export default {
  name: "Home",
  components: { EventCard },
  data() {
    return {
      events: [],
      loading: true,
    };
  },
  async mounted() {
    try {
      const response = await axios.get("http://localhost:8080/events/approved");

      // Get today's date to filter out past events
      const today = new Date();

      this.events = response.data
        .filter(event => {
          // Ensure event has showtimes and filter out past events
          if (!event.showtimes || event.showtimes.length === 0) return false;
          const latestShowtime = event.showtimes.reduce((latest, showtime) => {
            const showDate = new Date(`${showtime.date}T${showtime.time}`);
            return showDate > latest ? showDate : latest;
          }, new Date(0));
          return latestShowtime >= today;
        })
        .sort((a, b) => {
          // Sort events by the earliest showtime
          const earliestA = new Date(`${a.showtimes[0].date}T${a.showtimes[0].time}`);
          const earliestB = new Date(`${b.showtimes[0].date}T${b.showtimes[0].time}`);
          return earliestA - earliestB;
        });
    } catch (error) {
      console.error("Error fetching events:", error);
    } finally {
      this.loading = false;
    }
  },
};
</script>

<style scoped>
.home {
  padding: 16px;
  display: flex; /* Enable flexbox */
  flex-direction: column; /* Stack children vertically */
  align-items: center; /* Center-align children horizontally */
}

h1 {
  margin-top: 0;
  margin-bottom: 16px;
  color: #000; /* Make the text color contrast well */
  background-image: url('@/assets/images/masking-tape-crop.png');
  background-size: cover; /* Ensure the image scales to fit */
  background-repeat: no-repeat; /* Prevent repetition */
  background-position: center; /* Center the image */
  padding: 12px 16px; /* Add spacing around the text */
  /* border-radius: 8px; Add slight rounding to the edges */
  display: inline-block; /* Wrap the content to only the heading's size */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Optional: Add a shadow for emphasis */
}



button {
  margin-bottom: 24px; /* Adds spacing below the button */
  background-color: #ffec80;
  color: #000000;
  padding: 12px 24px;
  font-size: 1.2rem;
  font-weight: bold;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Optional: Add shadow for emphasis */
}



button:hover {
  background-color: #FED800;
  transform: scale(1.05);
}

button:active {
  transform: scale(0.95);
}

.event-list {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.loading,
.no-events {
  text-align: center;
  font-size: 18px;
  color: #000000;
}
</style>
