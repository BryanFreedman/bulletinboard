<template>
  <div class="event-form">
    <h2>Submit a New Event</h2>
    <form @submit.prevent="submitEvent">
      
      <!-- Event Title -->
      <label for="title">Event Title:</label>
      <input type="text" id="title" v-model="event.title" required />

      <!-- Description -->
      <label for="description">Description:</label>
      <textarea id="description" v-model="event.description" required></textarea>

      <!-- Company Name -->
      <label for="company_name">Company Name:</label>
      <input type="text" id="company_name" v-model="event.company_name" required />

      <!-- Venue Name -->
      <label for="venue_name">Venue Name:</label>
      <input type="text" id="venue_name" v-model="event.venue_name" required />

      <!-- Venue Address -->
      <label for="venue_address">Venue Address:</label>
      <input type="text" id="venue_address" v-model="event.venue_address" required />

      <!-- Ticket Price -->
      <label for="ticket_price">Ticket Price:</label>
      <input type="text" id="ticket_price" v-model="event.ticket_price" required />

      <!-- Event Link -->
      <label for="event_link">Event Link:</label>
      <input type="url" id="event_link" v-model="event.event_link" />

      <!-- Contact Email -->
      <label for="email">Contact Email: (Will not be publicly displayed)</label>
      <input type="email" id="email" v-model="event.email_address" required />

      <!-- Image Upload -->
      <label for="image">Upload Event Image:</label>
      <input type="file" id="image" @change="handleImageUpload" accept="image/*" />

     <!-- Event Date & Time Selection -->
      <label for="event_datetime">Event Date & Time:</label>
      <div class="date-time-input">
        <input type="datetime-local" id="event_datetime" v-model="newDateTime" />
        <button type="button" @click="addDateTime" :disabled="!newDateTime">Add Date & Time</button>
      </div>

      <ul>
        <li v-for="(date, index) in event.event_dates" :key="index">
          {{ formatDate(date) }}
          <button type="button" @click="removeDate(index)">X</button>
        </li>
      </ul>

      <!-- Accessibility Options -->
      <fieldset class="accessibility-fieldset">
  <legend>Accessibility Features:</legend>
  <div class="accessibility-options">
  <div 
    v-for="option in accessibilityOptions" 
    :key="option.id" 
    class="accessibility-option"
  >
    <input type="checkbox" :id="option.id" :value="option.id" v-model="event.accessibilityOptions" />
    <label :for="option.id">{{ option.name }}</label>
  </div>
</div>


  <!-- Other Accessibility Option -->
  <label for="other_accessibility">Other Accessibility Features:</label>
  <input 
    type="text" 
    id="other_accessibility" 
    v-model="event.other_accessibility" 
    placeholder="Specify other accessibility features" 
  />
</fieldset>





      <!-- Masking Requirements -->
<fieldset class="masking-fieldset">
  <legend>Masking Requirements:</legend>
  <div class="masking-options">
    <div class="masking-option">
      <input type="checkbox" id="masks_all" value="Masks Required for All Performances" v-model="event.masking_policy" />
      <label for="masks_all">Masks Required for All Performances</label>
    </div>
    <div class="masking-option">
      <input type="checkbox" id="masks_some" value="Masks Required for Some Performances" v-model="event.masking_policy" />
      <label for="masks_some">Masks Required for Some Performances</label>
    </div>
    <div class="masking-option">
      <input type="checkbox" id="no_masks" value="No Mask Requirement" v-model="event.masking_policy" />
      <label for="no_masks">No Mask Requirement</label>
    </div>
  </div>
