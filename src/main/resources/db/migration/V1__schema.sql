CREATE TABLE catalog (
id BIGINT GENERATED ALWAYS AS IDENTITY,
code VARCHAR,
name VARCHAR,
price NUMERIC(10,2)
);


insert into catalog(code, name, price) values ('abcr', 'All Butter Croissant', 0.75);
insert into catalog(code, name, price) values ('ccr', 'Chocolate Croissant', 0.95);
insert into catalog(code, name, price) values ('b', 'Fresh Baguette', 1.60);
insert into catalog(code, name, price) values ('rv', 'Red Velvet', 3.95);
insert into catalog(code, name, price) values ('vs', 'Victoria Sponge', 5.45);
insert into catalog(code, name, price) values ('cc', 'Carrot Cake', 3.45);