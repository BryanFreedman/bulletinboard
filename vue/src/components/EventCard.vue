<template>
  <div class="event-card">
    <div class="flip-container">
      <div class="flipper">
        <!-- Front Face: Event Details -->
        <div class="front">
          <div class="pushpin" :style="{ backgroundColor: pushpinColor }"></div>
          <h2>{{ event.title }}</h2>
          <p>{{ event.description }}</p>
          <p><strong>Company:</strong> {{ event.company_name }}</p>
          <p>
            <strong>Venue:</strong> {{ event.venue_name }} - {{ event.venue_address }}
          </p>
          <p><strong>Ticket Price:</strong> {{ event.ticket_price }}</p>
          <p v-if="event.event_link">
            <strong>Link:</strong>
            <a :href="event.event_link" target="_blank" rel="noopener noreferrer">
              {{ event.event_link }}
            </a>
          </p>
          <p v-if="event.content_warnings">
            <strong>Content Warnings:</strong> {{ event.content_warnings }}
          </p>
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
        <!-- Back Face: Event Image -->
        <div class="back">
          <img :src="event.image_url" alt="Event Image" v-if="event.image_url" />
          <p v-else>No image available</p>
        </div>
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
      pushpinColor: ""
    };
  },
  created() {
    const randomIndex = Math.floor(Math.random() * this.availableColors.length);
    this.pushpinColor = this.availableColors[randomIndex];
  },
  methods: {
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
/* Container with perspective for 3D flip effect */
.event-card {
  perspective: 1000px;
  width: 400px;
  margin: 16px auto;
}

/* Flip container */
.flip-container {
  position: relative;
  width: 100%;
  transition: transform 0.6s;
  transform-style: preserve-3d;
}
.event-card:hover .flip-container {
  transform: rotateY(180deg);
}

/* Flipper */
.flipper {
  position: relative;
  width: 100%;
  height: 100%;
}

/* Front and Back Faces */
.front, .back {
  position: absolute;
  width: 100%;
  backface-visibility: hidden;
  -webkit-backface-visibility: hidden;
  top: 0;
  left: 0;
  padding: 16px;
  border: 1px solid #ddd;
  background: #fff;
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
  border-radius: 8px;
}

/* Front Face */
.front {
  z-index: 2;
}

/* Back Face: Flip it */
.back {
  transform: rotateY(180deg);
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

/* Pushpin styling */
.pushpin {
  position: absolute;
  top: -12px;
  left: 50%;
  transform: translateX(-50%);
  width: 24px;
  height: 24px;
  border-radius: 50%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  border: 2px solid #fff;
  z-index: 3;
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

/* Text styling */
h2 {
  margin-top: 32px; /* Extra space for pushpin overlap */
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

/* Image on the back */
.back img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 8px;
}
</style>
