R2DBC Streaming of change rows using Postgres LISTEN/NOTIFY
=========================================================================

This example leverages Postgres `LISTEN`/`NOTIFY` for inserting and updating rows.

It consists of two pieces:

* Trigger: Every time a row in `pgbench_accounts` is changed, it emits the row as JSON to the `accounts_event_notification` channel.
* Notification listener that listens to `accounts_event_notification` and exposes events as HTTP Server-Sent Events.

## Building from Source

To run that project, you can easily use the provided [maven wrapper](https://github.com/takari/maven-wrapper).
You also need JDK 1.8.

```bash
 $ ./mvnw clean install
```

## Requirements

You need a running PostgreSQL database and `src/main/resources/application.properties` configured.
Please adapt the parameters to your environment.

## Running the Application

To run that project, you can easily use the provided [maven wrapper](https://github.com/takari/maven-wrapper).
You also need JDK 1.8.

```bash
 $ ./mvnw spring-boot:run
```

## Testing the Application

The application exposes two HTTP endpoints:

* `GET /bench/events`: Obtain the stream of account event notifications

Note: This example uses `curl`.

To test the application yourself, run the application and issue the following command in terminal.

**Obtain SSE stream**

```bash
 $ curl http://localhost:8080/bench/events
``` 


In the terminal that listens to the SSE stream, you should see:

```
$ curl http://localhost:8080/bench/events

data:{"id":62,"username":"joe","login_time":"2019-09-13T09:23:32.170708"}

data:{"id":63,"username":"joe","login_time":"2019-09-13T09:24:28.571422"}

data:{"id":64,"username":"joe","login_time":"2019-09-13T09:24:28.926448"}
```

Note that Postgres notifications are not persisted nor queued.

## Behind the scenes

Notifications are produced by a trigger that invokes a function. Your Postgres database gets initialized by using `schema.sql`.

R2DBC Postgres is an asynchronous/non-blocking/reactive driver that consumes asynchronous notifications and exposes these as `Flux<Notification>` on a single connection that is subscribed to the `account_event_notification` channel. 

Note: Spring uses semicolon (`;`) as statement separator. Since the trigger function uses semicolon as well, statements in `schema.sql` are separated by double semicolon (`;;`).

## License

This project is Open Source software licensed under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html).
