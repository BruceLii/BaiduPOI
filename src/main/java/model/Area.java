package model;

/**
 * 新政政区域划分
 * Created by liyonglin on 2017/10/26.
 */
public class Area {
    public static final String CHINA = "中国";

    public static final String XINJIANG = "新疆维吾尔自治区";
    
    public static final String WLMQ = "乌鲁木齐市";


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


}
