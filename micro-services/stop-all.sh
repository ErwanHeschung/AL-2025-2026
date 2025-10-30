#!/bin/bash

PROJECT_NAME="microservices"

echo "Stopping User service..."
docker-compose -p "$PROJECT_NAME" -f User/docker-compose.yml down

echo "Stopping PatientManagement service..."
docker-compose -p "$PROJECT_NAME" -f PatientManagement/docker-compose.yml down

echo "Stopping Gateway service..."
docker-compose -p "$PROJECT_NAME" -f Gateway/docker-compose.yml down

echo "All services stopped!"
