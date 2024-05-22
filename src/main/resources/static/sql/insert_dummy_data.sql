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
