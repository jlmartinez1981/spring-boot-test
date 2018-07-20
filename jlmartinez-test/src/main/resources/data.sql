
SET REFERENTIAL_INTEGRITY FALSE;
INSERT INTO USERS VALUES (0, now(), now(), 1532085363208, 'pepe@gmail.com', 'pepe');
INSERT INTO USERS VALUES (1, now(), now(), 1532085363208, 'paco@gmail.com', 'paco');

INSERT INTO ADDRESS VALUES(0, now(), now(), 'CS', 'ES', 'CV', 'S1', '12001', 0);
INSERT INTO ADDRESS VALUES(1, now(), now(), 'CS', 'ES', 'CV', 'S2', '12002', 1);
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO users (name, price) VALUES 'Spring Boot - Spring Data JPA with Hibernate and H2 Web Console', 0.0)
