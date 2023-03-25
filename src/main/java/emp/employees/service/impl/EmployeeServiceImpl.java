package emp.employees.service.impl;

import emp.employees.entity.Employee;
import lombok.AllArgsConstructor;
import emp.employees.dto.EmployeeDto;
import emp.employees.exception.ResourceNotFoundException;
import emp.employees.mapper.AutoMapperEmployee;
import emp.employees.repository.EmployeeRepository;
import emp.employees.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = AutoMapperEmployee.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = AutoMapperEmployee.MAPPER.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(!employee.isPresent())
            throw new ResourceNotFoundException("Employee", "employeeId", employeeId);
        return AutoMapperEmployee.MAPPER.mapToEmployeeDto(employee.get());
    }
}
