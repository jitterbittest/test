package resources;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataProviderCommonUtils {
	/**
	 * This method returns data provider parameters in key-value pairs(Map
	 * object)
	 * 
	 * @param testMethod
	 * @return Map i.e {FileName=login_page_data.xls, Sheet=Sheet1}
	 * @throws Exception
	 */
	protected static Map<String, String> resolveDataProviderArguments(Method testMethod) throws Exception {
		if (testMethod == null)

			throw new IllegalArgumentException("Test Method context cannot be null.");
		// below line returns annotation with its arguments i.e
		// @gci.util.dataproviders.DataProviderParameter(value=[FileName=login_page_data.xls;Sheet=Sheet1])
		DataProviderParameter args = testMethod.getAnnotation(DataProviderParameter.class);
		if (args == null)
			throw new IllegalArgumentException("Test Method context has no DataProviderArguments annotation.");
		if (args.value() == null || args.value().length == 0)
			throw new IllegalArgumentException("Test Method context has a malformed DataProviderArguments annotation.");
		Map<String, String> arguments = new HashMap<String, String>();
		for (int i = 0; i < args.value().length; i++) {
			String[] parts = args.value()[i].split(";");
			for (int j = 0; j < parts.length; j++) {
				String[] mparts = parts[j].split("=");
				arguments.put(mparts[0], mparts[1]);
			}
		}
		return arguments;
	}
}