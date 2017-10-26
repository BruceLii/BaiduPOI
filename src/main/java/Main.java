import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyonglin on 2017/10/26.
 */
public class Main {


    public static void main(String[] args) throws SQLException {
        long startTime = System.currentTimeMillis();


        List<Rectangle> tasklist = getTaskList();
        for (int i = 0; i < tasklist.size(); i++) {
            GetDatas.getByBounds(tasklist.get(i));
        }


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


    public static List<Rectangle> getTaskList() {
        List<Rectangle> list = new ArrayList<Rectangle>();
        //整个新疆  有国外地点
//        Point lb = new Point(73.269518, 34.727283);
//        Point rt = new Point(97.259593, 49.18224);
//        Rectangle r1 = new Rectangle(lb, rt);
//        list.add(r1);



//        81.137237,36.287478  90.630242,45.062743  新疆最大片区。
//        Point lb = new Point(81.137237, 36.287478);
//        Point rt = new Point(90.630242, 45.062743);
//        Rectangle r1 = new Rectangle(lb, rt);
//        list.add(r1);

//        Point lb = new Point(81.907186, 39.465609);
//        Point rt = new Point(91.289808,  44.722613);
//        Rectangle r1 = new Rectangle(lb, rt);
//        list.add(r1);



//        //乌鲁木齐
        Point lb = new Point(87.186354, 43.695967);
        Point rt = new Point(87.870504, 43.984768);
        Rectangle r1 = new Rectangle(lb, rt);
        list.add(r1);


//        Point lb1 = new Point(81.137237, 36.287478);
//        Point rt1 = new Point(90.630242, 45.062743);
//        Rectangle r2 = new Rectangle(lb1, rt1);
//        list.add(r2);

        return list;

    }
}
