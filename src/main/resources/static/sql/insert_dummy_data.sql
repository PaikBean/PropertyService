-- company 테이블 더미 데이터
INSERT INTO company_address (address_level1id, address_level2id, address_level3) VALUES (1, 1, '123 Main St, Suite 100');
INSERT INTO company_address (address_level1id, address_level2id, address_level3) VALUES (2, 2, '456 Elm St, Suite 200');
INSERT INTO company_address (address_level1id, address_level2id, address_level3) VALUES (3, 3, '789 Oak St, Suite 300');

INSERT INTO company (company_address_id, company_code, company_name, president_name, business_registration_number, service_start_date, service_end_date, created_date, updated_date, company_email) VALUES (1, 'COMP001', 'Company 1', 'President 1', '123-45-67890', NOW(), '2025-01-01 00:00:00', NOW(), NOW(), 'contact@company1.com');
INSERT INTO company (company_address_id, company_code, company_name, president_name, business_registration_number, service_start_date, service_end_date, created_date, updated_date, company_email) VALUES (2, 'COMP002', 'Company 2', 'President 2', '223-45-67890', NOW(), '2025-02-01 00:00:00', NOW(), NOW(), 'contact@company2.com');
INSERT INTO company (company_address_id, company_code, company_name, president_name, business_registration_number, service_start_date, service_end_date, created_date, updated_date, company_email) VALUES (3, 'COMP003', 'Company 3', 'President 3', '323-45-67890', NOW(), '2025-03-01 00:00:00', NOW(), NOW(), 'contact@company3.com');

-- department 테이블 더미 데이터
INSERT INTO department (company_id, department_name, department_code) VALUES (1, 'Sales', 'SALES001');
INSERT INTO department (company_id, department_name, department_code) VALUES (1, 'Marketing', 'MARK001');
INSERT INTO department (company_id, department_name, department_code) VALUES (2, 'HR', 'HR002');
INSERT INTO department (company_id, department_name, department_code) VALUES (2, 'Finance', 'FIN002');
INSERT INTO department (company_id, department_name, department_code) VALUES (3, 'IT', 'IT003');
INSERT INTO department (company_id, department_name, department_code) VALUES (3, 'Support', 'SUP003');

-- manager address 테이블 더미 데이터 삽입
-- INSERT INTO manager_address (address_level1id, address_level2id, address_level3) VALUES (1, 1, '123 Main St, Suite 100');

-- manager 테이블 더미 데이터 삽입
-- INSERT INTO manager (password_error_count, company_id, created_date, department_id, manager_address_id, manager_entrance_date, manager_resign_date, updated_date, manager_code, manager_email, manager_name, manager_password, manager_phone_number, manager_position, manager_rank, manager_state, gender) VALUES (0, 1, NOW(), 1, 1, NOW(), NULL, NOW(), 'MNG123', 'manager1@example.com', '김덕수', 'password123', '010-1234-1234', '운영', '대표', 'Active', 'MALE');

