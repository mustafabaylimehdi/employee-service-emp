package emp.employees.service;

import emp.employees.dto.APIResponseDto;
import emp.employees.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
