docker build -t web-interface:latest .
docker run -d -p 8080:80 --name web-interface web-interface:latest

Access web interface at http://localhost:8080
