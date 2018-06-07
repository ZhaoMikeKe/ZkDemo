package com.example.lenovo.myapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by zhaoke on 2018/6/6.
 */

public class ScreenUtils {
    private final static String rootPath = "D:\\shipei\\values-{0}x{1}\\";

    private final static float dw = 1080f;
    private final static float dh = 1920f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
    private final static String HTemplate = "<dimen name=\"y{0}\">{1}px</dimen>\n";

    public static void main(String[] args) {
        makeString(480, 854);
        makeString(480, 800);
        makeString(540, 960);
        makeString(720, 1184);
        makeString(720, 1208);
        makeString(720, 1280);
        makeString(720, 1440);
        makeString(752, 1280);
        makeString(1080, 1776);
        makeString(1080, 1788);
        makeString(1080, 1794);
        makeString(1080, 1800);
        makeString(1080, 1808);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1200, 1920);
        makeString(1080, 2160);
        makeString(1080, 2280);
        makeString(1440, 2560);
        makeString(1440, 2960);
    }

    public static void makeString(int w, int h) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>");
        float cellw = w / dw;
        for (int i = 1; i < 720; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
        sb.append(WTemplate.replace("{0}", "720").replace("{1}", w + ""));
        sb.append("</resources>");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb2.append("<resources>");
        float cellh = h / dh;
        for (int i = 1; i < 1280; i++) {
            sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellh * i) + ""));
        }
        sb2.append(HTemplate.replace("{0}", "1280").replace("{1}", h + ""));
        sb2.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layxFile = new File(path + "lay_x.xml");
        File layyFile = new File(path + "lay_y.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
            pw = new PrintWriter(new FileOutputStream(layyFile));
            pw.print(sb2.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}
