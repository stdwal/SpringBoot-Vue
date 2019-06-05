package qust.travelassistant.dto;

public class CityAndCount {

    private String city;

    private int count;

    public CityAndCount() {
    }

    public CityAndCount(String city, int count) {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CityAndCount{" +
                "city='" + city + '\'' +
                ", count=" + count +
                '}';
    }
}
