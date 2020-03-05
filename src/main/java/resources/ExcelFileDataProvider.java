package resources;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;
import org.testng.annotations.DataProvider;

//import Utilities.ReadExcelFile;
//import Utilities.ReadPropertiesFile;

public class ExcelFileDataProvider {
	
	/**
	 * This is data provider method which returns two dimensional object 
	 * @param testMethod : i.e public void gci.test.LoginPageTestCases.login(java.lang.String,java.lang.String)
	 * @return
	 * @throws Exception
	 */
	 @DataProvider(name = "getDataFromExcelFile")
	    public static Object[][] fileDataProvider(Method testMethod) throws Exception {
	        ReadExcelFile xlsUtil;
	        Map<String, String> arguments = DataProviderCommonUtils.resolveDataProviderArguments(testMethod);
	        String fileName = arguments.get("FileName");
	        System.out.println("The file used in ReadProppertiesfile is "+fileName);
	        String sSheet = arguments.get("Sheet");
	        System.out.println("The sheet used in ReadProppertiesfile is "+sSheet);
	        File file = new File(ReadPropertiesFile.getProperty("TestDataPath"));
	        xlsUtil = new ReadExcelFile( file.getCanonicalPath() + File.separatorChar + fileName, sSheet);
	        return xlsUtil.retrieveTestData(0);
	    }
}
