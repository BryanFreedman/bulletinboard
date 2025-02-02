CREATE TABLE events (
    id SERIAL PRIMARY KEY,
	status VARCHAR(50) DEFAULT 'Pending',
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
	company_name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    event_link VARCHAR(500),
    ticket_price VARCHAR(100) NOT NULL,
	email_address VARCHAR(255) NOT NULL
);

CREATE TABLE showtimes (
    id SERIAL PRIMARY KEY,
    event_id INT NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    date DATE NOT NULL,
    time TIME NOT NULL
);
