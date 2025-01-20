

<template>
  <div class="event-form">
    <h1>Create Event</h1>
    <form @submit.prevent="submitForm">
      <input v-model="title" placeholder="Event Title" required />
      <input v-model="location" placeholder="Location" required />
      <input v-model="companyName" placeholder="Company Name" required />
      <input v-model="emailAddress" type="email" placeholder="Email Address" required />
      <textarea v-model="description" placeholder="Description" required></textarea>
      <input v-model="eventLink" placeholder="Event Link (optional)" />
      <input v-model="ticketPrice" placeholder="Ticket Price (e.g., Free, $20)" required />

      <div>
        <h3>Showtimes</h3>
        <div v-for="(showtime, index) in showtimes" :key="index" class="showtime-input">
          <input v-model="showtime.date" placeholder="Date (YYYY-MM-DD)" required />
          <input v-model="showtime.time" placeholder="Time (HH:mm)" required />
          <button type="button" @click="removeShowtime(index)">Remove</button>
        </div>
        <button type="button" @click="addShowtime">Add Showtime</button>
      </div>

      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventForm",
  data() {
    return {
      title: "",
      location: "",
      companyName: "",
      emailAddress: "",
      description: "",
      eventLink: "",
      ticketPrice: "",
      showtimes: [{ date: "", time: "" }],
    };
  },
  methods: {
    addShowtime() {
      this.showtimes.push({ date: "", time: "" });
    },
    removeShowtime(index) {
      this.showtimes.splice(index, 1);
    },
    async submitForm() {
      try {
        const payload = {
          title: this.title,
          location: this.location,
          companyName: this.companyName,
          emailAddress: this.emailAddress,
          description: this.description,
          eventLink: this.eventLink,
          ticketPrice: this.ticketPrice,
          showtimes: this.showtimes,
        };

        // Send data to the API
        await axios.post("http://localhost:8080/events", payload);

        // Redirect to the home page after submission
        this.$router.push("/");
      } catch (error) {
        console.error("Error submitting form:", error);
        alert("There was an error submitting the form. Please try again.");
      }
    },
  },
};
</script>

<style scoped>
.event-form {
  padding: 16px;
  max-width: 600px;
  margin: auto;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.event-form input,
.event-form textarea {
  display: block;
  width: 100%;
  margin-bottom: 12px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.showtime-input {
  display: flex;
  gap: 8px;
  margin-bottom: 8px;
}

.showtime-input input {
  flex: 1;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

button[type="button"] {
  background-color: #dc3545;
  margin-left: 8px;
}

button[type="button"]:hover {
  background-color: #c82333;
}
</style>




<!-- <template>
  <div class="event-form">
    <h1>Create Event</h1>
    <form @submit.prevent="submitForm">
      <input v-model="title" placeholder="Event Title" required />
      <input v-model="location" placeholder="Location" required />
      <textarea v-model="description" placeholder="Description" required></textarea>
      <input v-model="eventLink" placeholder="Event Link (optional)" />
      <input v-model="ticketPrice" placeholder="Ticket Price (e.g., Free, $20)" required />

      <div>
        <h3>Showtimes</h3>
        <div v-for="(showtime, index) in showtimes" :key="index" class="showtime-input">
          <input v-model="showtime.date" placeholder="Date (YYYY-MM-DD)" required />
          <input v-model="showtime.time" placeholder="Time (HH:mm)" required />
          <button type="button" @click="removeShowtime(index)">Remove</button>
        </div>
        <button type="button" @click="addShowtime">Add Showtime</button>
      </div>

      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventForm",
  data() {
    return {
      title: "",
      location: "",
      description: "",
      eventLink: "",
      ticketPrice: "",
      showtimes: [{ date: "", time: "" }],
    };
  },
  methods: {
    addShowtime() {
      this.showtimes.push({ date: "", time: "" });
    },
    removeShowtime(index) {
      this.showtimes.splice(index, 1);
    },
    async submitForm() {
      try {
        const payload = {
          title: this.title,
          location: this.location,
          description: this.description,
          eventLink: this.eventLink,
          ticketPrice: this.ticketPrice,
          showtimes: this.showtimes,
        };

        // Send data to the API
        await axios.post("http://localhost:8080/events", payload);

        // Redirect to the home page after submission
        this.$router.push("/");
      } catch (error) {
        console.error("Error submitting form:", error);
        alert("There was an error submitting the form. Please try again.");
      }
    },
  },
};
</script>

<style scoped>
.event-form {
  padding: 16px;
  max-width: 600px;
  margin: auto;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.event-form input,
.event-form textarea {
  display: block;
  width: 100%;
  margin-bottom: 12px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.showtime-input {
  display: flex;
  gap: 8px;
  margin-bottom: 8px;
}

.showtime-input input {
  flex: 1;
}

button {
  padding: 8px 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

button[type="button"] {
  background-color: #dc3545;
  margin-left: 8px;
}

button[type="button"]:hover {
  background-color: #c82333;
}
</style> -->
