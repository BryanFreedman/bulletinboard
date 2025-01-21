CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    showtimes JSONB NOT NULL,
    location VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
