#!/bin/bash
set -e

# Get the directory of this script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

NETWORK_NAME="microservices-net"
PROJECT_NAME="microservices"

if ! docker network ls | grep -q "$NETWORK_NAME"; then
  echo "Creating Docker network: $NETWORK_NAME"
  docker network create "$NETWORK_NAME"
fi

echo "Starting User service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/User/docker-compose.yml" up -d

echo "Starting PatientManagement service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/PatientManagement/docker-compose.yml" up -d

echo "Starting Gateway service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/Gateway/docker-compose.yml" up -d

echo "Starting Form service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/Form/docker-compose.yml" up -d

echo "Starting DataProcessing service..."
docker-compose -p "$PROJECT_NAME" -f "$SCRIPT_DIR/DataProcessing/docker-compose.yml" up -d

echo "All services are up!"