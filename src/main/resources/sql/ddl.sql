CREATE DATABASE IF NOT EXISTS electricalsalesmg;
USE electricalsalesmg;

CREATE TABLE roles(
    id INT(8) AUTO_INCREMENT,
    name VARCHAR(255),
    CONSTRAINT pk_id_roles PRIMARY KEY (id)
);

CREATE TABLE users(
    id INT(8) AUTO_INCREMENT,
    enabled BOOLEAN,
    username VARCHAR(12),
    password VARCHAR(255),
    CONSTRAINT pk_id_users PRIMARY KEY (id)
);

CREATE TABLE user_roles(
    role_id INT(8),
    user_id INT(8),
    CONSTRAINT pk_id_user_roles PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_id_user_roles FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_id_roles_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 1. Tabla employee_role: Añadir 'NOT NULL' donde sea necesario
CREATE TABLE employee_role (
    id INT AUTO_INCREMENT,
    role_name VARCHAR(50),
    description TEXT,
    CONSTRAINT pk_id_employee_role PRIMARY KEY (id)
);

-- 2. Tabla country: Eliminar VARCHAR(3) por CHAR(3), ya que es un código estándar de 3 letras
CREATE TABLE country (
    id CHAR(5),
    name VARCHAR(30),
    CONSTRAINT pk_id_country PRIMARY KEY (id)
);

-- 3. Tabla phone_number: Añadir validaciones de NOT NULL
CREATE TABLE phone_number (
    id INT AUTO_INCREMENT,
    country_code VARCHAR(10),
    area_code VARCHAR(10),
    phone_number VARCHAR(15),
    CONSTRAINT pk_id_phone_number PRIMARY KEY (id)
);

-- 4. Tabla category: Añadir 'NOT NULL' a los campos importantes
CREATE TABLE category (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    CONSTRAINT pk_id_category PRIMARY KEY (id)
);

-- 5. Tabla discount: Añadir 'NOT NULL' para asegurar la integridad
CREATE TABLE discount (
    id INT AUTO_INCREMENT,
    description VARCHAR(255),
    percentage DECIMAL(5,2),
    CONSTRAINT pk_id_discount PRIMARY KEY (id)
);

-- 6. Tabla city: Añadir restricciones de ON DELETE para mantener integridad referencial
CREATE TABLE city (
    id CHAR(5) NOT NULL,
    name VARCHAR(50),
    country_id CHAR(5),
    CONSTRAINT pk_id_city PRIMARY KEY (id),
    CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES country(id) 
    ON DELETE CASCADE ON UPDATE CASCADE
);


-- 8. Tabla branch: Mejorar relación con city
CREATE TABLE branch (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    city_id CHAR(5),
    CONSTRAINT pk_id_branch PRIMARY KEY (id),
    CONSTRAINT fk_city_branch FOREIGN KEY (city_id) REFERENCES city(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 9. Tabla supplier: Mejorar integridad y evitar nulos
CREATE TABLE supplier (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(100),
    phone_id INT,
    city_id CHAR(5),
    CONSTRAINT pk_id_supplier PRIMARY KEY (id),
    CONSTRAINT fk_phone_supplier FOREIGN KEY (phone_id) REFERENCES phone_number(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_city_supplier FOREIGN KEY (city_id) REFERENCES city(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 10. Tabla customer: Añadir restricciones a los campos importantes
CREATE TABLE customer (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(100),
    phone_id INT,
    city_id CHAR(5),
    
    CONSTRAINT pk_id_customer PRIMARY KEY (id),
    CONSTRAINT fk_phone_customer FOREIGN KEY (phone_id) REFERENCES phone_number(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_city_customer FOREIGN KEY (city_id) REFERENCES city(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 11. Tabla product: Mejorar relación con otras tablas y evitar valores nulos
CREATE TABLE product (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    description TEXT,
    price DECIMAL(10, 2),
    category_id INT,
    discount_id INT,
    CONSTRAINT pk_id_product PRIMARY KEY (id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_discount FOREIGN KEY (discount_id) REFERENCES discount(id)
    ON DELETE SET NULL ON UPDATE CASCADE
);

-- 12. Tabla product_supplier: Añadir restricción 'ON DELETE CASCADE'
CREATE TABLE product_supplier (
    product_id INT,
    supplier_id INT,
    CONSTRAINT pk_id_product_supplier PRIMARY KEY (product_id, supplier_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id) REFERENCES supplier(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 13. Tabla warehouse: Ajustar restricciones
CREATE TABLE warehouse (
    id INT AUTO_INCREMENT,
    name VARCHAR(50),
    branch_id INT,
    city_id CHAR(5),
    CONSTRAINT pk_id_warehouse PRIMARY KEY (id),
    CONSTRAINT fk_branch_warehouse FOREIGN KEY (branch_id) REFERENCES branch(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_city_warehouse FOREIGN KEY (city_id) REFERENCES city(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 14. Tabla product_warehouse: Añadir integridad referencial adecuada
CREATE TABLE product_warehouse (
    id INT AUTO_INCREMENT,
    product_id INT,
    warehouse_id INT,
    stock INT CHECK (stock >= 0),
    CONSTRAINT pk_id_product_warehouse PRIMARY KEY (id),
    CONSTRAINT fk_product_warehouse FOREIGN KEY (product_id) REFERENCES product(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_warehouse_product FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 15. Tabla employee: Evitar valores nulos y mejorar relaciones
CREATE TABLE employee (
    id INT AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_id INT,
    hire_date DATE,
    salary DECIMAL(10, 2) CHECK (salary >= 0),
    role_id INT,
    branch_id INT,
    CONSTRAINT pk_id_employee PRIMARY KEY (id),
    CONSTRAINT fk_phone_employee FOREIGN KEY (phone_id) REFERENCES phone_number(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES employee_role(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_branch_employee FOREIGN KEY (branch_id) REFERENCES branch(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 16. Tabla inventory_movements: Mejorar relaciones y añadir restricciones
CREATE TABLE inventory_movements (
    id INT AUTO_INCREMENT,
    product_id INT,
    warehouse_id INT,
    movement_date DATE,
    quantity INT CHECK (quantity >= 0),
    movement_type VARCHAR(5),
    employee_id INT,
    CONSTRAINT pk_id_inventory_movements PRIMARY KEY (id),
    CONSTRAINT fk_product_inventory FOREIGN KEY (product_id) REFERENCES product(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_warehouse_inventory FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_employee_inventory FOREIGN KEY (employee_id) REFERENCES employee(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 17. Tabla purchase: Añadir integridad referencial
CREATE TABLE purchase (
    id INT AUTO_INCREMENT,
    purchase_date DATE,
    total_amount DECIMAL(10, 2) CHECK (total_amount >= 0),
    supplier_id INT,
    employee_id INT,
    branch_id INT,
    CONSTRAINT pk_id_purchase PRIMARY KEY (id),
    CONSTRAINT fk_supplier_purchase FOREIGN KEY (supplier_id) REFERENCES supplier(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_employee_purchase FOREIGN KEY (employee_id) REFERENCES employee(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_branch_purchase FOREIGN KEY (branch_id) REFERENCES branch(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 18. Tabla purchase_details: Añadir restricciones adecuadas
CREATE TABLE purchase_details (
    id INT AUTO_INCREMENT,
    purchase_id INT,
    product_id INT,
    quantity INT CHECK (quantity >= 0),
    unit_price DECIMAL(10, 2) CHECK (unit_price >= 0),
    CONSTRAINT pk_id_purchase_details PRIMARY KEY (id),
    CONSTRAINT fk_purchase_detail_purchase FOREIGN KEY (purchase_id) REFERENCES purchase(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_product_purchase_detail FOREIGN KEY (product_id) REFERENCES product(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 19. Tabla sale: Asegurar la integridad y evitar valores nulos
CREATE TABLE sale (
    id INT AUTO_INCREMENT,
    sale_date DATE,
    total_amount DECIMAL(10, 2) CHECK (total_amount >= 0),
    customer_id INT,
    employee_id INT ,
    branch_id INT ,
    CONSTRAINT pk_id_sale PRIMARY KEY (id),
    CONSTRAINT fk_customer_sale FOREIGN KEY (customer_id) REFERENCES customer(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_employee_sale FOREIGN KEY (employee_id) REFERENCES employee(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_branch_sale FOREIGN KEY (branch_id) REFERENCES branch(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- 20. Tabla sale_details: Añadir restricciones adecuadas
CREATE TABLE sale_details (
    id INT AUTO_INCREMENT,
    sale_id INT,
    product_id INT,
    quantity INT CHECK (quantity >= 0),
    unit_price DECIMAL(10, 2)  CHECK (unit_price >= 0),
    CONSTRAINT pk_id_sale_details PRIMARY KEY (id),
    CONSTRAINT fk_sale_detail_sale FOREIGN KEY (sale_id) REFERENCES sale(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_product_sale_detail FOREIGN KEY (product_id) REFERENCES product(id)
    ON DELETE CASCADE ON UPDATE CASCADE
);