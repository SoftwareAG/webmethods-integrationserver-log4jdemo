

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
// --- <<IS-END-IMPORTS>> ---

public final class Log4jDemo

{
	// ---( internal utility methods )---

	final static Log4jDemo _instance = new Log4jDemo();

	static Log4jDemo _newInstance() { return new Log4jDemo(); }

	static Log4jDemo _cast(Object o) { return (Log4jDemo)o; }

	// ---( server methods )---




	public static final void init (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(init)>> ---
		// @sigtype java 3.5
		File log4jConfigFile = new File(LOG4J_CFG);
		
		if (log4jConfigFile.exists() && log4jConfigFile.canRead()) {
		
			ConfigurationFactory factory =  XmlConfigurationFactory.getInstance();
			ConfigurationSource configurationSource = null;
			try {
				configurationSource = new ConfigurationSource(new FileInputStream(log4jConfigFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Configuration configuration = factory.getConfiguration(logCtx, configurationSource);
			
			// Get context instance
			logCtx = new LoggerContext(PACKAGE_NAME);
			
			// Start context
			logCtx.start(configuration);
		} else {
			throw new ServiceException("Configuration file '" + LOG4J_CFG + "' does not exist or cannot be read");
		}
		// --- <<IS-END>> ---

                
	}



	public static final void log (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(log)>> ---
		// @sigtype java 3.5
		// [i] field:0:required message
		// [i] field:0:required level {"TRACE","DEBUG","INFO","WARN","ERROR","FATAL"}
		// [i] field:0:required logger
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			String	message = IDataUtil.getString( pipelineCursor, "message" );
			String	levelStr = IDataUtil.getString( pipelineCursor, "level" );
			String	loggerStr = IDataUtil.getString( pipelineCursor, "logger" );
			
			Level level = Level.getLevel(levelStr);
			Logger logger = logCtx.getLogger(loggerStr);
			
			if (logger.isEnabled(level)) {
				logger.log(level, message);
			}
			
		} finally {
			pipelineCursor.destroy();
		}
		
		// pipeline
		// --- <<IS-END>> ---

                
	}



	public static final void shutdown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shutdown)>> ---
		// @sigtype java 3.5
		if (logCtx != null) {
			logCtx.stop();
		} else {
			throw new ServiceException("Logger '" + PACKAGE_NAME + "' not initialized");
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	private static final String PACKAGE_NAME = "Log4jDemo";
	private static final String LOG4J_CFG = "./packages/" + PACKAGE_NAME + "/config/log4j2.xml";
	private static LoggerContext logCtx  = null;
		
	// --- <<IS-END-SHARED>> ---
}

