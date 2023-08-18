CREATE TABLE recipe_manager_user
(
    id VARCHAR(36) UNIQUE NOT NULL PRIMARY KEY
);

ALTER TABLE recipe_index
    ADD user_id VARCHAR(36) NOT NULL;

ALTER TABLE recipe_index
    ADD CONSTRAINT fk_recipe_index_users FOREIGN KEY (user_id) REFERENCES recipe_manager_user (id);