\connect bddfun;

CREATE TABLE bddfun.author(
	username varchar(50) PRIMARY KEY NOT NULL,
	email varchar(50) NOT NULL,
	bio varchar(400) NOT NULL
);
ALTER TABLE bddfun.author
  OWNER TO postgres;

CREATE TABLE bddfun.post(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	title varchar(50) NOT NULL,
	body varchar(400) NOT NULL,
	author_id varchar(50) NOT NULL
);

ALTER TABLE bddfun.post  ADD  CONSTRAINT fk_author FOREIGN KEY(author_id)
REFERENCES bddfun.author (username);

ALTER TABLE bddfun.post
  OWNER TO postgres;

  
COMMIT;
