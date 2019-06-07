GRANT ALL PRIVILEGES ON DATABASE seed TO seed_user;

CREATE TABLE USERS (
  id text primary key,
  email text not null,
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

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO seed_user;