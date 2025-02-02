-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER theatrebulletin_app_owner
WITH PASSWORD 'theatrebulletinappowner';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO theatrebulletin_app_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO theatrebulletin_app_owner;

CREATE USER theatrebulletin_app_appuser
WITH PASSWORD 'theatrebulletinappuser';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO theatrebulletin_app_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO theatrebulletin_app_appuser;
