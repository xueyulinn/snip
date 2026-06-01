CREATE
DATABASE IF NOT EXISTS snip DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE
snip;
CREATE TABLE short_url_mapping
(
    short_code      CHAR(8)       NOT NULL,
    original_url    VARCHAR(2048) NOT NULL,
    origin_url_hash CHAR(32)      NOT NULL,
    create_time     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expire_time     TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status          SMALLINT      NOT NULL DEFAULT 1 COMMENT '0. cancelled 1. working 2. expired',
    PRIMARY KEY (short_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


