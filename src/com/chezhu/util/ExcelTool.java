package com.chezhu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.chezhu.dao.BrandService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;

public class ExcelTool {
	
	private BrandService brandService;
	private SupplyService supplyService;
	private StyleService styleService;
	
	private Map<String, Integer> styleMap = new HashMap<String, Integer>();
	
	
	private Logger logger = Logger.getLogger(ExcelTool.class);

	public ExcelTool() {
		ContextUtil.initIocContext();
		
		brandService = (BrandService)ContextUtil.getBean(BrandService.class, "brandService");
		supplyService = (SupplyService)ContextUtil.getBean(SupplyService.class, "supplyService");	
		styleService = (StyleService)ContextUtil.getBean(StyleService.class, "styleService");
	}
	
	public void readTest() {
		try {
			//InputStream is = new FileInputStream(new File("/Users/lijian/filter.xls"));
			InputStream is = new FileInputStream(new File("c:/filter.xls"));
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

	public void readSheet(String supplyName, int sheetIndex, int fromIndex) {
		try {
			//InputStream is = new FileInputStream(new File("c:/filter.xls"));
			InputStream is = new FileInputStream(new File("/Users/lijian/filter.xls"));
			// 根据输入流创建Workbook对象
			Workbook wb = WorkbookFactory.create(is);
			// get到Sheet对象
			Sheet sheet = wb.getSheetAt(sheetIndex);
			// 这个必须用接口
			int line = 0;
			Supply supplyEntity = supplyService.fetch(supplyName);
			if(supplyEntity == null) {
				System.err.println("can't find supply named [" + supplyName + "]");
				return;
			}
			int supplyId = supplyEntity.getId();
			for (Row row : sheet) {
				line++;
				if (line < fromIndex) {
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

				Brand brandEntity = brandService.fetch(brand);
				if(brandEntity == null) {
					System.err.println("can't find brand named [" + brand + "]");
					return;
				}
				int brandId = brandEntity.getId();
				
				String styleFullName = style + outter;
				int styleId = 0;
				if(styleMap.containsKey(styleFullName)) {
					styleId = styleMap.get(styleFullName);
				} else {
					Style styleEntity = styleService.fetchFullName(styleFullName);
					if(styleEntity == null) {
						// 此型号不存在
						List<Style> allStyle = styleService.getAllStyles();
						int counter = allStyle.size();
						styleId = counter + 1 + styleMap.size();
						logger.error("INSERT INTO style(style_id, brand_id, style_name, style_outter, style_motor, style_fullname) VALUES(" + styleId + ", " + brandId + ", '" + style + "', '" + outter + "', '" + motor + "', '" + styleFullName + "');");
						styleMap.put(styleFullName, styleId);
					} else {
						// 此型号已存在
						styleId = styleEntity.getId();
					}
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
		int fromIndex = 338;
		readSheet("索菲玛", 0, fromIndex);
		readSheet("博世", 1, fromIndex);
		readSheet("马勒", 2, fromIndex);
		readSheet("曼牌MANN", 3, fromIndex);
		readSheet("原厂号", 4, fromIndex);
	}

	public static void main(String[] args) {
		ExcelTool exTool = new ExcelTool();
		//exTool.readTest();
		exTool.readExcel();
	}

}
