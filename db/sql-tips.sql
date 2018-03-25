ALTER TABLE public.test ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.albaran ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.cliente ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.contacto ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.direccion ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.entrada ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.envio ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.pedido ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.socio ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;
ALTER TABLE public.transportista ALTER COLUMN id SET DEFAULT nextval('hibernate_sequence'::regclass) ;

CREATE OR REPLACE VIEW public.pebalmacen_public_tables_columns AS
 SELECT columns.table_name,
    columns.column_name
   FROM information_schema.columns
  WHERE columns.table_catalog::text ~~ 'pebalmacen'::text AND columns.table_schema::text ~~ 'public'::text
  ORDER BY columns.table_name, columns.ordinal_position;
