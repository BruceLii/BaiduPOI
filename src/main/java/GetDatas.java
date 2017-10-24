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
    public static String poiParam = "q=药店&region=新疆&output=json&ak=" + API_KEY + "&page_size=2";


    public static void main(String[] args) throws SQLException {
        List cities = getAllCitiesInProvince("新疆");

//        for (int i = 0; i < pageAll; i++) {
//            System.out.println("—————————————————————————————————————————————");
//            System.out.println("第" + (i + 1) + "页");
//            poiParam = "key=" + API_KEY + "&dtype=json&city=北京市" + "&page=" + (i + 1) + "&cid=1";
//            String poiResult1 = SendGET(poiUrl, poiParam);
//            JSONObject poiJson = JSONObject.fromObject(poiResult1);
//            JSONArray data = poiJson.getJSONObject("result").getJSONArray("data");
//            int size = data.size();
//
//            break;
//            for (int j = 0; j < size; j++) {
//                JSONObject item = data.getJSONObject(j);
//                //id,title,addr,pro,city,tags,ty1,ty2,lng,lat
//                String sql = "insert into bdpoi values('" + item.getString("id") + "','"
//                        + item.getString("title") + "','"
//                        + item.getString("address") + "','"
//                        + item.getString("province") + "','"
//                        + item.getString("city") + "','"
//                        + item.getString("tags") + "',"
//                        + item.getInt("type1") + ","
//                        + item.getInt("type2") + ","
//                        + item.getDouble("lng") + ","
//                        + item.getDouble("lat") + ")";
//                System.out.println("    " + sql);
////                ConOrcl.runSql(sql);
//            }
//        }
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
     * 获取某一省的所有城市名
     *
     * @param province 省份名称
     * @return
     */
    public static List<String> getAllCitiesInProvince(String province) {
        List<String> cities = new ArrayList<String>();
        String poiParam = "q=药店&region=" + province + "&output=json&ak=" + API_KEY + "&page_size=2";
        String result = SendGET(poiUrl, poiParam);

        JSONObject poiJsonroot = JSONObject.fromObject(result);

        poiJsonroot.getJSONObject("results");
        return cities;

    }
}