INSERT INTO HISTORIC (VERSION, DESCRIPTION)
    VALUES ('1.0.0', 'Criação e estruturação do banco de dados');

INSERT INTO HISTORIC (VERSION, DESCRIPTION)
VALUES ('1.1.0', 'Criação de roles de usuários do sistema, dividindo em 3 grupos diferentes');

INSERT INTO HISTORIC (VERSION, DESCRIPTION)
VALUES ('1.2.0', 'Criação de notas de atualizações para o sistema e rota de acesso');

ALTER TABLE HISTORIC
    ADD CONSTRAINT unique_version UNIQUE (VERSION);