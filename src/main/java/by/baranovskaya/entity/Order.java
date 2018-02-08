package by.baranovskaya.entity;


import java.sql.Date;

public class Order extends Entity {
    private int idOrder;
    private User user;
    private int roomNumber;
    private Date arrivalDate;
    private Date departureDate;
    private int noAdults;
    private int noChildren;
    private String typeApartment;
    private String breakfast;
    private double price;
    private String status;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getNoAdults() {
        return noAdults;
    }

    public void setNoAdults(int noAdults) {
        this.noAdults = noAdults;
    }

    public int getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(int noChildren) {
        this.noChildren = noChildren;
    }

    public String getTypeApartment() {
        return typeApartment;
    }

    public void setTypeApartment(String typeApartment) {
        this.typeApartment = typeApartment;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        if (roomNumber != order.roomNumber) return false;
        if (noAdults != order.noAdults) return false;
        if (noChildren != order.noChildren) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (arrivalDate != null ? !arrivalDate.equals(order.arrivalDate) : order.arrivalDate != null) return false;
        if (departureDate != null ? !departureDate.equals(order.departureDate) : order.departureDate != null)
            return false;
        if (typeApartment != null ? !typeApartment.equals(order.typeApartment) : order.typeApartment != null)
            return false;
        if (breakfast != null ? !breakfast.equals(order.breakfast) : order.breakfast != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idOrder;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + (arrivalDate != null ? arrivalDate.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        result = 31 * result + noAdults;
        result = 31 * result + noChildren;
        result = 31 * result + (typeApartment != null ? typeApartment.hashCode() : 0);
        result = 31 * result + (breakfast != null ? breakfast.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", user=" + user +
                ", roomNumber=" + roomNumber +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", noAdults=" + noAdults +
                ", noChildren=" + noChildren +
                ", typeApartment='" + typeApartment + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
