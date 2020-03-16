Scripts
Here, I memo scripts that I have used during development.

Postgres
Run Postgres Container(Create image)
docker run --name rest -p 5432:5432 -e POSTGRES_PASSWORD=pass -d postgres

This cmdlet will create Postgres instance so that you can connect to a database with:

database: postgres
username: postgres
password: pass
post: 5432
Getting into the Postgres container
docker exec -i -t rest bash
Then you will see the containers bash as a root user.

Connect to a database
psql -d postgres -U postgres
Query Databases
\l
Query Tables
\dt
Quit
\q