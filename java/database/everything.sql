BEGIN TRANSACTION;

-- Users Table
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Events Table
DROP TABLE IF EXISTS events CASCADE;
CREATE TABLE events (
    event_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    venue_name VARCHAR(255) NOT NULL,
    venue_address VARCHAR(255) NOT NULL,
    ticket_price VARCHAR(50) NOT NULL,
    event_link VARCHAR(500),
    email_address VARCHAR(255) NOT NULL,
    image_url VARCHAR(500), -- Stores the image path
    content_warnings TEXT,
    status VARCHAR(20) DEFAULT 'pending', -- pending, approved, rejected
    masking_policy TEXT, -- JSON array
    accessibility_options TEXT, -- JSON array
    other_accessibility TEXT,
    created_by INT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Event Dates Table (handles multiple showtimes per event)
DROP TABLE IF EXISTS event_dates CASCADE;
CREATE TABLE event_dates (
    date_id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    event_date TIMESTAMP NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE
);

-- Favorites Table (Users can favorite events)
DROP TABLE IF EXISTS favorites CASCADE;
CREATE TABLE favorites (
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    PRIMARY KEY (user_id, event_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE
);

COMMIT TRANSACTION;


GRANT SELECT ON TABLE events TO PUBLIC;


GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE events TO theatrebulletin_app_appuser;


GRANT USAGE, SELECT ON SEQUENCE events_event_id_seq TO theatrebulletin_app_appuser;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO theatrebulletin_app_appuser;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO theatrebulletin_app_appuser;