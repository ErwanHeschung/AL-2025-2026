#!/bin/bash

NETWORK_NAME="microservices-net"
PROJECT_NAME="microservices"

# Ensure network exists
if ! docker network ls | grep -q "$NETWORK_NAME"; then
  echo "Creating Docker network: $NETWORK_NAME"
  docker network create "$NETWORK_NAME"
fi

# Start all services using the same project name
echo "Starting all services as a single stack..."

docker-compose -p "$PROJECT_NAME" \
  -f User/docker-compose.yml \
  -f PatientManagement/docker-compose.yml up -d

echo "All services are up in project '$PROJECT_NAME'!"
