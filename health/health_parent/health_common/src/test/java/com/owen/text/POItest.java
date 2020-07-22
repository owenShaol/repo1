package com.owen.text;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.Date;

public class POItest {
    @Test
    public void polTest() throws IOException {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("D:\\demotest.xlsx")));
        XSSFSheet sheet = excel.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.println(cell.getRow());
            }
        }
        excel.close();


    }

    @Test
    public void test2() throws IOException {
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File("D:\\demotest.xlsx")));
        XSSFSheet sheet = excel.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
//            行是小于等于，列是小于
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                System.out.println(row.getCell(j));
            }
        }
    }

    //    excel写入数据
    @Test
    public void test3() throws IOException {
        XSSFWorkbook excel = new XSSFWorkbook();
        XSSFSheet sheet = excel.createSheet("owen1");
        XSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("name");
        title.createCell(1).setCellValue("gender");
        title.createCell(2).setCellValue("age");
        XSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("bob");
        dataRow.createCell(1).setCellValue("female");
        dataRow.createCell(2).setCellValue("20");
        FileOutputStream fos = new FileOutputStream(new File("D:\\hello.xlsx"));
        excel.write(fos);
        fos.flush();
        fos.close();


    }
    @Test
    public void test4(){
        System.out.println(new Date("2019/3/4"));
    }
}
