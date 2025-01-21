SELECT 
    e.id AS event_id,
    e.title AS event_title,
    e.description AS event_description,
    e.location AS event_location,
    e.company_name AS company_name,
    e.email_address AS email_address,
    e.status AS event_status,
    e.created_at AS created_at,
    s.id AS showtime_id,
    s.date AS showtime_date,
    s.time AS showtime_time
FROM 
    event e
LEFT JOIN 
    showtime s ON e.id = s.event_id
WHERE 
    e.status = 'Pending'
ORDER BY 
    e.id, s.date, s.time;
