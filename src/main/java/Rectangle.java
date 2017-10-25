/**
 * Created by liyonglin on 2017/10/25.
 * 矩形区域
 */
public class Rectangle {
    /**
     * 当前区域名，
     */
    public String currentAreaName;
    public Point leftbottom;
    public Point rightTop;

    public Rectangle(Point leftbottom, Point rightTop) {
        this.leftbottom = leftbottom;
        this.rightTop = rightTop;
    }

    public Rectangle(String currentAreaName, Point leftbottom, Point rightTop) {
        this.currentAreaName = currentAreaName;
        this.leftbottom = leftbottom;
        this.rightTop = rightTop;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "'" + currentAreaName + '\'' +
                "," + leftbottom +
                ", " + rightTop +
                '}';
    }
}
