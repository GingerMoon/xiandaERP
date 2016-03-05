package com.xianda.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.xianda.web.json.bean.BusiInfoJsonBean;

public class ExcelViewBusiInfo extends AbstractExcelView  {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Business Infomation");
		setExcelHeader(excelSheet);
		
		List<BusiInfoJsonBean> carList = (List<BusiInfoJsonBean>) model.get("busiInfo");
		setExcelRows(excelSheet,carList);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("编号");
		excelHeader.createCell(1).setCellValue("搅拌站名称");
		excelHeader.createCell(2).setCellValue("搅拌站联系人");
		excelHeader.createCell(3).setCellValue("搅拌站联系人电话");
		excelHeader.createCell(4).setCellValue("工地名称");
		excelHeader.createCell(5).setCellValue("工地负责人姓名");
		excelHeader.createCell(6).setCellValue("工地负责人电话");
		excelHeader.createCell(7).setCellValue("开工时间");
		excelHeader.createCell(8).setCellValue("完工时间");
		excelHeader.createCell(9).setCellValue("天泵单价");
		excelHeader.createCell(10).setCellValue("地泵单价");
		excelHeader.createCell(11).setCellValue("完成量");
		excelHeader.createCell(12).setCellValue("总方量");
		excelHeader.createCell(13).setCellValue("方量完成百分比");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<BusiInfoJsonBean> busInfoList){
		int record = 1;
		for (BusiInfoJsonBean busInfo : busInfoList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(busInfo.getId());
			excelRow.createCell(1).setCellValue(busInfo.getMixingStationName());
			excelRow.createCell(1).setCellValue(busInfo.getMixingStationContactPersonName());
			excelRow.createCell(1).setCellValue(busInfo.getMixingStationContactPersonPhone());
			excelRow.createCell(1).setCellValue(busInfo.getConstructionSiteName());
			excelRow.createCell(1).setCellValue(busInfo.getConstructionContactPersonName());
			excelRow.createCell(1).setCellValue(busInfo.getConstructionContactPersonPhone());
			excelRow.createCell(1).setCellValue(busInfo.getBeginDate());
			excelRow.createCell(1).setCellValue(busInfo.getEndDate());
			excelRow.createCell(1).setCellValue(busInfo.getUtPricePumpSky());
			excelRow.createCell(1).setCellValue(busInfo.getVolumeCompleted());
			excelRow.createCell(1).setCellValue(busInfo.getVolumeTotal());
			excelRow.createCell(1).setCellValue(busInfo.getVolumePercent());
		}
	}
}	