NETWORK_NAME="microservices-net"
PROJECT_NAME="microservices"

# Ensure network exists
if ! docker network ls | grep -q "$NETWORK_NAME"; then
  echo "Creating Docker network: $NETWORK_NAME"
  docker network create "$NETWORK_NAME"
fi

# Start services separately
docker-compose -p "$PROJECT_NAME" \
  -f User/docker-compose.yml \
  -f PatientManagement/docker-compose.yml \
  -f Gateway/docker-compose.yml \
  -f Form/docker-compose.yml \
  up -d --remove-orphans

echo "All services are up!"