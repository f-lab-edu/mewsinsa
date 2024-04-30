SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
	`order_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`receiver_address`	varchar(100)	NOT NULL,
	`receiver_name`	varchar(15)	NOT NULL,
	`receiver_phone`	varchar(13)	NOT NULL	COMMENT 'ex) 010-1234-5678',
	`member_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `receipt`;

CREATE TABLE `receipt` (
	`receipt_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`order_id`	bigint	NOT NULL,
	`total_price`	bigint	NOT NULL,
	`payment_method`	varchar(15)	NULL,
	`paid_at`	timestamp	NULL,
	`points`	bigint	NOT NULL,
	`used_points`	bigint	NOT NULL,
	`use_points_in_advance`	tinyint(1)	NOT NULL,
	`is_refunded`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '환불 완료가 되면 1'
);

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
	`product_option_id`	bigint	NOT NULL,
	`member_id`	bigint	NOT NULL,
	`count`	int	NOT NULL
);


DROP TABLE IF EXISTS `product` CASCADE;

CREATE TABLE `product` (
	`product_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`product_name`	varchar(50)	NOT NULL,
	`brand_id`	bigint	NOT NULL,
	`category`	varchar(16)	NOT NULL,
	`subcategory`	varchar(16)	NULL,
	`original_price`	bigint	NOT NULL,
	`product_like`	bigint	NULL,
	`product_click`	bigint	NULL
);

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
	`brand_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`brand_name`	varchar(20)	NOT NULL,
	`brand_like`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
	`coupon_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`coupon_name`	varchar(30)	NULL,
	`coupon_type`	tinyint(1)	NULL	DEFAULT 0	COMMENT 'ex) 1이면 정액할인, 0이면 정률할인',
	`discount_rate`	int	NULL	COMMENT '정액 할인 일시, null',
	`discount_amount`	bigint	NULL	COMMENT '정률 할인 일시, null',
	`started_at`	timestamp	NULL,
	`expired_at`	timestamp	NULL,
	`remaining` bigint UNSIGNED NOT NULL
);

DROP TABLE IF EXISTS `promotion`;

CREATE TABLE `promotion` (
	`promotion_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`promotion_name`	varchar(30)	NOT NULL,
	`promotion_type`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT 'ex) 1이면 정액할인, 0이면 정률할인',
	`discount_rate`	int	NULL,
	`discount_amount`	bigint	NULL,
	`started_at`	timestamp	NOT NULL,
	`expired_at`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`member_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`mewsinsa_id`	varchar(16)	NOT NULL UNIQUE KEY,
	`password`	varchar(44)	NOT NULL,
	`name`	varchar(10)	NOT NULL,
	`nickname`	varchar(16)	NOT NULL,
	`email`	varchar(50)	NOT NULL UNIQUE KEY,
	`phone`	varchar(13)	NOT NULL	COMMENT 'ex) 010-1234-5678',
	`profile_image`	varchar(2083)	NULL	COMMENT 'url',
	`tier_id`	int	NOT NULL,
	`is_admin`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '0: 일반 회원 / 1: 관리자',
	`range_of_notification`	varchar(10)	NOT NULL,
	`points`	bigint	NOT NULL,
	`default_delivery_address_id` bigint NULL DEFAULT NULL
	);

DROP TABLE IF EXISTS `tier`;

CREATE TABLE `tier` (
	`tier_id`	int	NULL PRIMARY KEY,
	`tier_name`	varchar(6)	NULL	COMMENT 'ex) 뉴비, 골드, 플래티넘, 다이아몬드',
	`discount_rate`	int	NOT NULL	DEFAULT 1,
	`member_accumulation_rate`	int	NOT NULL	DEFAULT 0
);

DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
	`ordered_product_id`	bigint	NOT NULL,
	`tracking_number`	varchar(14)	NOT NULL,
	`delivery_company`	varchar(20)	NOT NULL
);

DROP TABLE IF EXISTS `magazine`;

