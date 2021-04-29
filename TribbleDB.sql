--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-04-29 14:56:25

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
-- TOC entry 201 (class 1259 OID 24702)
-- Name: tribble; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tribble (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.tribble OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 24700)
-- Name: rock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rock_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rock_id_seq OWNER TO postgres;

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 200
-- Name: rock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rock_id_seq OWNED BY public.tribble.id;


--
-- TOC entry 2851 (class 2604 OID 24705)
-- Name: tribble id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tribble ALTER COLUMN id SET DEFAULT nextval('public.rock_id_seq'::regclass);


--
-- TOC entry 2985 (class 0 OID 24702)
-- Dependencies: 201
-- Data for Name: tribble; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tribble (id, name) VALUES (1, 'rocky');
INSERT INTO public.tribble (id, name) VALUES (2, 'tribble');
INSERT INTO public.tribble (id, name) VALUES (3, 'joe');
INSERT INTO public.tribble (id, name) VALUES (4, 'bob');
INSERT INTO public.tribble (id, name) VALUES (5, 'luke');
INSERT INTO public.tribble (id, name) VALUES (6, 'mike');
INSERT INTO public.tribble (id, name) VALUES (9, 'bob');
INSERT INTO public.tribble (id, name) VALUES (10, 'luke');
INSERT INTO public.tribble (id, name) VALUES (11, 'mike');
INSERT INTO public.tribble (id, name) VALUES (12, 'myers');


--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 200
-- Name: rock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rock_id_seq', 12, true);


--
-- TOC entry 2853 (class 2606 OID 24710)
-- Name: tribble rock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tribble
    ADD CONSTRAINT rock_pkey PRIMARY KEY (id);


-- Completed on 2021-04-29 14:56:25

--
-- PostgreSQL database dump complete
--

