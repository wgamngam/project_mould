package com.zb.project.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tools {

    public static void responseData(HttpServletResponse response,String data) throws Exception{
			/*
		     * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码),
		     * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会
		     * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。
		     * */
        data = data.replace(":\"[{", ":[{");//去除param参数中多余的“”,保留原参数json格式
        data = data.replace("}]\",", "}],");//\"
        data = data.replace("\\\"", "\"");
        //response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json;charset=utf-8"); 
        //response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String jsonString = data;
        out.print(jsonString);
        out.flush();
        out.close();
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 日期转字符串 格式:yyyy-MM-dd
     * @param date
     * @return
     */
    public static String DATE_TO_STRING(Date date) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dFormat.format(date);
    }

    /**
     * 日期时间转字符串 格式:yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String DATETIME_TO_STRING(Date date) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dFormat.format(date);
    }
    /**
     * 日期时间转字符串 格式(没有空格):yyyy-MM-ddHH:mm:ss
     * @param date
     * @return
     */
    public static String DATETIME_TO_STRING_WITHOUTSPACE(Date date) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        return dFormat.format(date);
    }
    /**
     * 日期时间转字符串 格式(没有符号):yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String DATETIME_TO_STRING_WITHOUTMARK(Date date) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dFormat.format(date);
    }
    /**
     * 字符串转日期 格式:yyyy-MM-dd HH:mm:ss
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static Date STRING_TO_DATE(String sdate) throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dFormat.parse(sdate);
        return date;
    }

    /**
     * 字符串转日期 格式:yyyy-MM-dd HH:mm:ss
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static Date STRING_TO_DATETIME(String sdate) throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dFormat.parse(sdate);
        return date;
    }
    /**
     * 字符串转日期 格式(没有空格):yyyy-MM-ddHH:mm:ss
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static Date STRING_TO_DATETIME_WITHOUTSPACE(String sdate) throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Date date = dFormat.parse(sdate);
        return date;
    }

    /**
     * 字符串转日期 格式格式(没有符号):yyyyMMddHHmmss
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static Date STRING_TO_DATETIME_WITHOUTMARK(String sdate) throws ParseException {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = dFormat.parse(sdate);
        return date;
    }
    /**
     * 获取指定日期的后一天
     * @param today
     * @return
     */
    public static Date GET_TOMORROW(Date today) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        Date tomorro = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // String dateString = formatter.format(tomorro);
        // System.out.println(dateString);
        return tomorro;
    }
    /**
     * 获取指定日期的前一天
     * @param today
     * @return
     */
    public static Date GET_YESTODAY(Date today) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
        Date yestoday = calendar.getTime(); // 这个时间就是日期往前推一天的结果
        return yestoday;
    }

    /**
     * java时间相减：http://j2eeyes.javaeye.com/blog/136170
     * now-before 的时间差，单位秒
     *
     * @throws ParseException
     */
    public static Long DATE_SUBTRACTION(Date before, Date now)
            throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        String nowTime = d.format(now);// 按以上格式 将now时间转换成字符串
        String beforeTime = d.format(before);// 按以上格式 将before时间转换成字符串
        long result = (d.parse(nowTime).getTime() - d.parse(beforeTime)
                .getTime()) / 1000;// 当前时间减去测试时间
        // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
        System.out.println("当前时间减去测试时间=" + result + "秒");
        return result;
    }

    /**
     * 创建文件：先验证文件是否存在，
     * 如果存在，返回true
     * 如果不存在，则创建新文件，范围false
     * false表示是新建的文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public static boolean FILE_ISEXIST_AND_CREATE(String fileName)
            throws IOException {
        boolean flag = true;
        File file = new File(fileName);
        if (file.exists() == false) {
            file.createNewFile();
            flag = false;
        }
        return flag;
    }

    public static String subStringDateFormat(String sdate) {
        // 取出<!-- 2009-03-12 13:25:01 -->格式中的日期字符串
        return sdate.substring(5, 24);
    }

    public static String HTML_DATE_TO_STRING(Date date) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "<!-- " + dFormat.format(date) + " -->";
        return s;
    }


    // 工具方法 把从页面获取的数据格式化，方便在显示的时候按原来的格式显示
    public static String transform(String content) {
        String contentNew = content;
        String enter = "<br>\n";
        contentNew = contentNew.replaceAll("&", "&amp");
        contentNew = contentNew.replaceAll("<", "&lt;");
        contentNew = contentNew.replaceAll(" ", "nbsp;");
        contentNew = contentNew.replaceAll(">", "&gt;");
        contentNew = contentNew.replaceAll("\r\n", enter);
        return contentNew;
    }

    /**
     * 功能：去掉所有的<*>标记,去除html标签
     *
     * @param content
     * @return
     */
    public static String removeTagFromText(String content) {
        Pattern p = null;
        Matcher m = null;
        String value = null;

        // 去掉<>标签
        p = Pattern.compile("(<[^>]*>)");
        m = p.matcher(content);
        String temp = content;
        while (m.find()) {
            value = m.group(0);
            temp = temp.replace(value, "");
        }

        // 去掉换行或回车符号
        p = Pattern.compile("(\r+|\n+)");
        m = p.matcher(temp);
        while (m.find()) {
            value = m.group(0);
            temp = temp.replace(value, " ");
            // System.out.println("....." + value);
        }
        return temp;
    }
    /**
     * 删除内容字符串中的html格式
     *
     * @param input
     * @param length
     * @return
     */
    public static String splitAndFilterString(String input, int length) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }
        return str;
    }
    /**
     * 获取请求真实IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 获取随机长度字符串 包含数字和字母
     * @param length  字符串长度
     * @return
     */
    public static final String getRandomString(int length) {
        Random randGen = null;
        char[] numbersAndLetters = null;
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }


    /**
     * 获取随机长度字符串 纯字母
     * @param length  字符串长度
     * @return
     */
    public static final String getRandomStringWithOutNumber(int length) {
        Random randGen = null;
        char[] numbersAndLetters = null;
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(51)];
        }
        return new String(randBuffer);
    }

    /**
     * 获取随机长度字符串 纯数字
     * @param length  字符串长度
     * @return
     */
    public static final String getRandomStringWithNumber(int length) {
        Random randGen = null;
        char[] numbersAndLetters = null;
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789").toCharArray();
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
        }
        return new String(randBuffer);
    }

    // 判断文件是否存在
    public static void judeFileExists(File file) throws IOException{

        if (file.exists()) {
        } else {
            file.createNewFile();
        }

    }

    // 判断文件夹是否存在
    public static void judeDirExists(File file)  throws IOException{

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }

    /**
     * 随机颜色
     */
    public static String randomColor() {
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length() == 1 ? "0" + red : red;
        //判断绿色代码的位数
        green = green.length() == 1 ? "0" + green : green;
        //判断蓝色代码的位数
        blue = blue.length() == 1 ? "0" + blue : blue;
        //生成十六进制颜色值
        String color = "#" + red + green + blue;

        return color;
    }

    //截取字符串中第一组数字
    public static String getFirstNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否都为数字 是 true 否false
    public static boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    // 判断一个字符串是否含有数字
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if(m.matches()){
            flag = true;
        }
        return flag;
    }
}
