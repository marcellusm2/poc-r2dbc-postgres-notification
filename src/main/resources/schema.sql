CREATE OR REPLACE FUNCTION notify_accounts_event() RETURNS TRIGGER AS
$$
BEGIN
    PERFORM pg_notify('accounts_event_notification', row_to_json(NEW)::text);
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS notify_accounts_event ON pgbench_accounts;

CREATE TRIGGER notify_accounts_event
    AFTER INSERT OR UPDATE OR DELETE
    ON pgbench_accounts
    FOR EACH ROW
EXECUTE PROCEDURE notify_accounts_event();


CREATE OR REPLACE FUNCTION notify_poi_event() RETURNS TRIGGER AS
$$
BEGIN
    PERFORM pg_notify('poi_event_notification', row_to_json(NEW)::text);
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS notify_poi_event ON purchase_order_item;

CREATE TRIGGER notify_poi_event
    AFTER INSERT OR UPDATE OR DELETE
    ON purchase_order_item
    FOR EACH ROW
EXECUTE PROCEDURE notify_poi_event();