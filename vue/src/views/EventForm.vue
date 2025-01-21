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
          <vue-datepicker
            v-model="showtime.datetime"
            type="datetime"
            :is-24="false" 
            placeholder="Select Date & Time"
            required
          />
          <button type="button" @click="removeShowtime(index)">Remove</button>
        </div>
        <button type="button" @click="addShowtime">Add Showtime</button>
      </div>

      <div class="submit-container">
        <button type="submit">Submit</button>
      </div>
    </form>
  </div>
</template>

<script>
import VueDatepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { format } from 'date-fns';
import axios from 'axios';

export default {
  name: 'EventForm',
  components: {
    VueDatepicker,
  },
  data() {
    return {
      title: '',
      location: '',
      companyName: '',
      emailAddress: '',
      description: '',
      eventLink: '',
      ticketPrice: '',
      showtimes: [{ datetime: null }],
    };
  },
  methods: {
    addShowtime() {
      this.showtimes.push({ datetime: null });
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
          showtimes: this.showtimes.map((st) => ({
            date: format(new Date(st.datetime), 'yyyy-MM-dd'),
            time: format(new Date(st.datetime), 'HH:mm'),
          })),
        };

        await axios.post('http://localhost:8080/events', payload);
        this.$router.push('/');
      } catch (error) {
        console.error('Error submitting form:', error);
        alert('There was an error submitting the form. Please try again.');
      }
    },
  },
};
</script>

<style scoped>
.event-form {
  padding: 32px; /* Increase padding to provide space inside the form */
  max-width: 600px; /* Set a specific maximum width for the form */
  margin: 32px auto; /* Center the form and add vertical margin */
  background-color: #f9f9f9; /* Set the background color */
  
  border: 1px solid #ddd; /* Add a border around the form */
  /* border-radius: 8px; Round the corners for a polished look */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
}

.event-form input,
.event-form textarea {
  display: block; /* Ensure each field starts on a new line */
  width: calc(100% - 32px); /* Reduce width slightly to account for padding */
  margin-bottom: 16px; /* Add spacing between fields */
  padding: 12px; /* Add inner padding for text fields */
  border: 1px solid #ccc; /* Add a light border */
  border-radius: 4px; /* Round the corners of input fields */
  box-sizing: border-box; /* Include padding and border in width calculation */
  font-family: Arial, Helvetica, sans-serif;
}

.showtime-input {
  display: flex;
  gap: 16px; /* Increase spacing between date and time fields */
  margin-bottom: 16px; /* Add spacing below each showtime input */
  font-family: Arial, Helvetica, sans-serif;
}

button {
  padding: 12px 24px; /* Add padding for better clickability */
  background-color: #007bff; /* Primary button color */
  color: #fff; /* White text color */
  border: none; /* Remove default borders */
  border-radius: 8px; /* Round the corners of buttons */
  cursor: pointer; /* Change the cursor to a pointer on hover */
  transition: background-color 0.3s ease, transform 0.2s ease; /* Smooth transitions */
}

button:hover {
  background-color: #0056b3; /* Darken the button color on hover */
}

button[type='button'] {
  background-color: #dc3545; /* Red background for remove buttons */
  margin-left: 8px; /* Add spacing to the left */
}

button[type='button']:hover {
  background-color: #c82333; /* Darker red on hover */
}

button[type='submit'] {
  display: block; /* Center the submit button */
  width: 200px; /* Set a fixed width */
  margin: 32px auto 0; /* Center horizontally and add space above */
  padding: 16px; /* Add more padding for larger appearance */
  font-size: 1.2rem; /* Increase font size */
  font-weight: bold; /* Make the text bold */
  background-color: #28a745; /* Green background */
  color: #fff; /* White text color */
  border: none; /* Remove borders */
  border-radius: 8px; /* Round the corners */
  transition: background-color 0.3s ease, transform 0.2s ease; /* Smooth transitions */
}

button[type='submit']:hover {
  background-color: #218838; /* Darker green on hover */
}

button[type='submit']:active {
  transform: scale(0.95); /* Add a slight "pressed" effect */
}

.submit-container {
  margin-top: 32px; /* Add spacing above the submit button */
  text-align: center; /* Center-align the button */
}

</style>
