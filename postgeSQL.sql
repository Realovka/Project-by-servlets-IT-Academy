CREATE TABLE users_hb2 (
	id BIGSERIAL PRIMARY KEY,
	user_name VARCHAR(50) NOT NULL,
	age INTEGER NOT NULL,
	login VARCHAR(50) NOT NULL,
	login_and_password VARCHAR(150) NOT NULL,
	status VARCHAR(50) NOT NULL,
	group_id int8 null
);

CREATE TABLE groups_hb2 (
	id BIGSERIAL,
	name VARCHAR (50)
);

CREATE TABLE group_student2 (
   group_id int8,
   student_id int8
);

CREATE TABLE themes_hb2 (
   id BIGSERIAL,
   name_theme VARCHAR (150),
   mark smallint null,
   group_id int8 null,
   student_id int8 null
);

CREATE TABLE trainer_with_salary_hb2 (
   id BIGSERIAL,
   name VARCHAR (35)
);

CREATE TABLE salary_hb2 (
    id BIGSERIAL,
    value numeric,
    trainer_with_salary_id int8
)