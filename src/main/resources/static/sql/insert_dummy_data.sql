-- company 테이블 더미 데이터
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP001', 1, 101, '123-45-67890', NOW(), '2025-01-01 00:00:00');
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP002', 2, 102, '223-45-67890', NOW(), '2025-02-01 00:00:00');
INSERT INTO company (company_code, company_address_id, president_id, business_registration_number, service_start_date, service_end_date) VALUES ('COMP003', 3, 103, '323-45-67890', NOW(), '2025-03-01 00:00:00');

-- department 테이블 더미 데이터
INSERT INTO department ( department_name, department_code) VALUES ( 'Sales', 'SALES001');
INSERT INTO department (department_name, department_code) VALUES ( 'Marketing', 'MARK001');
INSERT INTO department ( department_name, department_code) VALUES ( 'HR', 'HR002');
INSERT INTO department ( department_name, department_code) VALUES ( 'Finance', 'FIN002');
INSERT INTO department ( department_name, department_code) VALUES ( 'IT', 'IT003');
INSERT INTO department ( department_name, department_code) VALUES ( 'Support', 'SUP003');

-- manager address 테이블 더미 데이터 삽입
-- INSERT INTO manager_address (address_level1id, address_level2id, address_level3) VALUES (1, 1, '123 Main St, Suite 100');

-- manager 테이블 더미 데이터 삽입
-- INSERT INTO manager (password_error_count, company_id, created_date, department_id, manager_address_id, manager_entrance_date, manager_resign_date, updated_date, manager_code, manager_email, manager_name, manager_password, manager_phone_number, manager_position, manager_rank, manager_state, gender) VALUES (0, 1, NOW(), 1, 1, NOW(), NULL, NOW(), 'MNG123', 'manager1@example.com', '김덕수', 'password123', '010-1234-1234', '운영', '대표', 'Active', 'MALE');

