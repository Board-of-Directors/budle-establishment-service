CREATE TABLE detached_image
(
    id      UUID PRIMARY KEY,
    created TIMESTAMP NOT NULL DEFAULT NOW()
)
