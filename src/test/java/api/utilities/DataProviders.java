package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	
	@DataProvider(name="Data")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//RestAssuredData.xlsx"; //taking xl file from testData folder
		ExcelUtility xl=new ExcelUtility(path);
		
		int totalrows=xl.getRowCount("sheet1");
		int totalcols=xl.getCellCount("Sheet1", 1);
		
		String apidata[][]=new String[totalrows][totalcols];//creating 2d array
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<=totalcols-1;j++)
			{
				apidata[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	
	
	@DataProvider(name="userNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//RestAssuredData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		int rownum=xl.getRowCount("sheet1");
		
		String apidata[]= new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("sheet1", i, 1);
		}
		return apidata;
	}
	
	
	
}
