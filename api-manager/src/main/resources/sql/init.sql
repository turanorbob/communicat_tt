CREATE TABLE `api_collection` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_collection_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api` (
  `id` varchar(32) NOT NULL,
  `collection_id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `method` varchar(10) NOT NULL,
  `url` varchar(255) NOT NULL,
  `has_params` tinyint(1) NOT NULL,
  `has_authorization` tinyint(1) NOT NULL,
  `has_headers` tinyint(1) NOT NULL,
  `has_body` tinyint(1) NOT NULL,
  `has_pre_request_script` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_params` (
  `id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `key` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_params_un` (`api_id`,`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_headers` (
  `id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `key` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_headers_un` (`key`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_body` (
  `id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `type` varchar(30) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_body_un` (`type`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_pre_request_script` (
  `id` varchar(32) NOT NULL,
  `api_id` varchar(32) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_pre_request_script_un` (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_env_category` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_env_category_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_env` (
  `id` varchar(32) NOT NULL,
  `category_id` varchar(32) NOT NULL,
  `variable` varchar(100) NOT NULL,
  `initial_value` varchar(100) NOT NULL,
  `current_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_env_un` (`category_id`,`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
