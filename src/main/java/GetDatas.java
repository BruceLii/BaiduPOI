import model.StoreModel;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyonglin on 2017/10/24.
 */

public class GetDatas {
    public static String API_KEY = "ss0MByLM7edMu1jkedXGCP8QPC9579PP";
    public static String poiUrl = "http://api.map.baidu.com/place/v2/search";

    public static final int PAGE_SIZE = 20;

    public static int total_count = 0;


    public static void main(String[] args) throws SQLException {
        long startTime = System.currentTimeMillis();

        //乌鲁木齐
        Point lb = new Point(87.186354, 43.695967);
        Point rt = new Point(87.870504, 43.984768);
//        Point lb = new Point(72.863484, 35.761619);
//        Point rt = new Point(91.003219, 49.11624);

        Rectangle rectangle = new Rectangle(lb, rt);
        rectangle.currentAreaName = "乌鲁木齐市";
        getByBounds(rectangle);


        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        StringBuilder sb = new StringBuilder();
        sb.append(costTime % 1000).append("毫秒");
        if (costTime > 1000) {
            costTime = costTime / 1000;
            sb.insert(0, "秒");
            sb.insert(0, costTime % 60);
        }
        if (costTime > 60) {
            costTime = costTime / 60;
            sb.insert(0, "分钟");
            sb.insert(0, costTime % 60);
        }
        if (costTime > 60) {
            costTime = costTime / 60;
            sb.insert(0, "小时");
            sb.insert(0, costTime % 60);
        }
        System.out.println("任务耗时" + sb.toString());
    }

    public static String SendGET(String url, String param) {
        String result = "";//访问返回结果
        BufferedReader read = null;//读取访问结果
        try {
            //创建url
            URL realurl = new URL(url + "?" + param);
            //打开连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    /**
     * 获取某一个城市的药店信息
     *
     * @param cityname
     * @return
     */
    public static List<City> getDrugList(String cityname) {

        List<City> cities = new ArrayList<City>();
        String poiParam = "q=药店&region=" + cityname + "&scope=2&output=json&ak=" + API_KEY + "&page_size=20&page_num=19";
        String result = SendGET(poiUrl, poiParam);

        JSONObject poiJsonroot = JSONObject.fromObject(result);

        List<JSONObject> citys = poiJsonroot.getJSONArray("results");


        for (int i = 0; i < citys.size(); i++) {
            String storeName = citys.get(i).getString("name");
            String address = citys.get(i).getString("address");

            String lat = citys.get(i).getJSONObject("location").getString("lat");
            String lng = citys.get(i).getJSONObject("location").getString("lng");

        }
        return cities;

    }

    /**
     * 获取某一省的所有城市名
     *
     * @param province 省份名称
     * @return
     */
    public static List<City> getAllCitiesInProvince(String province) {
        List<City> cities = new ArrayList<City>();
        String poiParam = "q=药店&region=" + province + "&output=json&ak=" + API_KEY + "&page_size=2";
        String result = SendGET(poiUrl, poiParam);

        JSONObject poiJsonroot = JSONObject.fromObject(result);

        List<JSONObject> citys = poiJsonroot.getJSONArray("results");


        for (int i = 0; i < citys.size(); i++) {
            City tem = new City();
            tem.name = citys.get(i).getString("name");
            tem.num = citys.get(i).getString("num");

            cities.add(tem);
        }
        return cities;

    }

    public static void getByBounds(String leftbottom, String righttop) {
        String poiParam = "q=药店&output=json&ak=" + API_KEY + "&page_size=20&bounds=" + leftbottom + "," + righttop;
        String result = SendGET(poiUrl, poiParam);

        int total = 0;
        if (total >= 400) {
//                四分法切片

        } else {

        }
    }

    public static void getByBounds(Rectangle rectangle) {
        if (rectangle == null) return;

        Point lb = rectangle.leftbottom;
        Point rt = rectangle.rightTop;

        String leftbottom = lb.latitude + "," + lb.longitude;//先纬度，再经度
        String righttop = rt.latitude + "," + rt.longitude;//先纬度，再经度

        int currentPageIndex = 0;

        String poiParam = "q=药店&output=json&ak=" + API_KEY + "&page_size=20&bounds=" + leftbottom + "," + righttop + "&page_num=" + currentPageIndex;
        String result = SendGET(poiUrl, poiParam);
        JSONObject poiJsonroot = JSONObject.fromObject(result);

        int total = poiJsonroot.getInt("total");
        if (total >= 400) {
//        if (total < 400) {//test
//                四分法切片，切片后进入递归
            double half_long = (lb.longitude + rt.longitude) / 2;
            double half_lat = (lb.latitude + rt.latitude) / 2;

//            新规划出的5个点，
            Point centerPoint = new Point(half_long, half_lat);//中心点，

            Point p1 = new Point(lb.longitude, half_lat);//左中
            Point p2 = new Point(half_long, rt.latitude);//中上
            Point p3 = new Point(half_long, lb.latitude);//中下
            Point p4 = new Point(half_long, half_lat);//右中


            List<Rectangle> areas = new ArrayList<Rectangle>();
            areas.add(new Rectangle(rectangle.currentAreaName, lb, centerPoint));//
            areas.add(new Rectangle(rectangle.currentAreaName, centerPoint, rt));//
            areas.add(new Rectangle(rectangle.currentAreaName, p1, p2));//
            areas.add(new Rectangle(rectangle.currentAreaName, p3, p4));//

            for (int i = 0; i < areas.size(); i++) {
                Rectangle current = areas.get(i);
                getByBounds(current);//进入递归
            }

        } else {
            //进入数采集，
            int pages = total / PAGE_SIZE + 1;
            List<StoreModel> storeModelList = new ArrayList<StoreModel>(20);

            System.out.println("**************当前切片 " + "     " + rectangle.toString());

            for (int i = 0; i < pages; i++) {
                String pageparam = "q=药店&output=json&ak=" + API_KEY + "&page_size=20&bounds=" + leftbottom + "," + righttop + "&page_num=" + currentPageIndex;
                currentPageIndex++;//开始第二页的请求
                String r = SendGET(poiUrl, pageparam);
                JSONObject page = JSONObject.fromObject(r);
                addPageData(page, storeModelList, rectangle.currentAreaName);
            }
            //先保存数据
//            FileUtils.createCSV(storeModelList);
            storeModelList.clear();
            storeModelList = null;
            currentPageIndex = 0;//set as default.
        }
    }

    public static void addPageData(JSONObject page, List<StoreModel> storeModelList, String currentAreaName) {
        List<JSONObject> stores = page.getJSONArray("results");
        for (int k = 0; k < stores.size(); k++) {
            StoreModel storeModel = new StoreModel();

            storeModel.cityname = currentAreaName;
            storeModel.storeName = stores.get(k).getString("name");
            storeModel.address = stores.get(k).getString("address");
            storeModel.longitude = stores.get(k).getJSONObject("location").getString("lng");
            storeModel.latitude = stores.get(k).getJSONObject("location").getString("lat");

            storeModelList.add(storeModel);

            System.out.println("总计数： " + (total_count++) + "     " + storeModel.toString());
        }

    }


}