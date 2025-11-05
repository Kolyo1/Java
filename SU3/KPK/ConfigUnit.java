package KPK;

public class ConfigUnit {
    int id;
    String key;
    String value;

    public ConfigUnit(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;   
    }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %s", id, key, value);
    }

}
