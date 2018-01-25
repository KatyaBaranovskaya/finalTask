package by.baranovskaya.entity;


import java.sql.Date;
import java.util.Arrays;

public class Order extends Entity {
    private int idOrder;
    private int idClient;
    private int roomNumber;
    private Date arrivalDate;
    private Date departureDate;
    private int noPersons;
    private int classApartment;
    private double price;
    private String status;
    private Service[] services;
    private Client client;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNoPersons() {
        return noPersons;
    }

    public void setNoPersons(int noPersons) {
        this.noPersons = noPersons;
    }

    public int getClassApartment() {
        return classApartment;
    }

    public void setClassApartment(int classApartment) {
        this.classApartment = classApartment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Service[] getServices() {
        return services;
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        if (idClient != order.idClient) return false;
        if (roomNumber != order.roomNumber) return false;
        if (noPersons != order.noPersons) return false;
        if (classApartment != order.classApartment) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (arrivalDate != null ? !arrivalDate.equals(order.arrivalDate) : order.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(order.departureDate) : order.departureDate != null)
            return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(services, order.services)) return false;
        return client != null ? client.equals(order.client) : order.client == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idOrder;
        result = 31 * result + idClient;
        result = 31 * result + roomNumber;
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + noPersons;
        result = 31 * result + classApartment;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(services);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", roomNumber=" + roomNumber +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", noPersons=" + noPersons +
                ", classApartment=" + classApartment +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", services=" + Arrays.toString(services) +
                ", client=" + client +
                '}';
    }
}
