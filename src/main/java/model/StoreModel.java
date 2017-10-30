package model;

/**
 * Created by liyonglin on 2017/10/25.
 */
public class StoreModel {
    /**
     * 简化地址，新疆维吾尔自治区乌鲁木齐市天山区赛马场路
     */
    public String formatted_address = "";

    public String storeName = "";

    public String longitude = "";
    public String latitude = "";


    public String locationDetailURL;

    public Area areaInfo = null;


    public StoreModel() {

    }

    @Override
    public String toString() {
        return "StoreModel{" +
                "formatted_address='" + formatted_address + '\'' +
                ", storeName='" + storeName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", locationDetailURL='" + locationDetailURL + '\'' +
                ", areaInfo=" + areaInfo +
                '}';
    }
}
