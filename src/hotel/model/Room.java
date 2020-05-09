package hotel.model;

public class Room extends CommonModel {

    private String roomNumber;
    private String roomSituation;
    private Float price;
    private Integer humanCount;
    private String roomType;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomSituation() {
        return roomSituation;
    }

    public void setRoomSituation(String roomSituation) {
        this.roomSituation = roomSituation;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getHumanCount() {
        return humanCount;
    }

    public void setHumanCount(Integer humanCount) {
        this.humanCount = humanCount;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomSituation=" + roomSituation + ", price=" + price
                + ", humanCount=" + humanCount + ", roomType=" + roomType + "]";
    }

}
