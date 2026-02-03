CREATE TABLE likes (
  post_id BIGINT NOT NULL,
  username VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  PRIMARY KEY (post_id, username)
);

CREATE TABLE follows (
  follower_username VARCHAR(50) NOT NULL,
  followee_username VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  PRIMARY KEY (follower_username, followee_username),
  CHECK (follower_username <> followee_username)
);

drop table if exists follows;
drop table if exists likes;

select * from follows;