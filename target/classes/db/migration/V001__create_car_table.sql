CREATE table car_models (
    id 	BIGINT NOT NULL auto_increment,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    model VARCHAR(255),
    color VARCHAR(255),
    power FLOAT,
    producer VARCHAR(255),
    year_of_manufacture YEAR,
    PRIMARY KEY (id)
)
COLLATE = 'utf8_general_ci'
ENGINE = InnoDB;