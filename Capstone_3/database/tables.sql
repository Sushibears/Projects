
CREATE DATABASE commerce_data;
USE commerce_data;
CREATE TABLE users (
user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
address_1 VARCHAR(250),
address_2 VARCHAR(250),
city VARCHAR(250),
country VARCHAR(200),
zip_code INT UNSIGNED,
email_address VARCHAR(100),
privilege int
);

CREATE TABLE managers (
manager_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email_address VARCHAR(100),
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
privilege int
);

CREATE TABLE admins (
admin_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
email_address VARCHAR(100),
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
privilege int
);

CREATE TABLE products (
sku INT(3) zerofill NOT NULL AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(300) NOT NULL,
category VARCHAR(700),
price DECIMAL(6,2) UNSIGNED, 
product_description VARCHAR(700), 
qty INT UNSIGNED NOT NULL, 
product_img VARCHAR(300),
alt_text VARCHAR(300)
);

CREATE TABLE inventory (
item_id INT(3) zerofill NOT NULL AUTO_INCREMENT PRIMARY KEY, 
stock_num INT UNSIGNED NOT NULL, 
last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (item_id)
REFERENCES products (sku)
  ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE user_orders (
order_id INT NOT NULL auto_increment PRIMARY KEY, 
customer_id INT NOT NULL, 
created_date DATE, 
shipped_date DATE
);

CREATE TABLE order_details (
order_id INT NOT NULL PRIMARY KEY, 
customer_id INT NOT NULL, 
product_id INT(3) zerofill NOT NULL,
product_name VARCHAR(300) NOT NULL,
qty_ordered INT UNSIGNED NOT NULL,
price_per_item DECIMAL(6,2) UNSIGNED,
foreign key (order_id)
REFERENCES user_orders (order_id),
foreign key (customer_id)
REFERENCES users (user_id)
  ON UPDATE CASCADE
 ON DELETE CASCADE
);

CREATE TABLE user_login (
customer_id INT NOT NULL PRIMARY KEY, 
email VARCHAR(100) UNIQUE,
user_username VARCHAR(300) NOT NULL UNIQUE,
user_password VARCHAR(300) NOT NULL,
privilege int,
foreign key (customer_id)
REFERENCES users (user_id)
  ON UPDATE CASCADE
 ON DELETE CASCADE
);

CREATE TABLE manager_login (
manager_id INT NOT NULL PRIMARY KEY, 
manager_email VARCHAR(100) UNIQUE,
manager_username VARCHAR(300) NOT NULL UNIQUE,
manager_password VARCHAR(300) NOT NULL,
privilege int,
foreign key (manager_id)
REFERENCES managers (manager_id)
  ON UPDATE CASCADE
 ON DELETE CASCADE
);

CREATE TABLE admin_login (
admin_id INT NOT NULL PRIMARY KEY, 
admin_email VARCHAR(100) UNIQUE,
admin_username VARCHAR(300) NOT NULL UNIQUE,
admin_password VARCHAR(300) NOT NULL,
privilege int,
foreign key (admin_id)
REFERENCES admins (admin_id)
  ON UPDATE CASCADE
 ON DELETE CASCADE
);

CREATE TABLE privileges (
privilege_id INT,
privilege_name VARCHAR(20)
);
