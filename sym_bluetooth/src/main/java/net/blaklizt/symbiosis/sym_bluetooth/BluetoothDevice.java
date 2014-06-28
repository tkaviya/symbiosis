package net.blaklizt.symbiosis.sym_bluetooth;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/4/13
 * Time: 5:16 PM
 */
public class BluetoothDevice
{
	protected LinkedList<ServiceRecord> services = null;
	protected String friendlyName = null;
	protected DeviceClass deviceClass = null;
	protected boolean inProximity = false;
	protected RemoteDevice remoteDevice = null;

	public BluetoothDevice(RemoteDevice remoteDevice, DeviceClass deviceClass) {
		this.remoteDevice = remoteDevice;
		this.deviceClass = deviceClass;
		this.inProximity = true;
	}

	public String getFriendlyName() {

		if (friendlyName == null)
		{
			try
			{
				friendlyName = remoteDevice.getFriendlyName(true);
				inProximity = true;
			}
			catch (IOException ioe)
			{
				inProximity = false;
				System.out.println("WARNING: DEVICE IS NOW OUT OF RANGE!");
			}
		}
		return friendlyName;
	}

	public void addService(ServiceRecord serviceRecord)
	{
		if (services == null) services = new LinkedList<>();
		if (!services.contains(serviceRecord)) services.add(serviceRecord);
	}

	public LinkedList<ServiceRecord> getServices()
	{
		return services;
	}

	public String getBluetoothAddress() {
		return remoteDevice != null ? remoteDevice.getBluetoothAddress() : null;
	}

	public RemoteDevice getRemoteDevice() {
		return remoteDevice;
	}
}
