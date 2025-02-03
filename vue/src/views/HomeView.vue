<template>
  <div class="home">
    <h1>UPCOMING EVENTS in NEW YORK</h1> 
    <button @click="$router.push('/create-event')">SUBMIT YOUR EVENT!</button>
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
    const response = await axios.get(`${import.meta.env.VITE_REMOTE_API}/events`);

    // Get today's date to filter out past events 
    const today = new Date();

    this.events = response.data
      .filter(event => {
        
        if (!event.event_dates || event.event_dates.length === 0) return false;
        

        const earliestDate = new Date(Math.min(...event.event_dates.map(date => new Date(date).getTime())));
        return earliestDate >= today;
      })
      .sort((a, b) => {
        const earliestA = new Date(Math.min(...a.event_dates.map(date => new Date(date).getTime())));
        const earliestB = new Date(Math.min(...b.event_dates.map(date => new Date(date).getTime())));
        return earliestA - earliestB;
      });
  } catch (error) {
    console.error("Error fetching events:", error);
  } finally {
    this.loading = false;
  }
}
};
</script>

<style scoped>
.home {
  padding: 16px;
  display: flex; 
  flex-direction: column; 
  align-items: center; 
}

h1 {
  margin-top: 0;
  margin-bottom: 16px;
  color: #000; 
  background-image: url('@/assets/images/masking-tape-crop.png');
  background-size: cover; 
  background-repeat: no-repeat; 
  background-position: center; 
  padding: 12px 16px; 
 
  display: inline-block; 
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
}



button {
  margin-bottom: 24px; 
  background-color: #ffec80;
  color: #000000;
  padding: 12px 24px;
  font-size: 1.2rem;
  font-weight: bold;
  font-family: 'Kalam', sans-serif;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
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
  background-color: white; 
  padding: 12px 16px; 
  border-radius: 8px; 
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
  display: inline-block; 
  margin-top: 16px; 
}
</style>



<!-- <template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template> -->

<!-- <script>
export default {
};
</script> -->
