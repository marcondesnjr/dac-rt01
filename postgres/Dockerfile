FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123456
ENV POSTGRES_DB dac-atividade-02
ADD ./create.sql /docker-entrypoint-initdb.d/
ADD ./insert.sql /docker-entrypoint-initdb.d/
