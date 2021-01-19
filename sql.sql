CREATE type user_role as enum ('ADMIN', 'TRAINER', 'STUDENT');

CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	age INTEGER NOT NULL,
	login VARCHAR(50) NOT NULL,
	login_and_password VARCHAR(150) NOT NULL,
	status user_role,
	group_id BIGINT UNIQUE
);

CREATE TABLE groups (
	group_id BIGINT REFERENCES users(group_id),
	student_id BIGINT REFERENCES users(id)
);

CREATE TABLE themes (
   student_id BIGINT REFERENCES users(id),
   group_id BIGINT REFERENCES users(group_id),
   name_theme VARCHAR (150),
   mark SMALLINT
);