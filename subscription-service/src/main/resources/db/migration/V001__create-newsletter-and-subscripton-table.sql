create table if not exists newsletter
(
    id          uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    description varchar(100) not null
);


insert into newsletter(id, description) values ('36d05deb-aca9-4b94-9c2c-76fd4a6f4530','zaragoza_newsletter');
insert into newsletter(description) values ('global_newsletter');

create table if not exists subscription
(
    id            uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name    varchar(100),
    email         varchar(100) not null,
    gender        varchar(15),
    date_of_birth timestamp    not null,
    consent       boolean      not null,
    newsletter_id uuid         not null,

    foreign key (newsletter_id) references newsletter (id)
);