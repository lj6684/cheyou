package com.chezhu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTool {
	
	private Map<String, Integer> brandMap = new HashMap<String, Integer>();
	private Map<String, Integer> supplyMap = new HashMap<String, Integer>();
	private Map<String, Integer> styleMap = new HashMap<String, Integer>();
	
	private int brandIndex = 0;
	private int styleIndex = 0;
	
	private Logger logger = Logger.getLogger(ExcelTool.class);

	public ExcelTool() {
		supplyMap.put("博世", 1);
		supplyMap.put("原厂", 2);
		supplyMap.put("索菲玛", 3);
		supplyMap.put("马勒", 4);
		supplyMap.put("曼牌", 5);
		supplyMap.put("箭牌", 6);
		
		try {
			logger.error("truncate table brand;");
			logger.error("truncate table style;");
			logger.error("truncate table filter;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void readTest() {
		try {
			InputStream is = new FileInputStream(new File("/Users/lijian/filter.xls"));
			// 根据输入流创建Workbook对象
			Workbook wb = WorkbookFactory.create(is);
			// get到Sheet对象
			Sheet sheet = wb.getSheetAt(0);
			// 这个必须用接口
			for (Row row : sheet) {
				for (Cell cell : row) {
					// cell.getCellType是获得cell里面保存的值的type
					// 如Cell.CELL_TYPE_STRING
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						// 得到Boolean对象的方法
						System.out.print(cell.getBooleanCellValue() + " ");
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// 先看是否是日期格式
						if (DateUtil.isCellDateFormatted(cell)) {
							// 读取日期格式
							System.out.print(cell.getDateCellValue() + " ");
						} else {
							// 读取数字
							System.out.print(cell.getNumericCellValue() + " ");
						}
						//break;
					case Cell.CELL_TYPE_FORMULA:
						// 读取公式
						System.out.print(cell.getCellFormula() + " ");
						break;
					case Cell.CELL_TYPE_STRING:
						// 读取String
						System.out.print(cell.getRichStringCellValue().toString() + " ");
						break;
					}
				}
				System.out.println("");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void readSheet(String supplyName, int sheetIndex) {
		try {
			//InputStream is = new FileInputStream(new File("c:/大众.xls"));
			InputStream is = new FileInputStream(new File("/Users/lijian/filter.xls"));
			// 根据输入流创建Workbook对象
			Workbook wb = WorkbookFactory.create(is);
			// get到Sheet对象
			Sheet sheet = wb.getSheetAt(sheetIndex);
			// 这个必须用接口
			int line = 0;
			int supplyId = supplyMap.get(supplyName);
			for (Row row : sheet) {
				line++;
				if (line == 1) {
					continue;
				}

				Cell brandCell = row.getCell(0);
				Cell styleCell = row.getCell(1);
				Cell outterCell = row.getCell(2);
				Cell motorCell = row.getCell(3);
				Cell machineOilCell = row.getCell(4);
				Cell fuelOilCell = row.getCell(5);
				Cell airCell = row.getCell(6);
				Cell airConditionStdCell = row.getCell(7);
				Cell airConditionCarbonCell = row.getCell(8);
				
				String brand = getCellValue(brandCell);
				String style = getCellValue(styleCell);
				String outter = getCellValue(outterCell);
				String motor = getCellValue(motorCell);
				String machineOil = getCellValue(machineOilCell);
				String fuelOil = getCellValue(fuelOilCell);
				String air = getCellValue(airCell);
				String airConditionStd = getCellValue(airConditionStdCell);
				String airConditionCarbon = getCellValue(airConditionCarbonCell);

				int brandId = 0;
				if(brandMap.containsKey(brand)) {
					brandId = brandMap.get(brand);
				} else {
					brandIndex++;
					brandId = brandIndex;
					logger.error("INSERT INTO brand(brand_id, brand_name) VALUES(" + brandId + ", '" + brand + "');");
					brandMap.put(brand, brandIndex);
				}
				
				int styleId = 0;
				String styleFullName = brand + " " + style + " " + outter + " " + motor;
				if(styleMap.containsKey(styleFullName)) {
					styleId = styleMap.get(styleFullName);
				} else {
					styleIndex++;
					styleId = styleIndex;
					logger.error("INSERT INTO style(style_id, brand_id, style_name, outter, motor, style_fullname) VALUES(" + styleId + ", " + brandId + ", '" + style + "', '" + outter + "', '" + motor + "', '" + styleFullName + "');");
					styleMap.put(styleFullName, styleIndex);
				}
				
				logger.error("INSERT INTO filter(supply_id, brand_id, style_id, air, machine_oil, fuel_oil, air_condition_std, air_condition_carbon) VALUES(" + supplyId +", " + brandId + ", " + styleId + ", '" + air + "', '" + machineOil + "', '" + fuelOil + "', '" + airConditionStd + "', '" + airConditionCarbon + "');");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private String getCellValue(Cell cell) {
		if(cell == null) {
			return "";
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String res = cell.getRichStringCellValue().toString().trim();

		return res;
	}
	
	public void readExcel() {
		readSheet("索菲玛", 0);
		readSheet("博世", 1);
		readSheet("马勒", 2);
		readSheet("曼牌", 3);
		readSheet("原厂", 4);
		
	}

	public static void main(String[] args) {
		ExcelTool exTool = new ExcelTool();
		//exTool.readTest();
		exTool.readExcel();
	}

}
