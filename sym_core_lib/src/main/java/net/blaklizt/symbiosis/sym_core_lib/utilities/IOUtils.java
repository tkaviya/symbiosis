package net.blaklizt.symbiosis.sym_core_lib.utilities;

import java.io.*;

/**
 * Created by tsungai.kaviya on 2015-09-14.
 */
public class IOUtils
{
	private static final Long DEFAULT_MAX_BUFFER_SIZE = 1024L;

	private static final boolean DEFAULT_ENABLE_BUFFERING = true;

	public static File writeToFile(InputStream inputStream, String absoluteFilePath, boolean useBufferedWrite, Long bufferSize) throws Exception
	{
		OutputStream outputStream = null;
		try
		{
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
		String fileSystemSeparatorChar = File.separator;
		String tempFileDirectory = System.getProperty("java.io.tmpdir");
		return tempFileDirectory.endsWith(fileSystemSeparatorChar) ? tempFileDirectory : tempFileDirectory + fileSystemSeparatorChar;
	}
}
