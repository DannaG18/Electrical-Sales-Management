USE electricalsalesmg;

INSERT INTO roles (name) VALUES ('Admin');
INSERT INTO roles (name) VALUES ('Viewer');

INSERT INTO users (enabled, username, password) VALUES (TRUE, 'alice', 'password123');
INSERT INTO users (enabled, username, password) VALUES (TRUE, 'bob', 'password456');
INSERT INTO users (enabled, username, password) VALUES (FALSE, 'charlie', 'password789');

INSERT INTO user_roles (role_id, user_id) VALUES (1, 1);  -- Admin role for alice
INSERT INTO user_roles (role_id, user_id) VALUES (2, 2);  -- Viewer role for bob
INSERT INTO user_roles (role_id, user_id) VALUES (1, 3);  -- Admin role for charlie (even though charlie is disabled)



-- 1. Tabla employee_role
INSERT INTO employee_role (role_name, description) VALUES
('Manager', 'Oversees store operations'),
('Sales Associate', 'Handles customer sales and inquiries'),
('Warehouse Staff', 'Manages inventory and stock');

-- 2. Tabla country
INSERT INTO country (id, name) VALUES
('US', 'United States'),
('MX', 'Mexico'),
('CA', 'Canada');

-- 3. Tabla phone_number
INSERT INTO phone_number (country_code, area_code, phone_number) VALUES
('+1', '212', '5551234'),
('+52', '55', '5555678'),
('+1', '416', '5558765');

-- 4. Tabla category
INSERT INTO category (name) VALUES
('Electronics'),
('Home Appliances'),
('Tools');

-- 5. Tabla discount
INSERT INTO discount (description, percentage) VALUES
('Seasonal Sale', 10.00),
('Clearance', 20.00),
('Black Friday', 30.00);

-- 6. Tabla city
INSERT INTO city (id, name, country_id) VALUES
('NYC', 'New York', 'US'),
('CDMX', 'Mexico City', 'MX'),
('TO', 'Toronto', 'CA');

-- 8. Tabla branch
INSERT INTO branch (name, city_id) VALUES
('Main Store', 'NYC'),
('Outlet Store', 'CDMX'),
('Warehouse', 'TO');

-- 9. Tabla supplier
INSERT INTO supplier (name, email, phone_id, city_id) VALUES
('Tech Supplier Inc.', 'contact@techsupplier.com', 1, 'NYC'),
('HomeGoods Ltd.', 'info@homegoods.com', 2, 'CDMX'),
('Tool Masters', 'sales@toolmasters.com', 3, 'TO');

-- 10. Tabla customer
INSERT INTO customer (name, email, phone_id, city_id) VALUES
('Alice Smith', 'alice.smith@example.com', 1, 'NYC'),
('Bob Johnson', 'bob.johnson@example.com', 2, 'CDMX'),
('Carol White', 'carol.white@example.com', 3, 'TO');

-- 11. Tabla product
INSERT INTO product (name, description, price, category_id, discount_id) VALUES
('Smartphone', 'Latest model smartphone', 699.99, 1, 1),
('Washing Machine', 'Energy efficient washing machine', 499.99, 2, 2),
('Drill', 'Heavy duty drill', 89.99, 3, 3);

-- 12. Tabla product_supplier
INSERT INTO product_supplier (product_id, supplier_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- 13. Tabla warehouse
INSERT INTO warehouse (name, branch_id, city_id) VALUES
('Main Warehouse', 3, 'NYC'),
('Backup Warehouse', 3, 'CDMX');

-- 14. Tabla product_warehouse
INSERT INTO product_warehouse (product_id, warehouse_id, stock) VALUES
(1, 1, 100),
(2, 1, 50),
(3, 2, 200);

-- 15. Tabla employee
INSERT INTO employee (first_name, last_name, email, phone_id, hire_date, salary, role_id, branch_id) VALUES
('John', 'Doe', 'john.doe@example.com', 1, '2024-01-15', 50000.00, 1, 1),
('Jane', 'Roe', 'jane.roe@example.com', 2, '2024-02-01', 35000.00, 2, 2),
('Emily', 'Johnson', 'emily.johnson@example.com', 3, '2024-03-10', 40000.00, 3, 3);
('Emily', 'Johnson', 'emily.johnson@example.com', 1, '2024-03-10', 40000.00, 3, 3);

-- 16. Tabla inventory_movements
INSERT INTO inventory_movements (product_id, warehouse_id, movement_date, quantity, movement_type, employee_id) VALUES
(1, 1, '2024-08-01', 10, 'IN', 1),
(2, 1, '2024-08-02', 5, 'OUT', 2),
(3, 2, '2024-08-03', 20, 'IN', 3);

-- 17. Tabla purchase
INSERT INTO purchase (purchase_date, total_amount, supplier_id, employee_id, branch_id) VALUES
('2024-08-10', 1000.00, 1, 1, 1),
('2024-08-12', 2000.00, 2, 2, 2);

-- 18. Tabla purchase_details
INSERT INTO purchase_details (purchase_id, product_id, quantity, unit_price) VALUES
(1, 1, 10, 699.99),
(1, 2, 5, 499.99),
(2, 3, 20, 89.99);

-- 19. Tabla sale
INSERT INTO sale (sale_date, total_amount, customer_id, employee_id, branch_id) VALUES
('2024-08-15', 769.99, 1, 1, 1),
('2024-08-16', 899.98, 2, 2, 2);

-- 20. Tabla sale_details
INSERT INTO sale_details (sale_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 699.99),
(1, 2, 1, 69.99),
(2, 3, 10, 89.99);
