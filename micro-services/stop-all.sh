#!/bin/bash
set -e

PROJECT_NAME="microservices"
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "Stopping User service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/User/docker-compose.yml" down

echo "Stopping PatientManagement service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/PatientManagement/docker-compose.yml" down

echo "Stopping Gateway service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/Gateway/docker-compose.yml" down

echo "Stopping Form service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/Form/docker-compose.yml" down

echo "Stopping DataProcessing service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/DataProcessing/docker-compose.yml" down

echo "All services stopped!"
