package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4jTest {
	private static Logger Logger = LogManager.getLogger(log4jTest.class);
	
	public static void main(String [] args) {
		Logger.trace("This is Trace Message");
		Logger.info("This is Information Message");
		Logger.warn("This is Warning Message");
		Logger.error("This is Error Message");
		Logger.fatal("This is Fatal Message");
	}
}