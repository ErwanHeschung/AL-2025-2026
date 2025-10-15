# Kafka Docker Compose Setup

A simple Docker Compose setup to run a single-node Kafka cluster with Zookeeper and Kafka UI.

---

## What it does

- Runs **Zookeeper** for Kafka
- Runs a **single Kafka broker**
- Runs **Kafka UI** for web

---

## How to run

```bash
  docker compose up -d
```

Access Kafka UI in your browser: http://localhost:8080

```bash
  docker compose down
```
