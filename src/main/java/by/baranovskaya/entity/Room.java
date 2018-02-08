package by.baranovskaya.entity;

public class Room extends Entity {
    private int roomNumber;
    private TypeRoom typeRoom;
    private String status;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
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

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (typeRoom != null ? !typeRoom.equals(room.typeRoom) : room.typeRoom != null) return false;
        return status != null ? status.equals(room.status) : room.status == null;
    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + (typeRoom != null ? typeRoom.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", typeRoom=" + typeRoom +
                ", status='" + status + '\'' +
                '}';
    }
}
