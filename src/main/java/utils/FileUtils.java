package utils;

import model.StoreModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by liyonglin on 2017/10/25.
 */
public class FileUtils {
    public static final String outPutPath = "G:" + File.separator;
    public static String fileName = "druginfo.csv";//文件名称

    public static void main(String[] args) {
        //数据
        List<StoreModel> rowList = new ArrayList<StoreModel>(20);

        StoreModel header = new StoreModel();
        header.formatted_address = "城市";
        header.storeName = "药店名";
        header.address = "地址";
        header.longitude = "经度";
        header.latitude = "纬度";
        rowList.add(header);

        for (int i = 0; i < 10; i++) {
            StoreModel storeModel = new StoreModel();
            storeModel.formatted_address = "beijing";
            storeModel.latitude = "11";
            storeModel.longitude = "33";
            storeModel.address = "被广宁路";
            storeModel.storeName = "大药房";
            rowList.add(storeModel);
        }

        writeIntoCSV(rowList);
    }


    /**
     * 创建CSV文件
     */
    public static void writeIntoCSV(List<StoreModel> headList) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + fileName);
            if (!csvFile.exists()) {//不存在则创建
                File parent = csvFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                csvFile.createNewFile();
            }

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile,true), "GB2312"), 1024);
            writeRow(headList, csvWtriter,csvFile);
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写一行数据
     *
     * @param row       数据列表
     * @param csvWriter
     * @param csvFile
     * @throws IOException
     */
    private static void writeRow(List<StoreModel> row, BufferedWriter csvWriter, File csvFile) throws IOException {
        for (int j = 0; j < row.size(); j++) {
            StoreModel model = row.get(j);

            for (int i = 0; i < 5; i++) {
                StringBuffer sb = new StringBuffer();

                int offset=0;
                if (csvFile.length() > 0) {
                    offset = (int) (csvFile.length() - 1);
                }
                String rowStr;
                switch (i) {
                    case 0:
                        rowStr = sb.append("\"").append(model.formatted_address).append("\",").toString();
                        csvWriter.append(rowStr);
                        break;
                    case 1:
                        rowStr = sb.append("\"").append(model.storeName).append("\",").toString();
                       csvWriter.append(rowStr);
                        break;
                    case 2:
                        rowStr = sb.append("\"").append(model.address).append("\",").toString();
                       csvWriter.append(rowStr);
                        break;
                    case 3:
                        rowStr = sb.append("\"").append(model.longitude).append("\",").toString();
                       csvWriter.append(rowStr);
                        break;
                    case 4:
                        rowStr = sb.append("\"").append(model.latitude).append("\",").toString();
                       csvWriter.append(rowStr);
                        break;
                }

            }
            csvWriter.newLine();

        }


    }


}
