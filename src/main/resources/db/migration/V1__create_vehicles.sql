CREATE TABLE vehicle (
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    year_manufacture INTEGER NOT NULL,
    description VARCHAR(256) NOT NULL,
    sold BOOLEAN NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);