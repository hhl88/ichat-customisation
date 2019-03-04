/*DROP TABLE IF EXISTS choose_chat_ui;
DROP TABLE IF EXISTS choose_layout;
DROP TABLE IF EXISTS ichat_ui;
DROP TABLE IF EXISTS chat_layout;
DROP TABLE IF EXISTS demand_info;
DROP TABLE IF EXISTS iagent_server;
DROP TABLE IF EXISTS ichat_user;*/


CREATE TABLE IF NOT EXISTS ichat_user
(
  id       VARCHAR(10)  NOT NULL,
  email    VARCHAR(128) NOT NULL,
  password VARCHAR(64)  NOT NULL,
  locked   CHAR(1),
  deleted  CHAR(1),
  CONSTRAINT ichat_user_pk_id PRIMARY KEY (id),
  CONSTRAINT ichat_user_uk_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS iagent_server
(
  id        VARCHAR(10)  NOT NULL,
  address   VARCHAR(128) NOT NULL,
  user_api  VARCHAR(100) NOT NULL,
  password  VARCHAR(64)  NOT NULL,
  client_id VARCHAR(128) NOT NULL,
  secret    VARCHAR(128) NOT NULL,
  locked    CHAR(1),
  deleted   CHAR(1),
  CONSTRAINT iagent_server_pk_id PRIMARY KEY (id),
  CONSTRAINT iagent_server_nn_id CHECK (id IS NOT NULL),
  CONSTRAINT iagent_server_nn_url CHECK (address IS NOT NULL),
  CONSTRAINT iagent_server_nn_user_api CHECK (user_api IS NOT NULL),
  CONSTRAINT iagent_server_nn_clientid CHECK (client_id IS NOT NULL),
  CONSTRAINT iagent_server_nn_secret CHECK (secret IS NOT NULL)
);

CREATE TABLE IF NOT EXISTS demand_info
(
  id          VARCHAR(10) NOT NULL,
  demand_info JSON        NOT NULL,

  CONSTRAINT demand_info_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ichat_ui
(
  id               VARCHAR(10)  NOT NULL,
  user_id          VARCHAR(10)  NOT NULL,
  name             VARCHAR(100),
  iagent_server_id VARCHAR(10),
  cloud_id         VARCHAR(10),
  url_path         VARCHAR(128) NOT NULL,
  demand_info_id   VARCHAR(10),
  connection_type  TINYINT  NOT NULL,

  CONSTRAINT ichat_ui_pk_id PRIMARY KEY (id),
  CONSTRAINT ichat_ui_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT ichat_ui_nn_id CHECK (id IS NOT NULL),
  CONSTRAINT ichat_ui_fk_iaserver_id FOREIGN KEY (iagent_server_id) REFERENCES iagent_server (id),
  CONSTRAINT ichat_ui_fk_dinfo_id FOREIGN KEY (demand_info_id) REFERENCES demand_info (id),

  CONSTRAINT ichat_ui_nn_url_path CHECK (url_path IS NOT NULL),

  CONSTRAINT ichat_ui_uk_iaserver_id UNIQUE (id, iagent_server_id, url_path, demand_info_id),
  CONSTRAINT ichat_ui_uk_cloud UNIQUE (id, cloud_id, url_path, demand_info_id)
);

CREATE TABLE IF NOT EXISTS chat_layout
(
  id              VARCHAR(10) NOT NULL,
  name            VARCHAR(100),
  user_id         VARCHAR(10) NOT NULL,
  display_type    TINYINT,
  text_input_type TINYINT,
  button_type     TINYINT,
  logo            LONGBLOB,
  background_img  LONGBLOB,
  background_type TINYINT,
  font_family     VARCHAR(50),
  font_size       VARCHAR(8),
  font_styles     BIT(4),
  bubble_style    JSON,
  locked          CHAR(1),
  deleted         CHAR(1),
  CONSTRAINT chat_layout_pk_id PRIMARY KEY (id),
  CONSTRAINT chat_layout_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT chat_layout_nn_id CHECK (id IS NOT NULL)
);


CREATE TABLE IF NOT EXISTS choose_layout
(
  user_id        VARCHAR(10) NOT NULL,
  chat_layout_id VARCHAR(10) NOT NULL,
  CONSTRAINT usr_layout_pk PRIMARY KEY (user_id),
  CONSTRAINT usr_layout_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT usr_layout_fk_clayout_id FOREIGN KEY (chat_layout_id) REFERENCES chat_layout (id)
);


CREATE TABLE IF NOT EXISTS choose_chat_ui
(
  user_id     VARCHAR(10) NOT NULL,
  ichat_ui_id VARCHAR(10) NOT NULL,

  CONSTRAINT usr_cui_pk_id PRIMARY KEY (user_id, ichat_ui_id),

  CONSTRAINT usr_cui_fk_usr_id FOREIGN KEY (user_id) REFERENCES ichat_user (id),
  CONSTRAINT usr_cui_fk_cui_id FOREIGN KEY (ichat_ui_id) REFERENCES ichat_ui (id),

  CONSTRAINT usr_cui_nn_usr_id CHECK (user_id IS NOT NULL)
);
