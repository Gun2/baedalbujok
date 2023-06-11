
-- 테이블 baedalbujok.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
    `id` varchar(50) NOT NULL,
    `name` varchar(200) NOT NULL COMMENT '역할 명',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='역할';



-- 테이블 baedalbujok.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '사용자 아이디',
    `password` varchar(255) DEFAULT NULL COMMENT '패스워드',
    `name` varchar(20) NOT NULL COMMENT '닉네임',
    `role_id` varchar(50) DEFAULT NULL COMMENT '역할 id',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_username_uindex` (`username`),
    KEY `member_role_id_fk` (`role_id`),
    CONSTRAINT `member_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='사용자 정보';



-- 테이블 baedalbujok.gifticon 구조 내보내기
CREATE TABLE IF NOT EXISTS `gifticon` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `amount` bigint(20) DEFAULT NULL COMMENT '리워드 포인트',
    `use` char(1) NOT NULL DEFAULT '0' COMMENT '사용유무',
    `use_member_id` int(11) DEFAULT NULL COMMENT '사용자 member id',
    `serial_number` varchar(16) NOT NULL COMMENT '기프티콘 번호',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `gifticon_serial_number_uindex` (`serial_number`),
    KEY `gifticon_user_id_fk` (`use_member_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='기프티콘';



-- 테이블 baedalbujok.menu 구조 내보내기
CREATE TABLE IF NOT EXISTS `menu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL COMMENT '음식명',
    `price` bigint(20) DEFAULT NULL COMMENT '가격',
    `desc` text DEFAULT NULL COMMENT '메뉴 설명',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    `image_name` varchar(1024) DEFAULT NULL COMMENT '메뉴 이미지 파일 이름',
    `member_id` int(11) DEFAULT NULL COMMENT '메뉴를 생성한 member id',
    `delete` char(1) DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `menu_member_id_fk` (`member_id`),
    CONSTRAINT `menu_member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='배달메뉴';



-- 테이블 baedalbujok.order 구조 내보내기
CREATE TABLE IF NOT EXISTS `order` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `member_id` int(11) DEFAULT NULL COMMENT '주문자 member id',
    `menu_id` int(11) DEFAULT NULL COMMENT '주문 menu id',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    `price` bigint(20) DEFAULT NULL COMMENT '주문가격',
    PRIMARY KEY (`id`),
    KEY `order_member_id_fk` (`member_id`),
    KEY `order_menu_id_fk` (`menu_id`),
    CONSTRAINT `order_member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
    CONSTRAINT `order_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='주문';



-- 테이블 baedalbujok.point 구조 내보내기
CREATE TABLE IF NOT EXISTS `point` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `member_id` int(11) DEFAULT NULL COMMENT '포인트와 연관된 사용자 member id',
    `balance` bigint(20) NOT NULL DEFAULT 0 COMMENT '잔액',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `point_member_id_fk` (`member_id`),
    CONSTRAINT `point_member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='포인트';



-- 테이블 baedalbujok.point_transaction 구조 내보내기
CREATE TABLE IF NOT EXISTS `point_transaction` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `point_id` int(11) DEFAULT NULL COMMENT 'point id',
    `balance` bigint(20) NOT NULL COMMENT '당시 잔액',
    `amount` bigint(20) DEFAULT NULL COMMENT '포인트 증감 차감 액',
    `target_point_id` int(11) NOT NULL COMMENT '선물하기, 선물받기 관련 사용자',
    `created_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `point_transaction_point_id_fk` (`point_id`),
    KEY `point_transaction_point_id_fk_2` (`target_point_id`),
    CONSTRAINT `point_transaction_point_id_fk` FOREIGN KEY (`point_id`) REFERENCES `point` (`id`),
    CONSTRAINT `point_transaction_point_id_fk_2` FOREIGN KEY (`target_point_id`) REFERENCES `point` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='포인트 사용 내역';



-- 테이블 baedalbujok.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `content` text DEFAULT NULL COMMENT '리뷰내용',
    `member_id` int(11) DEFAULT NULL COMMENT '작성자 member id',
    `menu_id` int(11) DEFAULT NULL COMMENT '리뷰 음식 menu id',
    `evaluation` int(3) DEFAULT NULL COMMENT '평점',
    `created_date` datetime DEFAULT NULL,
    `updated_date` datetime DEFAULT NULL,
    `image_name` varchar(1024) DEFAULT NULL COMMENT '이미지 파일 이름',
    PRIMARY KEY (`id`),
    KEY `review_member_id_fk` (`member_id`),
    KEY `review_menu_id_fk` (`menu_id`),
    CONSTRAINT `review_member_id_fk` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
    CONSTRAINT `review_menu_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='음식 리뷰';

