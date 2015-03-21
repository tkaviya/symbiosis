package net.blaklizt.symbiosis.sym_sync;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/03/21
 * Time: 8:17 AM
 */
public class SymSyncFile implements Serializable
{
	private String fileName;
	private String filePath;
	private String fileChecksum;
	private String fileData;
	private long size;

	public SymSyncFile(String fileName, String filePath, long size, String fileChecksum)
	{
		this.fileName = fileName;
		this.filePath = filePath;
		this.size = size;
		this.fileChecksum = fileChecksum;
	}

	public SymSyncFile(String fileName, String filePath, long size, String fileChecksum, String fileData)
	{
		this(fileName, filePath, size, fileChecksum);
		this.fileData = fileData;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFileChecksum()
	{
		return fileChecksum;
	}

	public void setFileChecksum(String fileMD5Hash)
	{
		this.fileChecksum = fileMD5Hash;
	}

	public String getFileData()
	{
		return fileData;
	}

	public void setFileData(String fileData)
	{
		this.fileData = fileData;
	}

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}
}
