package com.zb.project.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tangshiwen on 2016/12/6.
 *
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel2007<T> {
    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
                            OutputStream out) {
        exportExcel("Sheet1", headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
                            OutputStream out, String pattern) {
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset   需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings("unchecked")
    public void exportExcel(String title, String[] headers,
                            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个标题样式
        XSSFCellStyle style_title = workbook.createCellStyle();
        // 设置这些样式
        style_title.setFillForegroundColor(HSSFColor.WHITE.index);
        style_title.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_title.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont font_title = workbook.createFont();
        font_title.setColor(HSSFColor.BLACK.index);
        font_title.setFontHeightInPoints((short) 12);
        font_title.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style_title.setFont(font_title);
        // 生成并设置一个正文样式
        XSSFCellStyle style_content = workbook.createCellStyle();
        style_content.setFillForegroundColor(HSSFColor.WHITE.index);
        style_content.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style_content.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_content.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_content.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style_content.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_content.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style_content.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        XSSFFont font_content = workbook.createFont();
        font_content.setColor(HSSFColor.BLACK.index);
        font_content.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style_content.setFont(font_content);
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(title);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth((short) 15);
            // 产生表格标题行
            XSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellStyle(style_title);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < fields.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style_content);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName,
                                new Class[]
                                        {});
                        Object value = getMethod.invoke(t, new Object[]
                                {});
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            textValue = "男";
                            if (!bValue) {
                                textValue = "女";
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            if (value != null) {
                                textValue = value.toString();
                            }

                        }
                        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                cell.setCellValue(textValue);
                            }
                        }
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }
            }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String path, String[] headers, List<T> dataList) {
        try {
            OutputStream out = new FileOutputStream(path);
            exportExcel(headers, dataList, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
