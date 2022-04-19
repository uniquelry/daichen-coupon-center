CREATE DATABASE IF NOT EXISTS coupon_center_db;

DROP TABLE IF EXISTS `coupon_center_db`.`coupon_template`;
CREATE TABLE IF NOT EXISTS `coupon_center_db`.`coupon_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `created_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '优惠券详细信息',
  `type` varchar(16) NOT NULL DEFAULT '' COMMENT '优惠券类型，比如满减、随机立减、晚间双倍等等',
  `shop_id` bigint(20) COMMENT '优惠券适用的门店，如果是空则代表所有门店适用',
  `rule` varchar(2048) NOT NULL DEFAULT '' COMMENT '详细的使用规则',
  `available` boolean NOT NULL DEFAULT false COMMENT '优惠券可用状态',
  PRIMARY KEY (`id`),
  KEY `idx_shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='优惠券模板';

DROP TABLE if exists  `coupon_center_db`.`coupon`;
CREATE TABLE IF NOT EXISTS `coupon_center_db`.`coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `created_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `template_id` int(11) NOT NULL DEFAULT '0' COMMENT '关联优惠券模板 Id',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '拥有这张券的用户 Id',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '优惠券的状态，比如未用，已用',
  `shop_id` bigint(20) COMMENT '优惠券适用的门店，冗余字段，方便查找',
  PRIMARY KEY (`id`),
  KEY `idx_template_id` (`template_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='优惠券';