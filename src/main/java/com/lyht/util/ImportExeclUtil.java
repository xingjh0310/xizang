package com.lyht.util;

import java.io.IOException;  
import java.io.InputStream;  
import java.lang.reflect.Field;  
import java.math.BigDecimal;  
import java.text.DecimalFormat;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  
import org.apache.commons.beanutils.PropertyUtils;  
import org.apache.commons.lang.StringUtils;  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

/** 
 *  
 * excel读取工具类 
 *  
 * @see [相关类/方法] 
 * @since [产品/模块版本] 
 */ 
public class ImportExeclUtil {
	private static int totalRows = 0;// 总行数  
    
    private static int totalCells = 0;// 总列数  
      
    private static String errorInfo;// 错误信息  
    
    /** 无参构造方法 */  
    public ImportExeclUtil() {
    	
    }
    
    public static int getTotalRows()  {
    	return totalRows;  
    }
      
    public static int getTotalCells() {
    	return totalCells;  
    }
      
    public static String getErrorInfo() {
    	return errorInfo;  
    } 
    
    /** 
     *  
     * 根据流读取Excel文件 
     *  
     *  
     * @param inputStream 
     * @param isExcel2003 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003)  
    		throws IOException  {
    	List<List<String>> dataLst = null;  
        
        /** 根据版本选择创建Workbook的方式 */  
        Workbook wb = null;  
        
        if (isExcel2003){
        	wb = new HSSFWorkbook(inputStream);  
        }else{
        	wb = new XSSFWorkbook(inputStream);  
        }
        
        dataLst = readDate(wb);  
    	
