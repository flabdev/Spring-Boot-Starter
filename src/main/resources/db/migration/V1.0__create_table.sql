CREATE TABLE employee (
  id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  email_address varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL
);

CREATE TABLE todos (
  id varchar(255) NOT NULL PRIMARY KEY,
  completed bit(1),
  order_number int,
  title varchar(255) NOT NULL
);