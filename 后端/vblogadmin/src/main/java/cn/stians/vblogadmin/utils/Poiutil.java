package cn.stians.vblogadmin.utils;

import cn.stians.vblogadmin.model.Users;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Poiutil {

    public static ResponseEntity<byte[]> users2Excel(List<Users> list) {
//      1.  创建excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
//        创建文档摘要
        workbook.createInformationProperties();
//        3获取并配置文档信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
//        设置文档类别
        information.setCategory("用户信息");
//        设置文档管理员
        information.setManager("suitianshuang");
//        设置公司信息
        information.setCompany("www/stians.cn");
//        获取文档的摘要信息
        SummaryInformation suminfo = workbook.getSummaryInformation();
//        文档标题
        suminfo.setTitle("用户表");
//        作者
        suminfo.setAuthor("suitianshuang");
//        备注信息
        suminfo.setComments("如有疑问，请联系tianshuangsui@Gmail.com.");

//        创建样式
//        创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
//        设置表单 默认为sheet
        HSSFSheet sheet = workbook.createSheet("用户表");
//设置列的宽度
        sheet.setColumnWidth(0,5*256);
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,20*256);
        sheet.setColumnWidth(3,25*256);
        sheet.setColumnWidth(4,10*256);
        sheet.setColumnWidth(5,10*256);
        sheet.setColumnWidth(6,20*256);
        sheet.setColumnWidth(7,10*256);
        sheet.setColumnWidth(8,20*256);
        sheet.setColumnWidth(9,20*256);
//          创建标题行
        HSSFRow row0 = sheet.createRow(0);
//        创建列
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("编号");
        cell0.setCellStyle(headerStyle);
        HSSFCell cell1 = row0.createCell(1);
        cell1.setCellValue("用户名");
        cell1.setCellStyle(headerStyle);
        HSSFCell cell2 = row0.createCell(2);
        cell2.setCellValue("邮箱");
        cell2.setCellStyle(headerStyle);
        HSSFCell cell3 = row0.createCell(3);
        cell3.setCellValue("创建时间戳");
        cell3.setCellStyle(headerStyle);
        HSSFCell cell4 = row0.createCell(4);
        cell4.setCellValue("性别");
        cell4.setCellStyle(headerStyle);
        HSSFCell cell5 = row0.createCell(5);
        cell5.setCellValue("年龄");
        cell5.setCellStyle(headerStyle);
        HSSFCell cell6 = row0.createCell(6);
        cell6.setCellValue("情感");
        cell6.setCellStyle(headerStyle);
        HSSFCell cell7 = row0.createCell(7);
        cell7.setCellValue("工作");
        cell7.setCellStyle(headerStyle);
        HSSFCell cell8 = row0.createCell(8);
        cell8.setCellValue("生日");
        cell8.setCellStyle(headerStyle);
        HSSFCell cell9 = row0.createCell(9);
        cell9.setCellValue("家乡");
        cell9.setCellStyle(headerStyle);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        便利list集合
        for (int i = 0; i < list.size(); i++) {
            Users user = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getCreateTime());
            row.createCell(4).setCellValue(user.getUserinfo().getSex());
            row.createCell(5).setCellValue(user.getUserinfo().getAge());
            row.createCell(6).setCellValue(user.getUserinfo().getEmotion());
            row.createCell(7).setCellValue(user.getUserinfo().getJob());
            HSSFCell c8 = row.createCell(8);
            c8.setCellStyle(dateCellStyle);
            c8.setCellValue(user.getUserinfo().getBirthday());
            row.createCell(9).setCellValue(user.getUserinfo().getHometown());
        }
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            headers.setContentDispositionFormData("attachment", new String("用户表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }
}