        return dataLst;  
    }
    
    
    /** 
     *  
     * 读取数据 
     *  
     * @param wb 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    private List<List<String>> readDate(Workbook wb)  {
    	List<List<String>> dataLst = new ArrayList<List<String>>();  
        
        /** 得到第一个shell */  
        Sheet sheet = wb.getSheetAt(0);  
          
        /** 得到Excel的行数 */  
        totalRows = sheet.getPhysicalNumberOfRows();  
        
        /** 得到Excel的列数 */  
        if (totalRows >= 1 && sheet.getRow(0) != null) {
        	totalCells = sheet.getRow(0).getPhysicalNumberOfCells(); 
        }
        
        /** 循环Excel的行 */  
        for (int r = 0; r < totalRows; r++) {
        	Row row = sheet.getRow(r);  
        	if (row == null) {
        		continue;
        	}
        	List<String> rowLst = new ArrayList<String>();  
        	/** 循环Excel的列 */  
            for (int c = 0; c < getTotalCells(); c++) {
            	 Cell cell = row.getCell(c);  
                 String cellValue = "";  
                   
                 if (null != cell){
                	// 以下是判断数据的类型  
                     switch (cell.getCellType())  
                     {  
                         case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
                             cellValue = cell.getNumericCellValue() + "";  
                             break;  
                           
                         case HSSFCell.CELL_TYPE_STRING: // 字符串  
                             cellValue = cell.getStringCellValue();  
                             break;  
                           
                         case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                             cellValue = cell.getBooleanCellValue() + "";  
                             break;  
                           
                         case HSSFCell.CELL_TYPE_FORMULA: // 公式  
                             cellValue = cell.getCellFormula() + "";  
                             break;  
                           
                         case HSSFCell.CELL_TYPE_BLANK: // 空值  
                             cellValue = "";  
                             break;  
                           
                         case HSSFCell.CELL_TYPE_ERROR: // 故障  
                             cellValue = "非法字符";  
                             break;  
                           
                         default:  
                             cellValue = "未知类型";  
                             break;  
                     }  
                 }
                 rowLst.add(cellValue);  
            }
            /** 保存第r行的第c列 */  
            dataLst.add(rowLst);  
        }
    	
        return dataLst; 
    }
    
    
    /** 
     *  
     * 按指定坐标读取实体数据 
     * <按顺序放入带有注解的实体成员变量中> 
     *  
     * @param wb 工作簿 
     * @param t 实体 
     * @param in 输入流 
     * @param integers 指定需要解析的坐标 
     * @return T 相应实体 
     * @throws IOException 
     * @throws Exception 
     * @see [类、类#方法、类#成员] 
     */  
    @SuppressWarnings("unused")
	public static <T> T readDateT(Workbook wb, T t, InputStream in, Integer[]... integers)  
            throws IOException, Exception  {
    	
    	// 获取该工作表中的第一个工作表  
        Sheet sheet = wb.getSheetAt(0);  
          
        // 成员变量的值  
        Object entityMemberValue = "";  
          
        // 所有成员变量  
        Field[] fields = t.getClass().getDeclaredFields();  
        // 列开始下标  
        int startCell = 0;  
          
        /** 循环出需要的成员 */  
        for (int f = 0; f < fields.length; f++){
        	fields[f].setAccessible(true);  
            String fieldName = fields[f].getName();  
            boolean fieldHasAnno = fields[f].isAnnotationPresent(IsNeeded.class);  
            // 有注解  
            if (fieldHasAnno){
            	IsNeeded annotation = fields[f].getAnnotation(IsNeeded.class);  
                boolean isNeeded = annotation.isNeeded();  
                
                // Excel需要赋值的列  
                if (isNeeded) {
                	// 获取行和列  
                    int x = integers[startCell][0] - 1;  
                    int y = integers[startCell][1] - 1;  
                      
                    Row row = sheet.getRow(x);  
                    Cell cell = row.getCell(y);  
                    
                    if (row == null) {
                    	continue;
                    }
                    
                    // Excel中解析的值  
                    String cellValue = getCellValue(cell);  
                    // 需要赋给成员变量的值  
                    entityMemberValue = getEntityMemberValue(entityMemberValue, fields, f, cellValue);  
                    // 赋值  
                    PropertyUtils.setProperty(t, fieldName, entityMemberValue);  
                    // 列的下标加1  
                    startCell++;
                }
            }  
        }
    	
    	return t; 
    }
    
    /** 
     *  
     * 读取列表数据  
     * <按顺序放入带有注解的实体成员变量中> 
     *  
     * @param wb 工作簿 
     * @param t 实体 
     * @param beginLine 开始行数 
     * @param totalcut 结束行数减去相应行数 
     * @return List<T> 实体列表 
     * @throws Exception 
     * @see [类、类#方法、类#成员] 
     */  
    @SuppressWarnings("unchecked")
	public static <T> List<T> readDateListT(Workbook wb, T t, int beginLine, int totalcut)  
            throws Exception  {
    	
    	List<T> listt = new ArrayList<T>();  
        
        /** 得到第一个shell */  
        Sheet sheet = wb.getSheetAt(0);  
          
        /** 得到Excel的行数 */  
        totalRows = sheet.getPhysicalNumberOfRows();  
        
        /** 得到Excel的列数 */  
        if (totalRows >= 1 && sheet.getRow(0) != null){
        	totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }
        
        /** 循环Excel的行 */  
        for (int r = beginLine - 1; r < totalRows - totalcut; r++) {
        	Object newInstance = t.getClass().newInstance();  
            Row row = sheet.getRow(r);  
            if (row == null) {
            	continue;
            }
            
            // 成员变量的值  
            Object entityMemberValue = "";  
              
            // 所有成员变量  
            Field[] fields = t.getClass().getDeclaredFields();  
            // 列开始下标  
            int startCell = 0;  
              
            for (int f = 0; f < fields.length; f++) {
            	fields[f].setAccessible(true);  
                String fieldName = fields[f].getName();  
                boolean fieldHasAnno = fields[f].isAnnotationPresent(IsNeeded.class);  
                // 有注解  
                if (fieldHasAnno) {
                	IsNeeded annotation = fields[f].getAnnotation(IsNeeded.class);  
                    boolean isNeeded = annotation.isNeeded();  
                    // Excel需要赋值的列  
                    if (isNeeded) {
                    	Cell cell = row.getCell(startCell);  
                        String cellValue = getCellValue(cell);  
                        entityMemberValue = getEntityMemberValue(entityMemberValue, fields, f, cellValue);  
                        // 赋值  
                        PropertyUtils.setProperty(newInstance, fieldName, entityMemberValue);  
                        // 列的下标加1  
                        startCell++; 
                    }
                }
            }
            listt.add((T)newInstance);  
        }
        
    	return listt;
    }
    
    /** 
     *  
     * 根据Excel表格中的数据判断类型得到值 
     *  
     * @param cell 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    private static String getCellValue(Cell cell) {
    	String cellValue = "";  
        
        if (null != cell) {
        	// 以下是判断数据的类型  
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){  
                	Date theDate = cell.getDateCellValue();  
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");  
                    cellValue = dff.format(theDate);  
                }else {  
                    DecimalFormat df = new DecimalFormat("0");  
                    cellValue = df.format(cell.getNumericCellValue());  
                }  
                break;  
            case HSSFCell.CELL_TYPE_STRING: // 字符串  
                cellValue = cell.getStringCellValue();  
                break;  
              
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                cellValue = cell.getBooleanCellValue() + "";  
                break;  
              
            case HSSFCell.CELL_TYPE_FORMULA: // 公式  
                cellValue = cell.getCellFormula() + "";  
                break;  
              
            case HSSFCell.CELL_TYPE_BLANK: // 空值  
                cellValue = "";  
                break;  
              
            case HSSFCell.CELL_TYPE_ERROR: // 故障  
                cellValue = "非法字符";  
                break;  
              
            default:  
                cellValue = "未知类型";  
                break;  
            }
        }
    	return cellValue;
    }
    
    
    /** 
     *  
     * 根据实体成员变量的类型得到成员变量的值 
     *  
     * @param realValue 
     * @param fields 
     * @param f 
     * @param cellValue 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    private static Object getEntityMemberValue(Object realValue, Field[] fields, int f, 
    		String cellValue) {
    	String type = fields[f].getType().getName();  
        switch (type){
	        case "char":  
	        case "java.lang.Character":  
	        case "java.lang.String":  
	            realValue = cellValue;  
	            break;  
	        case "java.util.Date":  
	            realValue = StringUtils.isBlank(cellValue) ? null : DateUtil.strToDate(cellValue, "yyyy-MM-dd");  
	            break;  
	        case "java.lang.Integer":  
	            realValue = StringUtils.isBlank(cellValue) ? null : Integer.valueOf(cellValue);  
	            break;  
	        case "int":  
	        case "float":  
	        case "double":  
	        case "java.lang.Double":  
	        case "java.lang.Float":  
	        case "java.lang.Long":  
	        case "java.lang.Short":  
	        case "java.math.BigDecimal":  
	            realValue = StringUtils.isBlank(cellValue) ? null : new BigDecimal(cellValue);  
	            break;  
	        default:  
	            break;  
        }
    	return realValue;
    }
    
    
    /** 
     *  
     * 根据路径或文件名选择Excel版本 
     *  
     *  
     * @param filePathOrName 
     * @param in 
     * @return 
     * @throws IOException 
     * @see [类、类#方法、类#成员] 
     */  
    public static Workbook chooseWorkbook(String filePathOrName, InputStream in)  
            throws IOException {
    	
    	/** 根据版本选择创建Workbook的方式 */  
        Workbook wb = null;  
        boolean isExcel2003 = ExcelVersionUtil.isExcel2003(filePathOrName);  
          
        if (isExcel2003) {
        	wb = new HSSFWorkbook(in);  
        }else{
        	wb = new XSSFWorkbook(in);  
        }
    	return wb;
    }
    
}
