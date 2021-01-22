drop table if exists comment CASCADE;
drop table if exists post CASCADE;
drop table if exists board CASCADE;
drop table if exists user CASCADE;

create table board
(
    id          bigint primary key auto_increment,
    created_at  timestamp not null default current_timestamp(),
    modified_at timestamp,
    name        varchar(255),
    type        varchar(255)
);
create table comment
(
    id          bigint primary key auto_increment,
    created_at  timestamp not null default current_timestamp(),
    modified_at timestamp,
    content     varchar(255),
    post_id     bigint,
    writer_id   bigint
);
create table post
(
    id          bigint primary key auto_increment,
    created_at  timestamp not null default current_timestamp(),
    modified_at timestamp,
    content     varchar(255),
    title       varchar(255),
    board_id    bigint,
    writer_id   bigint
);
create table user
(
    id          bigint primary key auto_increment,
    created_at  timestamp    not null,
    modified_at timestamp,
    email       varchar(255) not null,
    first_name  varchar(255),
    last_name   varchar(255),
    mobile      varchar(255),
    nickname    varchar(255) not null,
    password    varchar(255)
);
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user
    add constraint UK_n4swgcf30j6bmtb4l4cjryuym unique (nickname);
alter table comment
    add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 foreign key (post_id) references post(id);
alter table comment
    add constraint FKciieobgjeefmyp0mfyt1ehptd foreign key (writer_id) references user(id);
alter table post
    add constraint FK2t7katxxymxif93a9osshl0ns foreign key (board_id) references board(id);
alter table post
    add constraint FKh38931f585nv7e1qp8w7r9ux1 foreign key (writer_id) references user(id);


insert into board (id, created_at, modified_at, name, type) values (1, current_timestamp(), null, 'testNotice!!!', 'NOTICE');
insert into board (id, created_at, modified_at, name, type) values (2, current_timestamp(), null, 'testCommunity!!!', 'COMMUNITY');
insert into board (id, created_at, modified_at, name, type) values (3, current_timestamp(), null, 'testBlog!!!', 'BLOG');
insert into board (id, created_at, modified_at, name, type) values (4, current_timestamp(), null, 'testEtc!!!', 'ETC');

insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'hong@test.com', '길동', '홍', '0100000000', 'gildong', '11111', 1);
insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'sonny@test.com', '흥민', '손', '0100000000', 'sonny', '22222', 2);
insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'faker@test.com', '상혁', '이', '0100000000', 'faker', '33333', 3);
insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'zzang@test.com', '짱구', '신', '0100000000', 'zzanggu', '44444', 4);
insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'rooney@test.com', 'wayne', 'rooney', '0100000000', 'pig', '55555', 5);
insert into user (created_at, modified_at, email, first_name, last_name, mobile, nickname, password, id) values (sysdate(), null, 'messi@test.com', 'lionel', 'messi', '0100000000', 'goat', '66666', 6);


insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (1, sysdate(), null, 1, 'test content1', 'test title1', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (2, sysdate(), null, 2, 'test content2', 'test title2', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (3, sysdate(), null, 3, 'test content3', 'test title3', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (4, sysdate(), null, 4, 'test content4', 'test title4', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (5, sysdate(), null, 1, 'test content5', 'test title5', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (6, sysdate(), null, 2, 'test content6', 'test title6', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (7, sysdate(), null, 3, 'test content7', 'test title7', 1);
insert into post (id, created_at, modified_at, board_id, content, title, writer_id) values (8, sysdate(), null, 4, 'test content8', 'test title8', 1);



select count(*) from post group by writer_id order by null;