docker run -d --name pg5432 -e POSTGRES_PASSWORD=postgres -p 5432:5432 postgres
cd store
chmod +x mvnw
./mvnw -Pdev
npm install
npm start