</fieldset>


      <!-- Content Warnings -->
      <label for="content_warnings">Content Warnings:</label>
      <textarea id="content_warnings" v-model="event.content_warnings"></textarea>

      <!-- Submit Button -->
      <button type="submit">Submit Event</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
  return {
    event: {
      title: "",
      description: "",
      company_name: "",
      venue_name: "",
      venue_address: "",
      ticket_price: "",
      event_link: "https://",
      email_address: "",
      content_warnings: "",
      image: null,
      event_dates: [],
      accessibilityOptions: [], // Changed from accessibility to accessibilityOptions
      other_accessibility: "",
      masking_policy: []
    },
    newDateTime: "",
    accessibilityOptions: [
      { id: "wheelchair", name: "Wheelchair Accessible" },
      { id: "closed_caption", name: "Closed Captions" },
      { id: "open_caption", name: "Open Captions" },
      { id: "audio_description", name: "Audio Description" },
      { id: "sign_language", name: "ASL Interpreting" },
      { id: "strobe_warning", name: "STROBE WARNING" }
    ]
  };
},
  methods: {
    addDateTime() {
      if (this.newDateTime && !this.event.event_dates.includes(this.newDateTime)) {
        this.event.event_dates.push(this.newDateTime);
        this.newDateTime = ""; // Reset input
      }
    },
    removeDate(index) {
      this.event.event_dates.splice(index, 1);
    },
    formatDate(dateTime) {
      return new Date(dateTime).toLocaleString("en-US", { 
        year: "numeric", 
        month: "long", 
        day: "numeric", 
        hour: "2-digit", 
        minute: "2-digit" 
      });
    },
    async submitEvent() {
      try {
        const jsonData = {
          ...this.event,
          event_dates: this.event.event_dates.map(date => new Date(date).toISOString()), // Convert to ISO
        };

        await axios.post("http://localhost:9000/events", jsonData, {
          headers: { "Content-Type": "application/json" },
        });

        alert("Event submitted successfully!");
        this.$router.push("/");
      } catch (error) {
        console.error("Error submitting event:", error);
        alert("Failed to submit event.");
      }
    }
  }
};

</script>

<style scoped>
.event-form {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin: 10px 0 5px;
}

input, textarea {
  width: 100%;
  padding: 8px;
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #ffec80;
  color: black;
  padding: 12px;
  font-size: 1.2rem;
  font-weight: bold;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #FED800;
}

/* Normalize checkbox sizes */
input[type="checkbox"] {
  width: 16px; /* Set consistent width */
  height: 16px; /* Set consistent height */
  margin: 0; /* Remove default margins */
  padding: 0; /* Remove default padding */
  display: inline-block;
  vertical-align: middle; /* Align with text */
}

/* General Fieldset Styling */
fieldset {
  margin: 16px 0;
  padding: 0;
  border: none;
}

/* Accessibility & Masking Fieldsets */
.accessibility-fieldset, .masking-fieldset {
  font-size: 1rem;
  margin: 16px 0;
}

.accessibility-options, .masking-options {
  display: grid;
  grid-template-columns: repeat(3, 1fr); /* 3 equal columns */
  gap: 12px; /* Space between grid items */
  margin-bottom: 16px;
}

.accessibility-option, .masking-option {
  display: flex;
  align-items: center; /* Align checkbox and label vertically */
  gap: 10px; /* Space between checkbox and label */
}

/* Add vertical dividers between columns */
.accessibility-option:not(:nth-child(3n)),
.masking-option:not(:nth-child(3n)) {
  border-right: 1px solid #ccc; /* Add divider */
  padding-right: 12px; /* Space between divider and text */
}

/* Align 'Other Accessibility Features' text input */
.accessibility-fieldset input[type="text"] {
  grid-column: span 3; /* Stretch across all columns */
}

/* Button Alignment */
button {
  margin-top: 16px;
  padding: 12px 20px;
  background-color: #ffec80;
  color: black;
  font-size: 1.1rem;
  font-weight: bold;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #fed800;
}

/* Responsive Design */
@media (max-width: 768px) {
  .accessibility-options, .masking-options {
    grid-template-columns: repeat(2, 1fr); /* 2 columns for smaller screens */
  }
}




</style>
