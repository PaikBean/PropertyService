-- company 테이블 더미 데이터
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP001', 1, 101, '123-45-67890', NOW(), '2025-01-01 00:00:00');
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP002', 2, 102, '223-45-67890', NOW(), '2025-02-01 00:00:00');
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP003', 3, 103, '323-45-67890', NOW(), '2025-03-01 00:00:00');

-- department 테이블 더미 데이터
INSERT INTO department (company_id, department_name, department_code) VALUES (1, 'Sales', 'SALES001');
INSERT INTO department (company_id, department_name, department_code) VALUES (1, 'Marketing', 'MARK001');
INSERT INTO department (company_id, department_name, department_code) VALUES (2, 'HR', 'HR002');
INSERT INTO department (company_id, department_name, department_code) VALUES (2, 'Finance', 'FIN002');
INSERT INTO department (company_id, department_name, department_code) VALUES (3, 'IT', 'IT003');
INSERT INTO department (company_id, department_name, department_code) VALUES (3, 'Support', 'SUP003');

-- Owner 테이블에 더미 데이터 삽입
INSERT INTO owner (owner_id, owner_name, owner_relation, owner_phone_number) VALUES (1, 'John Doe', 'Owner', '123-456-7890');
INSERT INTO owner (owner_id, owner_name, owner_relation, owner_phone_number) VALUES (2, 'Jane Smith', 'Co-Owner', '987-654-3210');

-- Building 테이블에 더미 데이터
INSERT INTO building (building_id, owner_id) VALUES (1, 1);
INSERT INTO building (building_id, owner_id) VALUES (2, 2);

-- BuildingRemark 테이블에 더미 데이터
INSERT INTO building_remark (remark_id, building_id, remark) VALUES (1, 1, 'This is a remark for building 1');
INSERT INTO building_remark (remark_id, building_id, remark) VALUES (2, 2, 'This is a remark for building 2');

-- BuildingImage 테이블에 더미 데이터
INSERT INTO building_image (building_image_id, building_id, image_name, file_size, file_path, file_url) VALUES (1, 1, 'image1.jpg', 1024, '/images/image1.jpg', 'http://example.com/images/image1.jpg');
INSERT INTO building_image (building_image_id, building_id, image_name, file_size, file_path, file_url) VALUES (2, 2, 'image2.jpg', 2048, '/images/image2.jpg', 'http://example.com/images/image2.jpg');

-- BuildingAddress 테이블에 더미 데이터
INSERT INTO building_address (building_address_id, building_id, address_level1_id, address_level2_id, address_level3) VALUES (1, 1, 101, 201, 'Street 1, City A');
INSERT INTO building_address (building_address_id, building_id, address_level1_id, address_level2_id, address_level3) VALUES (2, 2, 102, 202, 'Street 2, City B');

