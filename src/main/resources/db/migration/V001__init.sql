--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: campeonato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.campeonato (
    id_campeonato bigint NOT NULL,
    divisao integer,
    temporada integer
);


ALTER TABLE public.campeonato OWNER TO postgres;

--
-- Name: campeonato_id_divisao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.campeonato ALTER COLUMN id_campeonato ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.campeonato_id_divisao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: participante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.participante (
    id_participante bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.participante OWNER TO postgres;

--
-- Name: participante_id_participante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.participante ALTER COLUMN id_participante ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.participante_id_participante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    role_id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.roles ALTER COLUMN role_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: standing; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.standing (
    id_standing bigint NOT NULL,
    pontos integer,
    pontos_fora integer,
    posicao integer,
    id_campeonato bigint,
    id_participante bigint
);


ALTER TABLE public.standing OWNER TO postgres;

--
-- Name: standing_id_standing_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.standing ALTER COLUMN id_standing ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.standing_id_standing_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: campeonato; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.campeonato (id_campeonato, divisao, temporada) VALUES (2, 1, 1);
INSERT INTO public.campeonato (id_campeonato, divisao, temporada) VALUES (3, 2, 1);
INSERT INTO public.campeonato (id_campeonato, divisao, temporada) VALUES (17, 3, 1);


--
-- Data for Name: participante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.participante (id_participante, nome) VALUES (16, 'Ichigo');
INSERT INTO public.participante (id_participante, nome) VALUES (21, 'Sakura');
INSERT INTO public.participante (id_participante, nome) VALUES (15, 'Sasuke');
INSERT INTO public.participante (id_participante, nome) VALUES (22, 'Kiba');
INSERT INTO public.participante (id_participante, nome) VALUES (25, 'Piccolo');


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (role_id, name) VALUES (1, 'USER');
INSERT INTO public.roles (role_id, name) VALUES (2, 'CREATOR');
INSERT INTO public.roles (role_id, name) VALUES (3, 'EDITOR');
INSERT INTO public.roles (role_id, name) VALUES (4, 'ADMIN');


--
-- Data for Name: standing; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (75, 1, 4, 3, 2, 22);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (73, 3, 2, 1, 2, 16);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (77, 2, 3, 2, 2, 21);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (76, 7, 2, 1, 3, 16);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (81, 7, 4, 3, 3, 22);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (80, 6, 2, 2, 3, 21);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (82, 4, 2, 1, 17, 16);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (83, 4, 1, 2, 17, 21);
INSERT INTO public.standing (id_standing, pontos, pontos_fora, posicao, id_campeonato, id_participante) VALUES (84, 3, 2, 3, 17, 22);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (user_id, enabled, password, username) VALUES (1, true, '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 'patrick');
INSERT INTO public.users (user_id, enabled, password, username) VALUES (2, true, '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 'alex');
INSERT INTO public.users (user_id, enabled, password, username) VALUES (3, true, '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 'john');
INSERT INTO public.users (user_id, enabled, password, username) VALUES (4, true, '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 'namhm');
INSERT INTO public.users (user_id, enabled, password, username) VALUES (5, true, '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 'admin');


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO public.users_roles (user_id, role_id) VALUES (4, 3);
INSERT INTO public.users_roles (user_id, role_id) VALUES (5, 4);


--
-- Name: campeonato_id_divisao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.campeonato_id_divisao_seq', 17, true);


--
-- Name: participante_id_participante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.participante_id_participante_seq', 62, true);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_role_id_seq', 4, true);


--
-- Name: standing_id_standing_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.standing_id_standing_seq', 84, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 5, true);


--
-- Name: campeonato campeonato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.campeonato
    ADD CONSTRAINT campeonato_pkey PRIMARY KEY (id_campeonato);


--
-- Name: participante nome; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participante
    ADD CONSTRAINT nome UNIQUE (nome);


--
-- Name: participante participante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participante
    ADD CONSTRAINT participante_pkey PRIMARY KEY (id_participante);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: standing standing_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.standing
    ADD CONSTRAINT standing_pkey PRIMARY KEY (id_standing);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users_roles fk2o0jvgh89lemvvo17cbqvdxaa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: standing fk57ndrynyggwg9db8jfsgthp5t; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.standing
    ADD CONSTRAINT fk57ndrynyggwg9db8jfsgthp5t FOREIGN KEY (id_campeonato) REFERENCES public.campeonato(id_campeonato);


--
-- Name: standing fkibpm12hmudp49h0unavqeplol; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.standing
    ADD CONSTRAINT fkibpm12hmudp49h0unavqeplol FOREIGN KEY (id_participante) REFERENCES public.participante(id_participante);


--
-- Name: users_roles fkj6m8fwv7oqv74fcehir1a9ffy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id) REFERENCES public.roles(role_id);


--
-- PostgreSQL database dump complete
--

