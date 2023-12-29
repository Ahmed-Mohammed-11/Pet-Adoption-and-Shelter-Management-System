-- MySQL Script generated by MySQL Workbench
-- Tue Dec 26 16:57:06 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pet_adoption
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pet_adoption
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pet_adoption` DEFAULT CHARACTER SET utf8mb4;
USE `pet_adoption` ;

-- -----------------------------------------------------
-- Table `pet_adoption`.`adopter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`adopter` (
  `user_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_adopter_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_adoption`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`adoption_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`adoption_record` (
  `adopter_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  `status` ENUM("PENDING", "ACCEPTED", "REJECTED") NOT NULL,
  `acceptance_date` DATETIME NULL,
  INDEX `fk_adoption_record_adopter1_idx` (`adopter_id` ASC) VISIBLE,
  INDEX `fk_adoption_record_pet1_idx` (`pet_id` ASC) VISIBLE,
  PRIMARY KEY (`adopter_id`, `pet_id`),
  CONSTRAINT `fk_adoption_record_adopter1`
    FOREIGN KEY (`adopter_id`)
    REFERENCES `pet_adoption`.`adopter` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_adoption_record_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet_adoption`.`pet` (`pet_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `pet_adoption`.`document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`document` (
  `pet_id` INT NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `document_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`document_id`, `pet_id`),
  UNIQUE INDEX `document_id_UNIQUE` (`document_id` ASC) VISIBLE,
  CONSTRAINT `fk_document_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet_adoption`.`pet` (`pet_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `pet_adoption`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`image` (
  `pet_id` INT NOT NULL,
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(45) NOT NULL,
  INDEX `fk_images_pet1_idx` (`pet_id` ASC) VISIBLE,
  PRIMARY KEY (`image_id`, `pet_id`),
  UNIQUE INDEX `image_id_UNIQUE` (`image_id` ASC) VISIBLE,
  CONSTRAINT `fk_images_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet_adoption`.`pet` (`pet_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`pet` (
    `pet_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NULL,
    `species` VARCHAR(45) NOT NULL,
    `age` INT NOT NULL,
    `gender` VARCHAR(1) NOT NULL,
    `description` VARCHAR(1000) NULL,
    `breed` VARCHAR(45) NOT NULL,
    `house_training` TINYINT NOT NULL,
    `behaviour` ENUM('PLAYFUL', 'CALM', 'CURIOUS', 'TIMID', 'AFFECTIONATE') NULL,
    `shelter_id` INT NOT NULL,
    `is_fertilised` TINYINT NOT NULL DEFAULT 1,
    `is_vaccinated` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`pet_id`, `shelter_id`),
    UNIQUE INDEX `id_UNIQUE` (`pet_id` ASC) VISIBLE,
    INDEX `fk_pet_shelter1_idx` (`shelter_id` ASC) VISIBLE,
    CONSTRAINT `fk_pet_shelter1`
        FOREIGN KEY (`shelter_id`)
            REFERENCES `pet_adoption`.`shelter` (`shelter_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`shelter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`shelter` (
    `shelter_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `location` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `shelter_mgr_id` INT NOT NULL,
    PRIMARY KEY (`shelter_id`, `shelter_mgr_id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `shelter_id_UNIQUE` (`shelter_id` ASC) VISIBLE,
    INDEX `fk_shelter_shelter_manager1_idx` (`shelter_mgr_id` ASC) VISIBLE,
    CONSTRAINT `fk_shelter_shelter_manager1`
    FOREIGN KEY (`shelter_mgr_id`)
    REFERENCES `pet_adoption`.`shelter_manager` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`shelter_manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`shelter_manager` (
    `user_id` INT NOT NULL,
    INDEX `fk_admin_user_idx` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`user_id`),
    CONSTRAINT `fk_admin_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_adoption`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`staff_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`staff_member` (
    `user_id` INT NOT NULL,
    `shelter_id` INT NOT NULL,
    `staff_role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_id`),
    INDEX `fk_admin_user_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_staff_member_shelter1_idx` (`shelter_id` ASC) VISIBLE,
    CONSTRAINT `fk_admin_user0`
    FOREIGN KEY (`user_id`)
    REFERENCES `pet_adoption`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_staff_member_shelter1`
    FOREIGN KEY (`shelter_id`)
    REFERENCES `pet_adoption`.`shelter` (`shelter_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_adoption`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet_adoption`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `role` ENUM("STAFF", "ADOPTER", "SHELTER_MANAGER") NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`userName` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
