CREATE TABLE IF NOT EXISTS `Client`(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `birthDay` DATE NOT NULL,
    `cpf` VARCHAR(15) NOT NULL,
    `profession` VARCHAR(100),
    `address` VARCHAR(200),
    `email` VARCHAR(200),
    primary key (id)
);
CREATE TABLE IF NOT EXISTS SavingsAccount(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    `client_id` BIGINT(20) NOT NULL,
    `balance` DECIMAL(20) NOT NULL DEFAULT 0.0,
    primary key (id),
    CONSTRAINT fk_SavingsAccount_client_id FOREIGN KEY (client_id) REFERENCES Client(id)
);
CREATE TABLE IF NOT EXISTS CheckingAccount(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    `client_id` BIGINT(20) NOT NULL,
    `balance` DECIMAL(20) NOT NULL DEFAULT 0.0,
    primary key (id),
    CONSTRAINT fk_CheckingAccount_client_id FOREIGN KEY (client_id) REFERENCES Client(id)
);