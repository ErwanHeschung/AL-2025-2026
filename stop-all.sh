#!/bin/bash
set -e

# ---------- 1. Stop Microservices ----------
echo "Stopping Microservices..."
cd micro-services
./run-all.sh down || echo "Microservices already stopped"
cd ..

# ---------- 2. Stop Web Interface ----------
echo "Stopping Web Interface..."
docker-compose -f ./web-interface/docker-compose.yml down

# ---------- 3. Stop Bracelet ----------
echo "Stopping Bracelet..."
docker-compose -f ./bracelet/docker-compose.yml down

# ---------- 4. Stop IoT Gateway ----------
echo "Stopping IoT Gateway..."
docker-compose -f ./iot_gateway/docker-compose.yml down

# ---------- 5. Stop Kafka stack ----------
echo "Stopping Kafka stack..."
docker-compose -f ./kafka/docker-compose.yml down

# ---------- 5. Stop Microservices stack ----------
echo "Stopping Microservices stack..."
cd micro-services
./stop-all.sh

echo "All services are stopped!"
