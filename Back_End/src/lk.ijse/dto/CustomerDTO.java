package lk.ijse.dto;

public class CustomerDTO {

    private String cutomerId;
    private String customerName;
    private String customerAddress;
    private int customerSalary;

    public CustomerDTO(){}

    public CustomerDTO(String cutomerId, String customerName, String customerAddress, int customerSalary) {
        this.cutomerId = cutomerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
    }

    public String getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(String cutomerId) {
        this.cutomerId = cutomerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(int customerSalary) {
        this.customerSalary = customerSalary;
    }
}
