CREATE TABLE clients (
  client_id UUID NOT NULL,
   client_name VARCHAR(255),
   client_email_address VARCHAR(255),
   CONSTRAINT pk_clients PRIMARY KEY (client_id)
);

CREATE TABLE jobs (
  job_id BIGINT AUTO_INCREMENT NOT NULL,
   job_title VARCHAR(255),
   job_location VARCHAR(255),
   CONSTRAINT pk_jobs PRIMARY KEY (job_id)
);