package model;

/**
 * Created by liyonglin on 2017/10/25.
 */
public class StoreModel {
    public String cityname;
    public String storeName;
    public String address;

    public String longitude;
    public String latitude;


    public StoreModel(String cityname, String storeName, String address, String longti, String latitude) {
        this.cityname = cityname;
        this.storeName = storeName;
        this.address = address;
        this.longitude = longti;
        this.latitude = latitude;
    }

    public StoreModel() {

    }

    @Override
    public String toString() {
        return cityname + "," +
                storeName + "," +
                address + "," +
                longitude + "," +
                latitude;
    }
}
