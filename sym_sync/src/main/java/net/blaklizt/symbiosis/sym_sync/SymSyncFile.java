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
	private String fileMD5Hash;
	private String fileData;
	private long size;

	public SymSyncFile(String fileName, String filePath, long size, String fileMD5Hash)
	{
		this.fileName = fileName;
		this.filePath = filePath;
		this.size = size;
		this.fileMD5Hash = fileMD5Hash;
	}

	public SymSyncFile(String fileName, String filePath, long size, String fileMD5Hash, String fileData)
	{
		this(fileName, filePath, size, fileMD5Hash);
		this.fileData = fileData;
	}
}
