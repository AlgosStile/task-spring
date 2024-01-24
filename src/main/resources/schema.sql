CREATE TABLE IF NOT EXISTS tasklist (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    description varchar(50) NOT NULL,
    status varchar(50) NOT NULL,
    creationDate varchar(50) NOT NULL
);