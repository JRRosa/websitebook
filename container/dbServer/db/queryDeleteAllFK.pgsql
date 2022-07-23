DO $$
DECLARE r RECORD;
BEGIN
    FOR r IN SELECT 
            constraint_name, table_name
        FROM information_schema.table_constraints 
        WHERE table_schema = 'dbo'
        and constraint_type = 'FOREIGN KEY' 
    LOOP
        EXECUTE('ALTER TABLE  dbo.' || quote_ident(r.table_name) || ' DROP CONSTRAINT ' || quote_ident(r.constraint_name) || ';');
    END LOOP;
END;
$$;


