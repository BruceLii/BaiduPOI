import NetConst.URLUtils;
import model.Point;
import model.Rectangle;
import utils.Cosnt;

import java.sql.SQLException;
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
            System.out.println(" 并发总数 ： " + URLUtils.concurrentNum + "   每分钟并发量：" + (URLUtils.concurrentNum / costTime) * 60.0);
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
        List<Rectangle> xinjiang = Cosnt.getCuttedAreaList(new Rectangle(new Point(73.269518, 34.727283), new Point(97.259593, 49.18224)));

        return xinjiang;

    }


}
