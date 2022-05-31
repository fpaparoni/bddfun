FROM postgres:14.3
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=bddfun
COPY *.sql /docker-entrypoint-initdb.d/
