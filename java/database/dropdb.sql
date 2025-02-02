-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'theatrebulletin1';

DROP DATABASE theatrebulletin1;

DROP USER theatrebulletin_app_owner;
DROP USER theatrebulletin_app_appuser;
