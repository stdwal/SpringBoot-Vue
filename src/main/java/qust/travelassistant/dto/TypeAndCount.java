package qust.travelassistant.dto;

public class TypeAndCount {

    private String type;

    private int count;

    public TypeAndCount() {
    }

    public TypeAndCount(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TypeAndCount{" +
                "type='" + type + '\'' +
                ", count=" + count +
                '}';
    }
}
