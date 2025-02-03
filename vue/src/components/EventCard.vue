<template>
  <div class="event-card">
    <!-- Image Section with Pushpin -->
    <div class="image-container" v-if="event.image_url">
      <img :src="event.image_url" alt="Event Image" />
      <!-- Pushpin placed on top of the image, halfway hanging off the top -->
      <div class="pushpin" :style="{ backgroundColor: pushpinColor }"></div>
    </div>
    <!-- Details Section -->
    <div class="details">
      <h2>{{ event.title }}</h2>
      <p>{{ event.description }}</p>
      <p><strong>Company:</strong> {{ event.company_name }}</p>
      <p>
        <strong>Venue:</strong>
        {{ event.venue_name }} - {{ event.venue_address }}
      </p>
      <p><strong>Ticket Price:</strong> {{ event.ticket_price }}</p>
      <p v-if="event.event_link">
        <strong>Link: </strong>
        <a :href="event.event_link" target="_blank" rel="noopener noreferrer">
          {{ event.event_link }}
        </a>
      </p>
      <!-- Spoiler Toggle for Content Warnings -->
      <div v-if="event.content_warnings" class="spoiler-section">
        <button class="spoiler-toggle" @click="toggleContentWarnings">
          {{ showContentWarnings ? "Hide Content Warnings" : "*Show Content Warnings*" }}
        </button>
        <div v-if="showContentWarnings" class="content-warnings">
          <strong>Content Warnings:</strong> {{ event.content_warnings }}
        </div>
      </div>
      <div v-if="event.event_dates && event.event_dates.length">
        <strong>Event Dates:</strong>
        <ul>
          <li v-for="(date, index) in event.event_dates" :key="index">
            {{ formatDate(date) }}
          </li>
        </ul>
      </div>
      <div v-if="event.accessibility && event.accessibility.length">
        <strong>Accessibility:</strong>
        <p>{{ event.accessibility.join(', ') }}</p>
      </div>
      <div v-if="event.masking_policy && event.masking_policy.length">
        <strong>Masking:</strong>
        <p>{{ event.masking_policy.join(', ') }}</p>
      </div>
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
  data() {
    return {
      availableColors: [
        "#dc3545", // red
        "#fd7e14", // orange
        "#ffc107", // yellow
        "#6f42c1", // purple
        "#28a745", // green
        "#007bff"  // blue
      ],
      pushpinColor: "",
      showContentWarnings: false
    };
  },
  created() {
    const randomIndex = Math.floor(Math.random() * this.availableColors.length);
    this.pushpinColor = this.availableColors[randomIndex];
  },
  methods: {
    toggleContentWarnings() {
      this.showContentWarnings = !this.showContentWarnings;
    },
    formatDate(dateTime) {
      return new Date(dateTime).toLocaleString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit"
      });
    }
  }
};
</script>

<style scoped>
.event-card {
  width: 400px;
  margin: 16px auto;
  background: #fff;
  border: 10px solid #ffffff;
  
  overflow: visible;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* Image container now acts as a positioning context */
.image-container {
  position: relative;
  width: 100%;
}
.image-container img {
  width: 100%;
  display: block;
}

.pushpin {
  position: absolute;
  top: -20px;
  left: 50%;
  transform: translateX(-50%);
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 2px solid #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  z-index: 2;
}
.pushpin::before {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 6px;
  height: 6px;
  background-color: #fff;
  border-radius: 50%;
}

/* Details section */
.details {
  padding: 16px;
  margin-top: -8px;
}

/* Spoiler styling */
.spoiler-section {
  margin: 12px 0;
}
.spoiler-toggle {
  background: #f7f7f7;
  border: 1px solid #ccc;
  padding: 4px 8px;
  cursor: pointer;
  font-size: 0.9em;
  margin-bottom: 8px;
}
.content-warnings {
  background: #fefefe;
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 8px;
}

/* Text styling */
h2 {
  margin-top: 8px;  
  font-size: 1.5em;
  margin-bottom: 8px;
  color: #000;
}
p {
  margin: 4px 0;
  font-size: 1em;
  color: #000;
}
a {
  color: #007bff;
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
ul {
  list-style-type: disc;
  margin-left: 20px;
  padding: 0;
}
</style>
