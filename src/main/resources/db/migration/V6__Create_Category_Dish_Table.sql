create table category_dish
(
	category_id bigint not null
		constraint fk5eayyygwtlbv6337ukeqj1vig
			references categories,
	dish_id bigint not null
		constraint fkj7jyu9g12t3mr4pht86jam4v6
			references dishes,
	constraint category_dish_pkey
		primary key (category_id, dish_id)
);

alter table category_dish owner to postgres;

