CREATE TABLE todo_list (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(45),
    create_at DATETIME,
    modified_at DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE todo_item (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(45),
    complete TINYINT(1),
    create_at DATETIME,
    modified_at DATETIME,
    todo_list_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (todo_list_id) REFERENCES todo_list(id)
);