#!/bin/bash

PROJECT_NAME="microservices"

echo "Building all services in project '$PROJECT_NAME'..."

echo "Building User service..."
docker-compose -p "$PROJECT_NAME" -f User/docker-compose.yml build

echo "Building PatientManagement service..."
docker-compose -p "$PROJECT_NAME" -f PatientManagement/docker-compose.yml build

echo "Building Gateway service..."
docker-compose -p "$PROJECT_NAME" -f Gateway/docker-compose.yml build

echo "All images built!"
