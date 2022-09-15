create table library (
	book_seq integer auto_increment primary key,
	book_title varchar(50) not null,
	book_author varchar(50) not null,
	book_price integer not null,
	book_image varchar(50) not null,
	book_attachment varchar(50)
);

select * from library;
