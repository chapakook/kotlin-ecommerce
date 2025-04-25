create table `points`
(
    `balance`       bigint not null,
    `point_id`      bigint auto_increment primary key,
    `update_millis` bigint not null,
    `user_id`       bigint not null,
    `version`       bigint not null
);

INSERT INTO `points` (`point_id`,`user_id`,`balance`,`update_millis`,`version`)
VALUES
  (1,1,1253500,0,0),
  (2,2,4524100,0,0),
  (3,3,8938400,0,0),
  (4,4,3380200,0,0),
  (5,5,2361400,0,0),
  (6,6,3594500,0,0),
  (7,7,3886700,0,0),
  (8,8,7729200,0,0),
  (9,9,3322700,0,0),
  (10,10,1684100,0,0);

create table `coupon_events`
(
    `current_count`   int     not null,
    `max_count`       int     not null,
    `type`            tinyint not null,
    `coupon_event_id` bigint auto_increment primary key,
    `expiry_millis`   bigint  not null,
    `value`           bigint  not null,
    check (`type` between 0 and 1)
);

INSERT INTO `coupon_events` (`coupon_event_id`,`max_count`,`expiry_millis`,`value`,`type`,`current_count`)
VALUES
    (1,82166,1746145464734,281,1,10),
    (2,58462,1746145464734,926,1,7),
    (3,52739,1746145464734,431,1,8),
    (4,1659,1746145464734,263,1,2),
    (5,70938,1746145464734,120,1,5),
    (6,23364,1746145464734,567,1,4),
    (7,9849,1746145464734,394,0,0),
    (8,93659,1746145464734,937,1,1),
    (9,2386,1746145464734,994,1,1),
    (10,13873,1746145464734,694,0,0);

create table `stocks`
(
    `quantity`   int    not null,
    `version`    int    not null,
    `product_id` bigint not null,
    `stock_id`   bigint auto_increment primary key
);

INSERT INTO `stocks` (`stock_id`,`product_id`,`quantity`,`version`)
VALUES
    (1,1,296,0),
    (2,2,902,0),
    (3,3,570,0),
    (4,4,863,0),
    (5,5,434,0),
    (6,6,724,0),
    (7,7,281,0),
    (8,8,283,0),
    (9,9,444,0),
    (10,10,754,0);

create table `products`
(
    `price`      bigint       not null,
    `product_id` bigint auto_increment primary key,
    `name`       varchar(255) not null
);

INSERT INTO `products` (`product_id`,`name`,`price`)
VALUES
    (1,"Zeph Boyer",3471),
    (2,"Zenia Garza",1693),
    (3,"Igor Bond",3685),
    (4,"Kennan Nash",4693),
    (5,"Minerva Woodard",7298),
    (6,"Iona Knapp",9024),
    (7,"Matthew Mullins",7010),
    (8,"Erasmus Hampton",6290),
    (9,"Nissim Santos",9446),
    (10,"Brenden Lott",8943);

create table `orders`
(
    `quantity`       int    not null,
    `create_millis`  bigint not null,
    `order_id`       bigint auto_increment primary key,
    `payment_amount` bigint not null,
    `product_id`     bigint not null,
    `total_amount`   bigint not null,
    `user_id`        bigint not null
);

create table `payments`
(
    `amount`        bigint not null,
    `create_millis` bigint not null,
    `order_id`      bigint not null,
    `payment_id`    bigint auto_increment primary key
);

create table `coupons`
(
    `is_active`     bit     not null,
    `type`          tinyint not null,
    `coupon_id`     bigint auto_increment primary key,
    `create_millis` bigint  not null,
    `expiry_millis` bigint  not null,
    `update_millis` bigint  not null,
    `user_id`       bigint  not null,
    `value`         bigint  not null,
    check (`type` between 0 and 1)
);

