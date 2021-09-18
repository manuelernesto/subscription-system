create table email
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    email_to  varchar(100) not null,
    send_date timestamp     not null,
    status   varchar(15)  not null
);