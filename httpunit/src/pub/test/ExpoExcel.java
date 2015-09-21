package pub.test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import pub.test.ReqInstan;

public class ExpoExcel {
	private HSSFWorkbook workbook = new HSSFWorkbook();
	private FileOutputStream fileName;
	private HSSFSheet sheet;
	private String uname;
	public ExpoExcel(){
		uname = "";
	}
	
	/*
	 * *
	 * 写入表格的sheet名称和sheet页的头部信息
	 * 
	 */
	public void dataFile(ReqInstan reginstan, int sheetNum, String sheetName){
		uname = sheetName;
		sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetName);
		String[] headers = new String[] { "一级菜单", "二级菜单", "model", "request", "code",  
		        "responseCode", "responseMessage", "result", "时间", "备注"}; 
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {  
            HSSFCell cell = row.createCell(i);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
		
            //设置单元格格式为字符串类型
			cell.setCellType(HSSFCell.ENCODING_UTF_16);

			HSSFFont font = workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontName("宋体");
			font.setFontHeight((short) 250);
			style.setFont(font);

			style.setFillForegroundColor(HSSFColor.RED.index); 
//			style.setFillBackgroundColor(HSSFColor.YELLOW.index);   
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			//应用上面的单元格设置
			cell.setCellStyle(style);									
		}	
	}
	public void setValue(ReqInstan reginstan){
		//获取sheet页的索引
		HSSFSheet sheets = workbook.getSheetAt(reginstan.getNums());
		int rowNum = sheets.getLastRowNum();
		HSSFRow rows = sheets.createRow(rowNum+1);
		HSSFCell cells = rows.createCell(0); 
		cells.setCellValue(reginstan.getMenuOne());
		
		cells = rows.createCell(1); 
		cells.setCellValue(reginstan.getMenuTwo());
		
		cells = rows.createCell(2); 
		cells.setCellValue(reginstan.getModel());
		
		cells = rows.createCell(3); 
		cells.setCellValue(reginstan.getRequest());
		
		cells = rows.createCell(4); 
		cells.setCellValue(reginstan.getCode());
		
		cells = rows.createCell(5); 
		cells.setCellValue(reginstan.getResponseCode());
		
		HSSFCellStyle styles = workbook.createCellStyle();	
		if(reginstan.getResponseCode().toString().equals("200") == false ){ 
			styles.setFillForegroundColor(HSSFColor.RED.index);
//			styles.setFillBackgroundColor(HSSFColor.RED.index);   
			styles.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cells.setCellStyle(styles);
		}
		
		cells = rows.createCell(6); 
		cells.setCellValue(reginstan.getResponseMessage());		
		
		cells = rows.createCell(7); 
		cells.setCellValue(reginstan.getResult());
		
		cells = rows.createCell(8); 
		cells.setCellValue(reginstan.getTimes());
		
		cells = rows.createCell(9); 
		cells.setCellValue(reginstan.getRemark());	
			
		String time = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date da = new Date();
		time = format.format(da);
		
		try {
			String path = System.getProperty("user.dir");
	    	path += "\\out\\"+uname+"_"+time+".xls";
	    	
			fileName = new FileOutputStream(path);
			workbook.write(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	}
	
	public void close(){	
		try {
			fileName.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}