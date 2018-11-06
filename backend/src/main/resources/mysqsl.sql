DROP TABLE IF EXISTS ichat_ui_setting;
DROP TABLE IF EXISTS choose_chat_ui;
DROP TABLE IF EXISTS choose_layout;
DROP TABLE IF EXISTS chat_layout;
DROP TABLE IF EXISTS demand_info;
DROP TABLE IF EXISTS iagent_server;
DROP TABLE IF EXISTS ichat_ui;
DROP TABLE IF EXISTS ichat_user;


CREATE TABLE IF NOT EXISTS ichat_user
(
  id       BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT,
  email    VARCHAR(128) NOT NULL,
  password VARCHAR(64),
  locked   CHAR(1),
  deleted  CHAR(1),
  CONSTRAINT ichat_user_pk_id PRIMARY KEY (id),
  CONSTRAINT ichat_user_uk_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS ichat_ui
(
  id              BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT,
  name            VARCHAR(100),
  connection_type VARCHAR(64) NOT NULL,
  CONSTRAINT ichat_ui_pk_id PRIMARY KEY (id),
  CONSTRAINT ichat_ui_nn_id CHECK (id IS NOT NULL)
);

CREATE TABLE IF NOT EXISTS iagent_server
(
  id        BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT,
  address   VARCHAR(128) NOT NULL,
  user_api  VARCHAR(100) NOT NULL,
  password  VARCHAR(64),
  clientid VARCHAR(128) NOT NULL,
  secret    VARCHAR(128) NOT NULL,
  locked    CHAR(1),
  deleted   CHAR(1),
  CONSTRAINT iagent_server_pk_id PRIMARY KEY (id),
  CONSTRAINT iagent_server_nn_id CHECK (id IS NOT NULL),
  CONSTRAINT iagent_server_nn_url CHECK (address IS NOT NULL),
  CONSTRAINT iagent_server_nn_user_api CHECK (user_api IS NOT NULL),
  CONSTRAINT iagent_server_nn_clientid CHECK (clientid IS NOT NULL),
  CONSTRAINT iagent_server_nn_secret CHECK (secret IS NOT NULL)
);

CREATE TABLE IF NOT EXISTS demand_info
(
  id          BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT,
  demand_info JSON NOT NULL,

  CONSTRAINT demand_info_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS chat_layout
(
  id              BIGINT (20) UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id         BIGINT (20) UNSIGNED NOT NULL,
  display_type    CHAR(1),
  text_input_type CHAR(1),
  button_type     CHAR(1),
  logo            BLOB,
  background_img  BLOB,
  background_type CHAR(1),
  font_family     VARCHAR(50),
  font_size       SMALLINT,
  font_styles     BIT(4),
  locked          CHAR(1),
  deleted         CHAR(1),
  CONSTRAINT chat_layout_pk_id PRIMARY KEY (id),
  CONSTRAINT chat_layout_nn_id CHECK (id IS NOT NULL)
);


CREATE TABLE IF NOT EXISTS choose_layout
(
  user_id        BIGINT (20) UNSIGNED NOT NULL,
  chat_layout_id BIGINT (20) UNSIGNED NOT NULL,
  name           VARCHAR(100),
  CONSTRAINT usr_layout_pk PRIMARY KEY (user_id),
  CONSTRAINT usr_layout_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT usr_layout_fk_clayout_id FOREIGN KEY (chat_layout_id) REFERENCES chat_layout (id)
);


CREATE TABLE IF NOT EXISTS choose_chat_ui
(
  user_id     BIGINT (20) UNSIGNED NOT NULL,
  ichat_ui_id BIGINT (20) UNSIGNED NOT NULL,

  CONSTRAINT usr_cui_pk_id PRIMARY KEY (user_id, ichat_ui_id),

  CONSTRAINT usr_cui_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT usr_cui_fk_cui_id FOREIGN KEY (ichat_ui_id) REFERENCES ichat_ui (id),

  CONSTRAINT usr_cui_nn_usr_id CHECK (user_id IS NOT NULL)
);

CREATE TABLE IF NOT EXISTS ichat_ui_setting
(
  ichat_ui_id      BIGINT (20) UNSIGNED NOT NULL,
  iagent_server_id BIGINT (20) UNSIGNED,
  cloud_id         BIGINT (20) UNSIGNED,
  url_path         VARCHAR(128) NOT NULL,
  demand_info_id   BIGINT (20) UNSIGNED,

  CONSTRAINT ichat_setting_fk_ichat_id FOREIGN KEY (ichat_ui_id) REFERENCES ichat_ui (id),
  CONSTRAINT ichat_setting_fk_iaserver_id FOREIGN KEY (iagent_server_id) REFERENCES iagent_server (id),
  CONSTRAINT ichat_setting_fk_dinfo_id FOREIGN KEY (demand_info_id) REFERENCES demand_info (id),

  CONSTRAINT ichat_setting_nn_ichat_id CHECK (ichat_ui_id IS NOT NULL),
  CONSTRAINT ichat_setting_nn_url_path CHECK (url_path IS NOT NULL),

  CONSTRAINT ichat_setting_uk_iaserver_id UNIQUE (ichat_ui_id, iagent_server_id, url_path, demand_info_id),
  CONSTRAINT ichat_setting_uk_cloud UNIQUE (ichat_ui_id, cloud_id, url_path, demand_info_id)
);


ALTER TABLE ichat_user
AUTO_INCREMENT = 500;

ALTER TABLE ichat_ui
AUTO_INCREMENT = 600;

ALTER TABLE iagent_server
AUTO_INCREMENT = 700;

ALTER TABLE demand_info
AUTO_INCREMENT = 800;

ALTER TABLE chat_layout
AUTO_INCREMENT = 900;