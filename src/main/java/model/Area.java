package model;

/**
 * 新政政区域划分
 * Created by liyonglin on 2017/10/26.
 */
public class Area {
    public static final String CHINA = "中国";

    public static final String XINJIANG = "新疆维吾尔自治区";

    public static final String WLMQ = "乌鲁木齐市";
    public static final String KLMY = "克拉玛依市";
    public static final String TLF = "吐鲁番市";
    public static final String HM = "哈密市";
    public static final String HKS = "阿克苏地区";
    public static final String KS = "喀什地区";
    public static final String HT = "和田地区";
    public static final String CJ = "昌吉回族自治州";
    public static final String BETLMG = "博尔塔拉蒙古自治州";
    public static final String BYGL = "巴音郭楞蒙古自治州";
    public static final String KMLSKEKM = "克孜勒苏柯尔克孜自治州";


    public static final String YL = "伊犁哈萨克自治州";



    /**
     * 地区综合命名
     */
    public String formatted_address = "";
    /**
     * 国家
     */
    public String country = "";
    /**
     * 省份，自治区，直辖市
     */
    public String province = "";
    /**
     * 市
     */
    public String city = "";
    /**
     * 区，县
     */
    public String district = "";
    /**
     * 街道
     */
    public String street = "";


    @Override
    public String toString() {
        return "Area{" +
                "country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
