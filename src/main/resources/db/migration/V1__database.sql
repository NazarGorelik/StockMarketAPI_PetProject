create table user
(
    id         int primary key auto_increment,
    username   varchar(50) unique not null,
    password   varchar(100) not null,
    created_at timestamp,
    role       varchar(50) not null
);