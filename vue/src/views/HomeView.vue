<template>
  <div class="home">
    <h1>Upcoming Events</h1> 
    <button @click="$router.push('/create-event')">Create New Event</button>
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
}

h1 {
  margin-top: 0; /* Remove top margin from the h1 */
  margin-bottom: 16px; /* Add controlled space below the heading */
}

button {
  margin: 0 auto; /* Centers the button */
  margin-bottom: 24px; /* Adds spacing below the button */
  display: inline-block;
  background-color: #007bff;
  color: #ffffff;
  padding: 12px 24px;
  font-size: 1.2rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}


button:hover {
  background-color: #0056b3;
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
