create table order_dish
(
	order_id bigint not null
		constraint fk1fevhe8ke4l3uebaotqn5ae77
			references orders,
	dish_id bigint not null
		constraint fksxcogiw9xscinh77ixpor5apo
			references dishes,
	constraint order_dish_pkey
		primary key (order_id, dish_id)
);

alter table order_dish owner to postgres;

