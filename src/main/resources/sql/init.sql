GRANT ALL PRIVILEGES ON DATABASE seed TO seed_user;

CREATE TABLE IF NOT EXISTS users (
  id text primary key,
  email text not null unique,
  user_name text,
  user_role text,
  created_by text not null,
  created_at timestamp not null,
  updated_by text not null,
  updated_at timestamp not null
);

COMMENT ON TABLE users IS 'ユーザー';
COMMENT ON COLUMN users.id IS 'ID';
COMMENT ON COLUMN users.email IS 'Email';
COMMENT ON COLUMN users.user_name IS 'ユーザー名';
COMMENT ON COLUMN users.user_role IS 'ユーザーロール';
COMMENT ON COLUMN users.created_by IS '登録者';
COMMENT ON COLUMN users.created_at IS '登録日時';
COMMENT ON COLUMN users.updated_by IS '更新者';
COMMENT ON COLUMN users.updated_at IS '更新日時';

CREATE TABLE IF NOT EXISTS auth (
  id text primary key references USERS(id),
  password text not null,
  created_by text not null,
  created_at timestamp not null,
  updated_by text not null,
  updated_at timestamp not null
);

COMMENT ON TABLE auth IS '認証';
COMMENT ON COLUMN auth.id IS 'ユーザーID';
COMMENT ON COLUMN auth.password IS 'パスワード';
COMMENT ON COLUMN auth.created_by IS '登録者';
COMMENT ON COLUMN auth.created_at IS '登録日時';
COMMENT ON COLUMN auth.updated_by IS '更新者';
COMMENT ON COLUMN auth.updated_at IS '更新日時';

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO seed_user;