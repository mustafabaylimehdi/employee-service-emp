package emp.employees.service.impl;

import emp.employees.dto.APIResponseDto;
import emp.employees.dto.DepartmentDto;
import emp.employees.entity.Employee;
import emp.employees.service.APIClient;
import lombok.AllArgsConstructor;
import emp.employees.dto.EmployeeDto;
import emp.employees.exception.ResourceNotFoundException;
import emp.employees.mapper.AutoMapperEmployee;
import emp.employees.repository.EmployeeRepository;
import emp.employees.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    //private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = AutoMapperEmployee.MAPPER.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = AutoMapperEmployee.MAPPER.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();
        if(employee == null)
            throw new ResourceNotFoundException("Employee", "employeeId", employeeId);


//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setEmployee(AutoMapperEmployee.MAPPER.mapToEmployeeDto(employee));

        return apiResponseDto;
    }
}
