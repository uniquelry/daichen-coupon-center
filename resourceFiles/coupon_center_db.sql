CREATE SCHEMA IF NOT EXISTS coupon_center_db;
use coupon_center_db;

DROP TABLE IF EXISTS `coupon_template`;
CREATE TABLE `coupon_template`
(
    `id`          int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` datetime(3)   NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `name`        varchar(64)   NOT NULL DEFAULT '' COMMENT '优惠券名称',
    `description` varchar(256)  NOT NULL DEFAULT '' COMMENT '优惠券详细信息',
    `type`        varchar(16)   NOT NULL DEFAULT '' COMMENT '优惠券类型，比如满减、随机立减、晚间双倍等等',
    `shop_id`     bigint(20)             DEFAULT NULL COMMENT '优惠券适用的门店，如果是空则代表所有门店适用',
    `rule`        varchar(2048) NOT NULL DEFAULT '' COMMENT '详细的使用规则',
    `available`   tinyint(1)    NOT NULL DEFAULT '0' COMMENT '优惠券可用状态',
    PRIMARY KEY (`id`),
    KEY `idx_shop_id` (`shop_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='优惠券模板'
;

DROP TABLE if exists `coupon`;
CREATE TABLE `coupon`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `template_id` int(11)     NOT NULL DEFAULT '0' COMMENT '关联优惠券模板 Id',
    `user_id`     bigint(20)  NOT NULL DEFAULT '0' COMMENT '拥有这张券的用户 Id',
    `status`      int(2)      NOT NULL DEFAULT '0' COMMENT '优惠券的状态，比如未用，已用',
    `shop_id`     bigint(20)           DEFAULT NULL COMMENT '优惠券适用的门店，冗余字段，方便查找',
    PRIMARY KEY (`id`),
    KEY `idx_template_id` (`template_id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='优惠券'
;

INSERT INTO coupon_center_db.coupon_template (id, create_time, name, description, type, shop_id, rule, available)
VALUES (1, '2022-04-20 02:59:03.135', '双十二满200减20', '测试双十二满减优惠券', '1', null,
        '{"discount":{"quota":2000,"threshold":20000},"limitation":10}', 1);
INSERT INTO coupon_center_db.coupon_template (id, create_time, name, description, type, shop_id, rule, available)
VALUES (2, '2022-04-20 03:14:22.922', '双十二满200减20', '测试双十二满减优惠券', '1', null,
        '{"discount":{"quota":2000,"threshold":20000},"limitation":10}', 1);
INSERT INTO coupon_center_db.coupon_template (id, create_time, name, description, type, shop_id, rule, available)
VALUES (3, '2022-04-20 03:15:51.444', '双十二满200减20', '测试双十二满减优惠券', '1', null,
        '{"discount":{"quota":2000,"threshold":20000},"limitation":10}', 0);
INSERT INTO coupon_center_db.coupon_template (id, create_time, name, description, type, shop_id, rule, available)
VALUES (4, '2022-04-20 03:20:18.795', '双十二满200减20', '测试双十二满减优惠券', '1', 234,
        '{"discount":{"quota":2000,"threshold":20000},"limitation":10}', 1);
INSERT INTO coupon_center_db.coupon_template (id, create_time, name, description, type, shop_id, rule, available)
VALUES (5, '2022-04-20 03:20:31.306', '双十二满200减20', '测试双十二满减优惠券', '1', null,
        '{"discount":{"quota":5000,"threshold":10000},"limitation":10}', 1);

INSERT INTO coupon_center_db.coupon (id, create_time, template_id, user_id, status, shop_id)
VALUES (1, '2022-04-20 04:00:55.716', 1, 123456, 3, null);
INSERT INTO coupon_center_db.coupon (id, create_time, template_id, user_id, status, shop_id)
VALUES (2, '2022-04-20 05:56:46.467', 1, 123456, 2, null);
INSERT INTO coupon_center_db.coupon (id, create_time, template_id, user_id, status, shop_id)
VALUES (3, '2022-04-20 05:57:04.848', 4, 123456, 1, 234);
INSERT INTO coupon_center_db.coupon (id, create_time, template_id, user_id, status, shop_id)
VALUES (4, '2022-04-20 05:57:18.464', 5, 123456, 1, null);