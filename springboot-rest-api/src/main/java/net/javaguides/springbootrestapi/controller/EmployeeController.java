package net.javaguides.springbootrestapi.controller;


import net.javaguides.springbootrestapi.bean.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("mfeNewHire")
public class EmployeeController {

    ResponseEntity responseEntity;
    static List<Employee> employees;

    static {
        employees= new ArrayList<>();
        employees.add(new Employee("100","Mathi","Male"));
        employees.add(new Employee("101","Seenu","Male"));
        employees.add(new Employee("102","Maran","Male"));
        employees.add(new Employee("103","Indhu","Female"));
        employees.add(new Employee("104","Neerai","Female"));
    }



    @GetMapping("getEmployee")
    public ResponseEntity<List<Employee>> getEmployee(){
    //responseEntity = new ResponseEntity(employee);
        //return   EmployeeController.employees;
        return  ResponseEntity.ok().header("custom-header","Mathi")
                        .body(EmployeeController.employees);
    }


    //{id} : URI TEMPLATE ID
    @GetMapping("getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable("id") int empId){
        //int index = Integer.parseInt(id);
        System.out.println("Index : "+empId );
        //return EmployeeController.employees.get(empId);
        return ResponseEntity.ok().header("custom-header","Mathi")
                        .body(EmployeeController.employees.get(empId));
    }

    //http://localhost:8080/getEmployeeQueryParam?id=100&name=Mathi&sex=Male
    @GetMapping("/getEmployeeQueryParam")
    public ResponseEntity<Employee> getEmployeeQueryParam(@RequestParam String id,
                                          @RequestParam String name,
                                          @RequestParam String sex){


        //return new Employee(id,name,sex);
        return ResponseEntity.ok().header("custom-header","Mathi")
                .body(new Employee(id,name,sex));
    }


    @PostMapping("createEmployee")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
        String ssn = employee.getSsn();
        String name = employee.getFirstName();
        String sex = employee.getSex();

        EmployeeController.employees.add(new Employee(ssn,name,sex));

        return new ResponseEntity<>(employee.toString(),HttpStatus.CREATED);
    }
    @PutMapping("updateEmployee/{id}")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable String id){
        String newName = employee.getFirstName();
         EmployeeController.employees.stream().filter(testEmp -> id.equals(testEmp.getSsn()))
                .forEach(emp -> emp.setFirstName(newName));
        return new ResponseEntity<>("Update : "+employee.toString(),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteEmployee/{name}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String name){

        //;
        Set<Employee> empSet =  EmployeeController.employees.stream().filter(testEmp -> name.equals(testEmp.getFirstName())).collect(Collectors.toSet());
        System.out.println(empSet);
        for (Employee employee : empSet){
            EmployeeController.employees.remove(employee);
        }

        return new ResponseEntity<>("Deleted Emp with Name : "+name,HttpStatus.GONE);
    }
}

