
/*SET REFERENTIAL_INTEGRITY FALSE;*/
INSERT INTO ADDRESS VALUES(0, now(), now(), 'CS', 'ES', 'CV', 'S1', '12001');
INSERT INTO ADDRESS VALUES(1, now(), now(), 'CS', 'ES', 'CV', 'S2', '12002');

INSERT INTO USERS VALUES (0, now(), now(), 1532085363208, 'pepe@gmail.com', 'pepe', 0);
INSERT INTO USERS VALUES (1, now(), now(), 1532085363208, 'paco@gmail.com', 'paco', 1);
/*SET REFERENTIAL_INTEGRITY TRUE;*/
