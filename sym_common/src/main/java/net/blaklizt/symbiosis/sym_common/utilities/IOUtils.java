package net.blaklizt.symbiosis.sym_common.utilities;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;

import java.io.*;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.temporal.JulianFields;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.time.temporal.JulianFields.JULIAN_DAY;

/**
 * Created by tsungai.kaviya on 2015-09-14.
 */
public class IOUtils
{
	private static final Logger logger = Configuration.getNewLogger(Format.class.getSimpleName());

	private static final Long DEFAULT_MAX_BUFFER_SIZE = 1024L;

	private static final boolean DEFAULT_ENABLE_BUFFERING = true;

	public static File writeToFile(InputStream inputStream, String absoluteFilePath, boolean useBufferedWrite, Long bufferSize) throws Exception
	{
		OutputStream outputStream = null;
		try
		{
			logger.info("=============================================================");
			logger.info(format("Writing inputStream to file %s", absoluteFilePath));
			logger.info(format("Buffer is:\t%s", useBufferedWrite ? format("enabled.\nBuffer size:\t%d", bufferSize) : "disabled."));
			LocalDateTime startTime = LocalDateTime.now();

			File outputFile = new File(absoluteFilePath);

			if (useBufferedWrite)
				outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
			else
				outputStream = new FileOutputStream(outputFile);

			int read;
			byte[] bytes = new byte[bufferSize.intValue()];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			logger.info(String.format("Wrote %d bytes in %s", outputFile.length(), LocalDateTime.now().getLong(JulianFields.JULIAN_DAY) - startTime.getLong(JULIAN_DAY)));
			logger.info("=============================================================");
			return outputFile;
		}
		finally
		{
			if (inputStream != null) inputStream.close();
			if (outputStream != null)
			{
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	public static File writeToFile(InputStream inputStream, String absoluteFilePath) throws Exception {
		return writeToFile(inputStream, absoluteFilePath, DEFAULT_ENABLE_BUFFERING, DEFAULT_MAX_BUFFER_SIZE);
	}

	public static File writeToFile(InputStream inputStream, String absoluteFilePath, boolean useBufferedWrite) throws Exception {
		return writeToFile(inputStream, absoluteFilePath, useBufferedWrite, DEFAULT_MAX_BUFFER_SIZE);
	}

	public static String getOSDefaultTempDir() {
		String fileSystemSeparatorChar = FileSystems.getDefault().getSeparator();
		String tempFileDirectory = System.getProperty("java.io.tmpdir");
		return tempFileDirectory.endsWith(fileSystemSeparatorChar) ? tempFileDirectory : tempFileDirectory + fileSystemSeparatorChar;
	}
}
