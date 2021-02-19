create table orders
(
	id bigint not null
		constraint orders_pkey
			primary key,
	date_created timestamp,
	date_modified timestamp,
	is_delete boolean,
	number integer not null,
	total_price numeric(19,2),
	client_id bigint
		constraint fkm2dep9derpoaehshbkkatam3v
			references clients
);

alter table orders owner to postgres;

