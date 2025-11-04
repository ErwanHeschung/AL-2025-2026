#!/bin/bash
# ---------- 1. Start Kafka stack ----------
echo "Starting Kafka stack..."
docker-compose -f ./kafka/docker-compose.yml up -d

# ---------- 2. Start IoT Gateway ----------
echo "Starting IoT Gateway..."
docker-compose -f ./iot_gateway/docker-compose.yml up -d

# ---------- 3. Start Bracelet ----------
echo "Starting Bracelet..."
docker-compose -f ./bracelet/docker-compose.yml up -d

# ---------- 4. Start Web Interface ----------
echo "Starting Web Interface..."
docker-compose -f ./web-interface/docker-compose.yml up -d

# ---------- 5. Start Microservices ----------
echo "Starting Microservices..."
./micro-services/run-all.sh

echo "All services are up!"
