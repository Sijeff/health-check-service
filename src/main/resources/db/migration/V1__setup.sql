create table health_check (
    id bigint primary key,
    service_name varchar(255) not null unique,
    health_check_url varchar(255) not null,
    is_healthy boolean not null default false,
    created_at timestamp not null default now()
);

insert into health_check (id, service_name, health_check_url, is_healthy, created_at)
values (1, 'MockService1', 'http://www.fake-url.net/health', true, now());

insert into health_check (id, service_name, health_check_url, is_healthy, created_at)
values (2, 'MockService2', 'https://www.fake-url.com/health', true, now());