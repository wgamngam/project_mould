package com.zb.project.util;

/**
 * Created by Chengxs on 2016/12/12.
 */

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;


public class ExcelReaderUtil {
    private static final Logger log = LoggerFactory.getLogger("ExcelReaderUtil");
    public LinkedList<Map<String, Object>> readExcel(String excelPath) throws InvalidFormatException, FileNotFoundException, IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(excelPath)));
        Sheet sheet = workbook.getSheetAt(0);
        int startRowNum = sheet.getFirstRowNum();
        int endRowNum = sheet.getLastRowNum();
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) continue;
            int startCellNum = row.getFirstCellNum();
            int endCellNum = row.getLastCellNum();
            for (int cellNum = startCellNum; cellNum < endCellNum; cellNum++) {
                Cell cell = row.getCell(cellNum);
                if (cell == null) continue;
                int type = cell.getCellType();
                switch (type) {
                    case Cell.CELL_TYPE_NUMERIC://数值、日期类型
                        double d = cell.getNumericCellValue();
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期类型
                            Date date = HSSFDateUtil.getJavaDate(d);
                        } else {//数值类型
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK://空白单元格
                        break;
                    case Cell.CELL_TYPE_STRING://字符类型
                        break;
                    case Cell.CELL_TYPE_BOOLEAN://布尔类型
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        log.error("非法单元格类型");//非法字符;
                        break;
                    default:
                        log.error("单元格类型未知");//未知类型
                        break;
                }
            }
        }
        return null;
    }

    public String readExcel2(String excelPath) throws InvalidFormatException, FileNotFoundException, IOException {
        Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(excelPath)));
        Sheet sheet = workbook.getSheetAt(0);
        int startRowNum = sheet.getFirstRowNum();
        int endRowNum = sheet.getLastRowNum();
        String str = "";
        for (int rowNum = startRowNum + 1; rowNum <= endRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) continue;
            Cell cell = row.getCell((short) 0);
            if (cell == null) continue;
            int type = cell.getCellType();
            if (type == Cell.CELL_TYPE_STRING) {
                str += cell.getStringCellValue() + ",";
            } else if (type == Cell.CELL_TYPE_NUMERIC) {
                double d = cell.getNumericCellValue();
                str += String.valueOf((int)d) + ",";
            } else {
                log.error("用户名单元格仅支持数字、字符、符号");
            }
        }
        return str;
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

//    public static void main(String[] args) {
//        ExcelReaderUtil ReadExcel2 = new ExcelReaderUtil();
//        try {
//            ReadExcel2.readExcel2("C:\\Users\\Jay\\Downloads\\【私享会投票】2015年电影大片看哪部？结果已出报名表.xlsx");
//            //ReadExcel2.readExcel("C:\\Users\\javaloveiphone\\Desktop\\templateyou.xls");
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
