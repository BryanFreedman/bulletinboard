<template>
  <div class="home">
    <h1>Theater Events</h1>
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
      const response = await axios.get("http://localhost:8080/events");
      
      // Filter events with status "Approved"
      this.events = response.data.filter(event => event.status === "Approved");
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

.event-list {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
}

.loading,
.no-events {
  text-align: center;
  font-size: 18px;
  color: #555;
}
</style>
