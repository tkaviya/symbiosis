package net.blaklizt.symbiosis.sym_common;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/06/25
 * Time: 9:11 PM
 */

public class ConfigurationEngine
{
	/* staticConfiguration engine settings */
	protected static Logger log4j = Logger.getLogger(ConfigurationEngine.class);
	protected static ConfigurationEngine configurationEngine = null;

	/* core settings */
	protected String coreManagerName;
	protected String masterName;

	/* bluetooth settings */
	protected Float bluetoothProximityRange;
	protected String bluetoothMasterMac;
	protected Long bluetoothPollPeriod;
	protected boolean bluetoothProximityAutostart;

	public static ConfigurationEngine getInstance()
	{
		if (configurationEngine == null)
		{
			configurationEngine = new ConfigurationEngine();
		}
		return configurationEngine;
	}

	public String getMasterName()
	{
		return masterName;
	}

	public String getCoreManagerName() {
		return coreManagerName;
	}

	public String getBluetoothMasterMac()
	{
		return bluetoothMasterMac;
	}

	public Float getBluetoothProximityRange()
	{
		return bluetoothProximityRange;
	}

	public Long getBluetoothPollPeriod()
	{
		return bluetoothPollPeriod;
	}

	public boolean getBluetoothProximityAutostart() {
		return bluetoothProximityAutostart;
	}

	public void setCoreManagerName(String coreManagerName)
	{
		this.coreManagerName = coreManagerName;
	}

	public void setMasterName(String masterName)
	{
		this.masterName = masterName;
	}

	public void setBluetoothProximityRange(Float bluetoothProximityRange)
	{
		this.bluetoothProximityRange = bluetoothProximityRange;
	}

	public void setBluetoothMasterMac(String bluetoothMasterMac)
	{
		this.bluetoothMasterMac = bluetoothMasterMac;
	}

	public void setBluetoothPollPeriod(Long bluetoothPollPeriod)
	{
		this.bluetoothPollPeriod = bluetoothPollPeriod;
	}

	public void setBluetoothProximityAutostart(boolean bluetoothProximityAutostart)
	{
		this.bluetoothProximityAutostart = bluetoothProximityAutostart;
	}

}
