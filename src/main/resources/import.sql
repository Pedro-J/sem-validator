/* INSERT usuários */
INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('admin', '', 'admin', '$2a$10$CoJLY0pNMZK52yA0KcDPme6oUtvJ0hPIGziwE2.jMuQpRpnx6WTxS', 'ADMIN');

INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('manager', '', 'manager', '$2a$10$HAQ8TCuhFtfCo0Q0RWv8DO4B/ugfvj0uGJee/U6cHs5AbhTohCJl.', 'MANAGER');

INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('user', '', 'user', '$2a$10$WQO.emtiXt5nNG1BLhSNW.61GVONGfXB4tAK6dI/nmQEQNNit1x3y', 'USER');


/* INSERT criterios */
INSERT INTO public.tb_criterion(id, description) VALUES (1, ' Criterio 1');
INSERT INTO public.tb_criterion(id, description) VALUES (2, ' Criterio 2');

/* INSERT questões */
INSERT INTO public.tb_question(id, description, tip, criterion_id, requirement_id)
VALUES (1, 'descrição 1', 'dica 1', 1, 1);

INSERT INTO public.tb_question(id, description, tip, criterion_id, requirement_id)
VALUES (2, 'descrição 2', 'dica 2', 2, 2);

/* INSERT requisitos */
INSERT INTO public.tb_requirement(id, description) VALUES (1, 'Requisito 1');
INSERT INTO public.tb_requirement(id, description) VALUES (2, 'Requisito 2');

/* INSERT models */
INSERT INTO public.tb_model(id, dsc_applicabilities, dsc_model, file_url, dsc_objectives, title, id_user)
VALUES (1, 'desc aplic.', 'desc modelo', '', 'obejtivos desc', 'model 1 titulo', 1);

/* INSERT checklist | type: 0 -> Verfication e 1 -> validation */
INSERT INTO public.tb_checklist(id, checklisttype, title, id_model, id_validator)
VALUES (1, 0, 'Titulo cl verificação', 1, 1);

INSERT INTO public.tb_checklist(id, checklisttype, title, id_model, id_validator)
VALUES (1, 1, 'Titulo cl validação', 1, 1);

