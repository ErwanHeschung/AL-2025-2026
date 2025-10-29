#!/bin/bash

PROJECT_NAME="microservices"

echo "Stopping all services in project '$PROJECT_NAME'..."

docker-compose -p "$PROJECT_NAME" \
  -f User/docker-compose.yml \
  -f PatientManagement/docker-compose.yml down

echo "All services stopped!"