CREATE TABLE `magazine` (
	`magazine_id`	bigint	NULL PRIMARY KEY AUTO_INCREMENT,
	`magazine_title`	varchar(50)	NOT NULL,
	`magazine_content`	text(10000)	NOT NULL,
	`author_id`	bigint	NOT NULL	COMMENT 'member 테이블의 id',
	`magazine_type`	varchar(10)	NOT NULL,
	`thumbnail_image`	varchar(2083)	NOT NULL	COMMENT 'url',
	`view`	bigint	NOT NULL	DEFAULT 0,
	`created_at`	timestamp	NOT NULL,
	`updated_at`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `magazine_reply`;

CREATE TABLE `magazine_reply` (
	`reply_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`reply_content`	text(500)	NULL,
	`created_at`	timestamp	NOT NULL,
	`magazine_id`	bigint	NULL,
	`member_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `search_ranking`;

CREATE TABLE `search_ranking` (
	`search_ranking_id`	bigint	NULL PRIMARY KEY AUTO_INCREMENT,
	`search_word`	varchar(50)	NOT NULL,
	`rank`	int	NULL	COMMENT '300위까지',
	`updated_at`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `product_ranking`;

CREATE TABLE `product_ranking` (
	`category`	varchar(16)	NOT NULL,
	`product_id`	bigint	NOT NULL,
	`rank`	int	NOT NULL	COMMENT '1~300위까지',
	`updated_at`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `product_display`;

CREATE TABLE `product_display` (
	`product_id`	bigint	NOT NULL,
	`original_price`	bigint	NOT NULL,
	`promotion_price`	bigint	NOT NULL	COMMENT '프로모션이 적용된 가격(쿠폰X)',
	`coupon_discount_amount`	bigint	NOT NULL	COMMENT '쿠폰으로 할인 받을 수 있는 금액(ex. 4390)',
	`discount_rate`	int	NOT NULL,
	`has_gift`	tinyint(1)	NOT NULL	COMMENT '0 -> 사은품 없음 / 1 -> 사은품 있음'
);

DROP TABLE IF EXISTS `search`;

CREATE TABLE `search` (
	`search_word`	varchar(50)	NOT NULL,
	`member_id`	bigint	NOT NULL	COMMENT '누가 검색했는지',
	`searched_at`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `product_option`;

CREATE TABLE `product_option` (
	`product_option_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`product_id`	bigint	NOT NULL,
	`product_option_name`	varchar(30)	NOT NULL,
	`stock`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `ordered_product`;

CREATE TABLE `ordered_product` (
	`ordered_product_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`quantity`	bigint	NOT NULL,
	`coupon_id`	bigint	NULL,
	`product_option_id`	bigint	NOT NULL,
	`order_id`	bigint	NOT NULL,
	`piece_price` bigint NOT NULL,
	`is_cancelled` tinyint(1) NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS `magazine_related_brand`;

CREATE TABLE `magazine_related_brand` (
	`magazine_id`	bigint	NULL,
	`brand_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
	`receipt_id`	bigint	NULL DEFAULT NULL,
	`order_id`	bigint	NOT NULL,
	`ordered_product_id`	bigint	NOT NULL,
	`ordered_at`	timestamp	NULL,
	`payment_confirmed_at`	timestamp	NULL,
	`refund_requested_at`	timestamp	NULL,
	`refund_completed_at`	timestamp	NULL,
	`release_requested_at`	timestamp	NULL,
	`release_started_at`	timestamp	NULL,
	`release_completed_at`	timestamp	NULL,
	`delivery_completed_at` timestamp NULL,
	`status`	varchar(30)	NULL	COMMENT 'ex) 결제 완료, 출고 완료, 배송 완료 등등'
);

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
	`tag_content`	varchar(10)	NOT NULL,
	`product_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `promotion_product`;

CREATE TABLE `promotion_product` (
	`product_id`	bigint	NOT NULL,
	`promotion_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `coupon_product`;

CREATE TABLE `coupon_product` (
	`product_id`	bigint	NOT NULL,
	`coupon_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `brand_like`;

CREATE TABLE `brand_like` (
	`brand_id`	bigint	NOT NULL,
	`member_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `delivery_address`;

CREATE TABLE `delivery_address` (
	`delivery_address_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`receiver_name`	varchar(15)	NOT NULL,
	`receiver_phone`	varchar(13)	NOT NULL,
	`receiver_address`	varchar(100)	NOT NULL,
	`member_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `product_like`;

CREATE TABLE `product_like` (
	`member_id`	bigint	NOT NULL,
	`product_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `issued_coupon`;

CREATE TABLE `issued_coupon` (
	`issued_coupon_id`	bigint	NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`is_used`	tinyint(1)	NOT NULL,
	`issued_at`	timestamp	NOT NULL,
	`used_at`	timestamp	NULL,
	`coupon_id`	bigint	NOT NULL,
	`member_id`	bigint	NOT NULL
);

DROP TABLE IF EXISTS `refresh_token`;

CREATE TABLE `refresh_token` (
	`member_id` bigint NOT NULL PRIMARY KEY,
	`token_value` varchar(200) NOT NULL UNIQUE KEY,
	`expiration` timestamp NOT NULL,
	FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

DROP TABLE IF EXISTS `access_token`;

CREATE TABLE `access_token` (
	`member_id` bigint NOT NULL PRIMARY KEY,
	`token_value` varchar(200) NOT NULL UNIQUE KEY,
	`expiration` timestamp NOT NULL,
	FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE
);

SET foreign_key_checks = 1;


ALTER TABLE `cart` ADD CONSTRAINT `PK_CART` PRIMARY KEY (
	`product_option_id`,
	`member_id`
);




ALTER TABLE `product_ranking` ADD CONSTRAINT `PK_PRODUCT_RANKING` PRIMARY KEY (
	`category`,
	`product_id`
);

ALTER TABLE `product_display` ADD CONSTRAINT `PK_PRODUCT_DISPLAY` PRIMARY KEY (
	`product_id`
);

ALTER TABLE `search` ADD CONSTRAINT `PK_SEARCH` PRIMARY KEY (
	`search_word`,
	`member_id`
);



ALTER TABLE `magazine_related_brand` ADD CONSTRAINT `PK_MAGAZINE_RELATED_BRAND` PRIMARY KEY (
	`magazine_id`,
	`brand_id`
);

ALTER TABLE `history` ADD CONSTRAINT `PK_HISTORY` PRIMARY KEY (
	`order_id`,
	`ordered_product_id`
);

ALTER TABLE `tag` ADD CONSTRAINT `PK_TAG` PRIMARY KEY (
	`tag_content`,
	`product_id`
);

ALTER TABLE `promotion_product` ADD CONSTRAINT `PK_PROMOTION_PRODUCT` PRIMARY KEY (
	`product_id`,
	`promotion_id`
);

ALTER TABLE `coupon_product` ADD CONSTRAINT `PK_COUPON_PRODUCT` PRIMARY KEY (
	`product_id`,
	`coupon_id`
);

ALTER TABLE `brand_like` ADD CONSTRAINT `PK_BRAND_LIKE` PRIMARY KEY (
	`brand_id`,
	`member_id`
);



ALTER TABLE `product_like` ADD CONSTRAINT `PK_PRODUCT_LIKE` PRIMARY KEY (
	`member_id`,
	`product_id`
);


ALTER TABLE `order` ADD CONSTRAINT `FK_member_TO_order_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `receipt` ADD CONSTRAINT `FK_order_TO_receipt_1` FOREIGN KEY (
	`order_id`
)
REFERENCES `order` (
	`order_id`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_member_TO_cart_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `member` ADD CONSTRAINT `FK_tier_TO_member_1` FOREIGN KEY (
	`tier_id`
)
REFERENCES `tier` (
	`tier_id`
);

ALTER TABLE `delivery` ADD CONSTRAINT `FK_ordered_product_TO_delivery_1` FOREIGN KEY (
	`ordered_product_id`
)
REFERENCES `ordered_product` (
	`ordered_product_id`
);

ALTER TABLE `magazine_reply` ADD CONSTRAINT `FK_magazine_TO_magazine_reply_1` FOREIGN KEY (
	`magazine_id`
)
REFERENCES `magazine` (
	`magazine_id`
);

ALTER TABLE `search_ranking` ADD CONSTRAINT `FK_search_TO_search_ranking_1` FOREIGN KEY (
	`search_word`
)
REFERENCES `search` (
	`search_word`
);

ALTER TABLE `product_ranking` ADD CONSTRAINT `FK_product_TO_product_ranking_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `product_display` ADD CONSTRAINT `FK_product_TO_product_display_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `search` ADD CONSTRAINT `FK_member_TO_search_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `product_option` ADD CONSTRAINT `FK_product_TO_product_option_1` FOREIGN KEY(
	`product_id`
)
REFERENCES `product` (
	`product_id`
) ON DELETE CASCADE;

ALTER TABLE `ordered_product` ADD CONSTRAINT `FK_product_option_TO_ordered_product_1` FOREIGN KEY (
	`product_option_id`
)
REFERENCES `product_option` (
	`product_option_id`
);

ALTER TABLE `ordered_product` ADD CONSTRAINT `FK_order_TO_ordered_product_1` FOREIGN KEY (
	`order_id`
)
REFERENCES `order` (
	`order_id`
);

ALTER TABLE `magazine_related_brand` ADD CONSTRAINT `FK_magazine_TO_magazine_related_brand_1` FOREIGN KEY (
	`magazine_id`
)
REFERENCES `magazine` (
	`magazine_id`
);

ALTER TABLE `magazine_related_brand` ADD CONSTRAINT `FK_brand_TO_magazine_related_brand_1` FOREIGN KEY (
	`brand_id`
)
REFERENCES `brand` (
	`brand_id`
);


 ALTER TABLE `history` ADD CONSTRAINT `FK_order_TO_history_1` FOREIGN KEY (
	`order_id`
)
REFERENCES `order` (
	`order_id`
);

ALTER TABLE `history` ADD CONSTRAINT `FK_ordered_product_TO_history_1` FOREIGN KEY (
	`ordered_product_id`
)
REFERENCES `ordered_product` (
	`ordered_product_id`
);

ALTER TABLE `tag` ADD CONSTRAINT `FK_product_TO_tag_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `promotion_product` ADD CONSTRAINT `FK_product_TO_promotion_product_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `promotion_product` ADD CONSTRAINT `FK_promotion_TO_promotion_product_1` FOREIGN KEY (
	`promotion_id`
)
REFERENCES `promotion` (
	`promotion_id`
);

ALTER TABLE `coupon_product` ADD CONSTRAINT `FK_product_TO_coupon_product_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `coupon_product` ADD CONSTRAINT `FK_coupon_TO_coupon_product_1` FOREIGN KEY (
	`coupon_id`
)
REFERENCES `coupon` (
	`coupon_id`
);

ALTER TABLE `brand_like` ADD CONSTRAINT `FK_brand_TO_brand_like_1` FOREIGN KEY (
	`brand_id`
)
REFERENCES `brand` (
	`brand_id`
);

ALTER TABLE `brand_like` ADD CONSTRAINT `FK_member_TO_brand_like_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `delivery_address` ADD CONSTRAINT `FK_member_TO_delivery_address_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `product_like` ADD CONSTRAINT `FK_member_TO_product_like_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);

ALTER TABLE `product_like` ADD CONSTRAINT `FK_product_TO_product_like_1` FOREIGN KEY (
	`product_id`
)
REFERENCES `product` (
	`product_id`
);

ALTER TABLE `issued_coupon` ADD CONSTRAINT `FK_coupon_TO_issued_coupon_1` FOREIGN KEY (
	`coupon_id`
)
REFERENCES `coupon` (
	`coupon_id`
);

ALTER TABLE `issued_coupon` ADD CONSTRAINT `FK_member_TO_issued_coupon_1` FOREIGN KEY (
	`member_id`
)
REFERENCES `member` (
	`member_id`
);


