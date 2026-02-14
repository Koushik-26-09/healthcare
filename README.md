# Lasya: Clinic Appointment Scheduler

Spring Cloud microservices demo for patient management, appointment scheduling, and reminder notifications.

## Services

- **Eureka Server** (`8761`): service discovery.
- **Patient Service** (`8092`): patient profile and contact APIs.
- **Appointment Service** (`8093`): booking and upcoming appointment APIs.
- **Reminder Service** (`8094`): hourly scheduled reminder polling via Feign.
- **API Gateway** (`8080`): routes `/api/patients/**`, `/api/appointments/**`, `/api/reminders/**`.

## Milestones implemented

1. **Eureka registration:** patient and appointment services are Eureka clients.
2. **Reminder Feign path:** hourly cron job fetches upcoming 24-hour appointments and patient contacts, then logs reminder output.
3. **Gateway routing:** all three API prefixes route through Spring Cloud Gateway using service discovery.

## Run locally

```bash
mvn clean package
```

Start apps in this order:

```bash
mvn -pl eureka-server spring-boot:run
mvn -pl patient-service spring-boot:run
mvn -pl appointment-service spring-boot:run
mvn -pl reminder-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```

Open Eureka dashboard: `http://localhost:8761`.

Trigger reminder manually:

```bash
curl -X POST http://localhost:8094/api/reminders/trigger
```
