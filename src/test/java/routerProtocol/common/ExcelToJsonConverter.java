package routerProtocol.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelToJsonConverter {
	
	public static void main(String[] args) {
		try {
			List<ChainData> chainDataList = readExcel("src/test/java/routerProtocol/testData/input.xlsx");
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(chainDataList);
			
			// Specify the file path to save the JSON data
			String filePath = "src/test/java/routerProtocol/testData/input.json";
			
			// Write the JSON data to the file
			FileWriter writer = new FileWriter(filePath);
			writer.write(json);
			writer.close();
			
			System.out.println("JSON data saved to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<ChainData> readExcel(String filename) throws IOException {
		List<ChainData> chainDataList = new ArrayList<>();
		
		FileInputStream file = new FileInputStream(new File(filename));
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0); // assuming data is in the first sheet
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) { // start from the second row (index 1)
			Row row = sheet.getRow(i);
			if (row != null) {
				ChainData chainData = new ChainData();
				chainData.chain = row.getCell(0).getStringCellValue();
				chainData.chainId = (int) row.getCell(1).getNumericCellValue();
				chainData.USDTAddress = row.getCell(2).getStringCellValue();
				chainData.USDCAddress = row.getCell(4).getStringCellValue();
				chainData.WETHAddress = row.getCell(6).getStringCellValue();
				
				List<ConversionData> conversionDataList = new ArrayList<>();
				for (int j = 1; j <= sheet.getLastRowNum() ; j++) {
					if(i==j) continue;
					Row row2 = sheet.getRow(j);
					ConversionData conversionData = new ConversionData();
					conversionData.chain = row2.getCell(0).getStringCellValue();
					conversionData.chainId = (int) row2.getCell(1).getNumericCellValue();
					conversionData.USDTAddress = row2.getCell(2).getStringCellValue();
					conversionData.USDCAddress = row2.getCell(4).getStringCellValue();
					conversionData.WETHAddress = row2.getCell(6).getStringCellValue();
					conversionDataList.add(conversionData);
				}
				chainData.conversion = conversionDataList;
				chainDataList.add(chainData);
			}
		}
		
		workbook.close();
		file.close();
		
		return chainDataList;
	}
	
	static class ChainData {
		public String chain;
		public int chainId;
		public String USDTAddress;
		public String USDCAddress;
		public String WETHAddress;
		public List<ConversionData> conversion = new ArrayList<>();
	}
	
	static class ConversionData {
		public String chain;
		public int chainId;
		public String USDTAddress;
		public String USDCAddress;
		public String WETHAddress;
	}
}
