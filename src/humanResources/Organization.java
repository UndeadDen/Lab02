package humanResources;

public class Organization {
    private String name;
    private Department[] departments;
    private int size;
    private static final int DEFAULT_ORGANIZATION_SIZE = 10;

    public Organization(String name){
        this.name = name;
        this.departments = new Department[DEFAULT_ORGANIZATION_SIZE];
    }

    public Organization(String name, Department[] departments){
        this.name = name;
        this.departments = departments;
        for (int i = 0; i < departments.length; i++)
            if (departments[i] != null)
                this.size++;
    }

    public void addDepartment (Department department){
        if (size < departments.length){
            this.departments[size] = department;
            this.size++;
        } else {
            Department[] newDepartments = new Department[departments.length * 2];
            System.arraycopy(departments, 0, newDepartments, 0 , size);
            this.departments = newDepartments;
            this.departments[size] = department;
            this.size++;
        }
    }

    public boolean fireDepartment (String departmentName){
        for (int i = 0; i< size; i++)
            if (this.departments[i].getName().equals(departmentName)) {
                for (int j = i; j < size - 1; j++) {
                    this.departments[j] = this.departments[j+1];
                }
                this.departments[size] = null;
                size--;
                return true;
            }
        return false;
    }

    public Department getDepartment (String departmentName){
        for (int i = 0; i< size; i++)
            if (this.departments[i].getName().equals(departmentName)) {
                return departments[i];
            }
            return null;
    }

    public Department[] getDepartments (){
        Department[] newDepartments = new Department[size];
        System.arraycopy(departments, 0, newDepartments, 0, size);
        return newDepartments;
    }

    public int getSize(){
        return size;
    }

    public int employeesQuantity(){
        int numberOfEmployess = 0;
        for (int i = 0; i< size; i++){
            numberOfEmployess += departments[i].getSize();
        }
        return numberOfEmployess;
    }

    public int employeesQuantity(String jobTitle){
        int numberOfEmployess = 0;
        for (int i = 0; i< size; i++){
            numberOfEmployess += departments[i].employeesQuantity(jobTitle);
        }
        return numberOfEmployess;
    }

    public Employee bestEmployee(){
        if (size > 0) {
            Employee employeeWithHighestSalary = departments[0].bestEmployee();
            for (int i = 1; i < this.size - 1; i++) {
                if (departments[i].bestEmployee().getSalary() > employeeWithHighestSalary.getSalary()) {
                    employeeWithHighestSalary = departments[i].bestEmployee();
                }
            }
            return employeeWithHighestSalary;
        } else return null;
    }

    public Department getEmployeeDepartment(String firstName, String secondName){
        for (int i = 0; i< size; i++){
            if (departments[i].findEmployee(firstName, secondName))
                return departments[i];
        }
        return null;
    }
}
