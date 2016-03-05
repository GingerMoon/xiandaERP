package com.xianda.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xianda.annotation.Layout;


@Controller
@RequestMapping("/excelFileUpload")
class ExcelFileUploadController {
	
	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	String index() {
		return "excelFileUpload";
	}
	
	@RequestMapping(method=RequestMethod.POST)
    public void uploadExcelFiles(@RequestParam("excelFile") MultipartFile [] excelFiles) {
		importExcelFiles(excelFiles);
    }
	
	public void importExcelFiles(MultipartFile [] excelFiles) {
        Workbook workbook;
        Sheet sheet;
    	
    	MultipartFile excelFile = excelFiles[0];
        try {
    		ByteArrayInputStream bis = new ByteArrayInputStream(excelFile.getBytes());
            if (excelFile.getOriginalFilename().endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);
            } else if (excelFile.getOriginalFilename().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }
            sheet = workbook.getSheet("方量明细表");
            Iterator<Row> rows = sheet.iterator();
            for(int i = 0; i < 5; i++)
            	rows.next();
            while(rows.hasNext()) {
            	Row row = rows.next();
            	Iterator<Cell> cells = row.cellIterator ();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}