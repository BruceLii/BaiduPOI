package utils;

import model.Point;
import model.Rectangle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liyonglin on 2017/10/27.
 * <p>
 * 由于百度地图poi 的矩形区域检索 经纬度最大跨度不能超过，一定范围，经纬度跨度 不能超越2  （超越2后 接口返回数据会非常少，）
 * <p>
 * <p>
 * 所以，目前的思路是，先把要检索的区域，划分成 n个小片，，没给小片的纬度跨度是2*2 ，然后加上残留的小片，
 */

public class Cosnt {
    /**
     * 百度地图的最大跨度
     */
    public static final double DIS = 0.2;

    public static void main(String[] args) {
//        整个新疆区域
        List<Rectangle> xinjiang = getCuttedAreaList(new Rectangle(new Point(73.269518, 34.727283), new Point(97.259593, 49.18224)));
//        List<Rectangle> xinjiang = getCuttedAreaList(new Rectangle(new Point(0.0, 0.0), new Point(2, 4)));


    }


    public static List<Rectangle> getCuttedAreaList(Rectangle rectangle) {
        List<Rectangle> list = new ArrayList<Rectangle>(20);


        int pieces_long = getPieces(rectangle.leftbottom.longitude, rectangle.rightTop.longitude);        //经度切片数目
        int pieces_lat = getPieces(rectangle.leftbottom.latitude, rectangle.rightTop.latitude);//纬度度切片数目

        for (int i = 1; i <= pieces_lat; i++) {
            double lb_lat = rectangle.leftbottom.latitude + DIS * (i - 1);//起始纬度
            double rt_lat = rectangle.leftbottom.latitude + DIS * i;//终止纬度
            if (rt_lat > rectangle.rightTop.latitude) {//不超过最大纬度
                rt_lat = rectangle.rightTop.latitude;
            }

            for (int j = 1; j <= pieces_long; j++) {
                double lb_long = rectangle.leftbottom.longitude + DIS * (j - 1);//起始经度
                double rt_long = rectangle.leftbottom.longitude + DIS * j;//终止经度
                if (rt_long > rectangle.rightTop.longitude) {
                    rt_long = rectangle.rightTop.longitude;
                }

                Rectangle r = new Rectangle(new Point(lb_long, lb_lat), new Point(rt_long, rt_lat));
                list.add(r);
            }
        }

        return list;
    }

    public static int getPieces(double from, double to) {
        int result = 0;

        double distance = Math.abs(to - from);
//        if (distance % DIS > 0) {
//
//            result = (int) (distance / DIS + 1);
//        } else if (distance % DIS == 0) {
//            result = (int) (distance / DIS);
//        }

        result = (int) (distance / DIS + 1);
        return result;

    }
}
