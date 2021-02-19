create table categories
(
	id bigint not null
		constraint categories_pkey
			primary key,
	category varchar(255),
	date_created timestamp,
	date_modified timestamp,
	is_delete boolean
);

alter table categories owner to postgres;

