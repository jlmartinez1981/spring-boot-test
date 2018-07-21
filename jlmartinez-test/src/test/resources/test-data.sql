DELETE FROM ADDRESS WHERE id >= 0;
DELETE FROM USERS WHERE id >= 0;
/*SET REFERENTIAL_INTEGRITY FALSE;*/
INSERT INTO ADDRESS VALUES(0, now(), now(), 'CS', 'ES', 'CV', 'S1', '12001');
INSERT INTO ADDRESS VALUES(1, now(), now(), 'CS', 'ES', 'CV', 'S2', '12002');

INSERT INTO USERS VALUES (0, now(), now(), now(), 'isabel@gmail.com', 'isabel', 0);
INSERT INTO USERS VALUES (1, now(), now(), now(), 'elena@gmail.com', 'elena', 1);
/*SET REFERENTIAL_INTEGRITY TRUE;*/
