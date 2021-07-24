package salesforce.utils;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
		public static String[][] readData(String fileName, String sheetName) throws IOException
		{
			XSSFWorkbook wb = new XSSFWorkbook("./data/" + fileName + ".xlsx");
			XSSFSheet ws = wb.getSheet(sheetName);
			int rowCnt = ws.getLastRowNum();
			int cellCnt = ws.getRow(0).getLastCellNum();
			
			String [][] data = new String[rowCnt][cellCnt];
			
			try {
				for (int i = 1; i <= rowCnt; i++)
				{
					for (int j = 0; j < cellCnt; j++)
					{
						String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
						data[i-1][j] = cellValue;
					System.out.println("Data is: "+cellValue);
					}
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			wb.close();
			return data;
		}
}
