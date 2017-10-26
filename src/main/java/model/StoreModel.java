package model;

/**
 * Created by liyonglin on 2017/10/25.
 */
public class StoreModel {
    /**
     * 简化地址，新疆维吾尔自治区乌鲁木齐市天山区赛马场路
     */
    public String formatted_address;

    public String storeName;
    public String address;

    public String longitude;
    public String latitude;


    public String locationDetailURL;


    public StoreModel(String formatted_address, String storeName, String address, String longti, String latitude, String locationDetailURL) {
        this.formatted_address = formatted_address;
        this.storeName = storeName;
        this.address = address;
        this.longitude = longti;
        this.latitude = latitude;
        this.locationDetailURL = locationDetailURL;
    }

    public StoreModel() {

    }

    @Override
    public String toString() {
        return formatted_address + "," +
                storeName + "," +
                address + "," +
                longitude + "," +
                latitude;
    }
}
