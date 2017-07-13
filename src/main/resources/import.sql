/* INSERT usuários */
INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('admin', '', 'admin', '$2a$10$CoJLY0pNMZK52yA0KcDPme6oUtvJ0hPIGziwE2.jMuQpRpnx6WTxS', 'ADMIN');

INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('manager', '', 'manager', '$2a$10$HAQ8TCuhFtfCo0Q0RWv8DO4B/ugfvj0uGJee/U6cHs5AbhTohCJl.', 'MANAGER');

INSERT INTO public.tb_user(firstname, lastname, login, password, profile)
VALUES ('user', '', 'user', '$2a$10$WQO.emtiXt5nNG1BLhSNW.61GVONGfXB4tAK6dI/nmQEQNNit1x3y', 'USER');


/* INSERT criterio */
INSERT INTO public.tb_criterion(id, description) VALUES (1, ' descrição 1');
INSERT INTO public.tb_criterion(id, description) VALUES (2, ' descrição 2');

/* INSERT questões */
INSERT INTO public.tb_question(id, description, "number", tip, criterion_id) VALUES (1, '', 1, '', 1);
INSERT INTO public.tb_question(id, description, "number", tip, criterion_id) VALUES (2, '', 1, '', 2);

/* INSERT requisitos */
INSERT INTO public.tb_requirement(id, description) VALUES (1, ' Requisito 1');
INSERT INTO public.tb_requirement(id, description) VALUES (2, ' Requisito 2');

/* ManyToMany criterio_requisito (os mesmos criterios podem estar em diferentes requisitos)*/
INSERT INTO public.tb_requirement_criterion(id_requirement, id_criterion) VALUES (1, 1);
INSERT INTO public.tb_requirement_criterion(id_requirement, id_criterion) VALUES (1, 2);
INSERT INTO public.tb_requirement_criterion(id_requirement, id_criterion) VALUES (2, 2);

/* checklist type: 0 -> Verfication e 1 -> validation */
INSERT INTO public.tb_checklist(id, checklisttype, title) VALUES (1, 0, 'Titulo cl verificação', 1);
INSERT INTO public.tb_checklist(id, checklisttype, title) VALUES (1, 1, 'Titulo cl validação', 1);

/* ManyToMany requisito_checklist (os mesmos requisistos podem estar em diferentes checklists)*/
INSERT INTO public.tb_checklist_requirement(id_checklist, id_requirement) VALUES (1, 1);
INSERT INTO public.tb_checklist_requirement(id_checklist, id_requirement) VALUES (1, 2);
INSERT INTO public.tb_checklist_requirement(id_checklist, id_requirement) VALUES (2, 1);
INSERT INTO public.tb_checklist_requirement(id_checklist, id_requirement) VALUES (2, 2);

