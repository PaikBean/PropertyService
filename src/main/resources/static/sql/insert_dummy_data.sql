-- company 테이블 더미 데이터
INSERT INTO company (company_code, company_name, biz_number, president_name, company_email, company_number, service_start_date, service_end_date, created_date, updated_date) VALUES ('COMP001', 'Company 1', '123-45-67890', 'President 1', 'contact@company1.com', '123-456-7890', NOW(), '2025-01-01 00:00:00', NOW(), NOW());
INSERT INTO company (company_code, company_name, biz_number, president_name, company_email, company_number, service_start_date, service_end_date, created_date, updated_date) VALUES ('COMP002', 'Company 2', '223-45-67890', 'President 2', 'contact@company2.com', '223-456-7890', NOW(), '2025-02-01 00:00:00', NOW(), NOW());
INSERT INTO company (company_code, company_name, biz_number, president_name, company_email, company_number, service_start_date, service_end_date, created_date, updated_date) VALUES ('COMP003', 'Company 3', '323-45-67890', 'President 3', 'contact@company3.com', '323-456-7890', NOW(), '2025-03-01 00:00:00', NOW(), NOW());

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

