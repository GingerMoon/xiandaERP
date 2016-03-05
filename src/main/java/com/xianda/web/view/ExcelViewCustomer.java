package com.xianda.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.xianda.web.json.bean.CustomerJsonBean;


public class ExcelViewCustomer extends AbstractExcelView  {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("客户列表");
		setExcelHeader(excelSheet);
		
		List<CustomerJsonBean> customerList = (List<CustomerJsonBean>) model.get("customerList");
		setExcelRows(excelSheet,customerList);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("编号");
		excelHeader.createCell(1).setCellValue("商砼公司");
		excelHeader.createCell(2).setCellValue("状态");
		excelHeader.createCell(3).setCellValue("备注");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<CustomerJsonBean> elements){
		int record = 1;
		for (CustomerJsonBean element : elements) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(element.getId());
			excelRow.createCell(1).setCellValue(element.getName());
			excelRow.createCell(2).setCellValue(element.getState());
			excelRow.createCell(3).setCellValue(element.getDescription());
		}
	}
}	