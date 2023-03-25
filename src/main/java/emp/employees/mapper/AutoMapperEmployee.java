package emp.employees.mapper;

import emp.employees.dto.EmployeeDto;
import emp.employees.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapperEmployee {
    AutoMapperEmployee MAPPER = Mappers.getMapper(AutoMapperEmployee.class);
    Employee mapToEmployee(EmployeeDto employeeDto);
    EmployeeDto mapToEmployeeDto(Employee employee);
}
