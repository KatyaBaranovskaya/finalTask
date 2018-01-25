package by.baranovskaya.entity;

public class Room extends Entity {
    private int roomNumber;
    private String typeRoom;
    private int classApartment;
    private int capacity;
    private double price;
    private String status;
    private String picture;
    private String description;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public int getClassApartment() {
        return classApartment;
    }

    public void setClassApartment(int classApartment) {
        this.classApartment = classApartment;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (classApartment != room.classApartment) return false;
        if (capacity != room.capacity) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (typeRoom != null ? !typeRoom.equals(room.typeRoom) : room.typeRoom != null) return false;
        if (status != null ? !status.equals(room.status) : room.status != null) return false;
        if (picture != null ? !picture.equals(room.picture) : room.picture != null) return false;
        return description != null ? description.equals(room.description) : room.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomNumber;
        result = 31 * result + (typeRoom != null ? typeRoom.hashCode() : 0);
        result = 31 * result + classApartment;
        result = 31 * result + capacity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", typeRoom='" + typeRoom + '\'' +
                ", classApartment=" + classApartment +
                ", capacity=" + capacity +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
