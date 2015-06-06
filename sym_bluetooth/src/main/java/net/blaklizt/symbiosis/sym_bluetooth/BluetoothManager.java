package net.blaklizt.symbiosis.sym_bluetooth;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import org.apache.log4j.Logger;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;

public class BluetoothManager extends Observable implements DiscoveryListener
{
	protected static final Logger logger = Configuration.getNewLogger(BluetoothManager.class.getSimpleName());
	protected static final String BASE_UUID_VALUE = "fa87c0d0afac11de8a390800200c9a66";

	public static Object lock = new Object();

	protected static BluetoothManager bluetoothManager;
	protected static LinkedList<BluetoothDevice> remoteDevices = new LinkedList();
	protected static LinkedList<ServiceRecord> remoteServices = new LinkedList();
	protected static String localBluetoothName = null;
	protected static String localBluetoothAddress = null;
	protected static boolean serviceRunning = false;
	protected static LocalDevice localDevice = null;
	protected static DiscoveryAgent agent = null;

	protected static final UUID uuids[] = new UUID[] {

			new UUID(BASE_UUID_VALUE, false),

			new UUID(UUIDs.OBEX.value),
			new UUID(UUIDs.OBEXFileTransfer.value),
			new UUID(UUIDs.OBEXObjectPushProfile.value),
			new UUID(UUIDs.OBEXFileTransferProfile.value),

			new UUID(UUIDs.AudioSource.value),
			new UUID(UUIDs.AudioSink.value),

			new UUID(UUIDs.A_V_RemoteControlTarget.value),
			new UUID(UUIDs.A_V_RemoteControl.value),
			new UUID(UUIDs.A_V_RemoteControlController.value),

			new UUID(UUIDs.Handsfree.value),
			new UUID(UUIDs.HandsfreeAudioGateway.value),

			new UUID(UUIDs.Headset1108.value),
			new UUID(UUIDs.HeadsetAudioGateway.value),
			new UUID(UUIDs.Headset1131.value),
	};

	@Override
	public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass)
	{
		// add the device to the list
		BluetoothDevice bluetoothDevice = new BluetoothDevice(remoteDevice, deviceClass);

		boolean newDevice = true;

		for (BluetoothDevice bd : getBluetoothDevices())
		{
			if (bd.getBluetoothAddress().equalsIgnoreCase(bluetoothDevice.getBluetoothAddress()))
				newDevice = false;
		}

		if (newDevice)
		{
			getBluetoothDevices().add(bluetoothDevice);
			//notifyObservers(getBluetoothDevices());
		}
	}

	@Override
	public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {
		for (ServiceRecord serviceRecord : serviceRecords) {

			for (BluetoothDevice device : remoteDevices)
			{
				if (serviceRecord.getHostDevice().getBluetoothAddress().equalsIgnoreCase(device.getBluetoothAddress()))
				{
					logger.debug(device.getBluetoothAddress() + " | found service " + serviceRecord);
					device.addService(serviceRecord);
				}
			}
		}
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
		logger.debug("Service search completed - code: " + respCode);
		synchronized (lock) {
			lock.notify();
		}
	}

	@Override
	public void inquiryCompleted(int discType) {
		switch (discType) {
			case DiscoveryListener.INQUIRY_COMPLETED:
				logger.debug("INQUIRY_COMPLETED");
				break;

			case DiscoveryListener.INQUIRY_TERMINATED:
				logger.debug("INQUIRY_TERMINATED");
				break;

			case DiscoveryListener.INQUIRY_ERROR:
				logger.error("INQUIRY_ERROR");
				break;

			default:
				logger.warn("Unknown Response Code");
				break;
		}
		synchronized (lock) {
			lock.notify();
		}
	}

	public static BluetoothManager getBluetoothManagerInstance()
	{
		if (bluetoothManager == null)
		{
			try
			{
				bluetoothManager = new BluetoothManager();
				remoteDevices.clear();
				remoteServices.clear();
				getLocalAddressAndName();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bluetoothManager = null;
			}
		}
		return bluetoothManager;
	}

	protected static LocalDevice getLocalDevice()
	{
		if (localDevice == null) {
			try {
				localDevice = LocalDevice.getLocalDevice();
			}
			catch (Exception ex) {
				logger.warn("Bluetooth not available. Bluetooth services will not run");
				localDevice = null;
			}
		}
		return localDevice;
	}

	protected static DiscoveryAgent getDiscoveryAgent()
	{
		if (agent == null && getLocalDevice() != null) {
			try {
				agent = getLocalDevice().getDiscoveryAgent();
			} catch (Exception ex) {
				ex.printStackTrace();
				agent = null;
			}
		}
		return agent;
	}

	protected static void getLocalAddressAndName()
	{
		try
		{
			logger.info("Getting local device address & name...");
			setLocalBluetoothAddress(getLocalDevice().getBluetoothAddress());
			setLocalBluetoothName(getLocalDevice().getFriendlyName());
			logger.info("Local Bluetooth Address: " + getLocalBluetoothAddress());
			logger.info("Local Bluetooth Name: " + getLocalBluetoothName());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error("Failed to get local device address and name: " + ex.getMessage());
		}
	}

	public static void enquireDevices()
	{
		try
		{
			logger.info("Starting device inquiry...");
			getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, getBluetoothManagerInstance());
			synchronized (lock) { lock.wait(); }
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Device inquiry completed.");
	}

	public static void enquireServices(String macAddress)
	{
		logger.info("Starting service inquiry...");

		if (macAddress == null) return;

		for (BluetoothDevice bluetoothDevice : getBluetoothDevices())
		{
			if (bluetoothDevice.getBluetoothAddress().equalsIgnoreCase(macAddress))
			{
				logger.info("Enquiring services from: " + bluetoothDevice.getBluetoothAddress());

				try
				{
					int transactionID = getDiscoveryAgent().searchServices(null, new UUID[] { new UUID(UUIDs.AudioSource.value) }, //uuids,
							bluetoothDevice.getRemoteDevice(), getBluetoothManagerInstance());

					logger.debug("Finished querying services. TransactionID = " + transactionID);

					synchronized (lock) { lock.wait(); }
				}
				catch (InterruptedException e) { e.printStackTrace(); }
				catch (Exception e) { e.printStackTrace(); }
			}
			else
			{
				logger.debug("Skipping device: " + bluetoothDevice.getBluetoothAddress());
			}
		}
		logger.debug("Service Inquiry Completed. ");
	}

//	protected static void printDevices()
//	{
//		int deviceCount = getBluetoothDevices().size();
//
//		if (deviceCount <= 0) { logger.info("No Devices Found ."); }
//		else
//		{
//			logger.info("Listing devices:");
//			int count = 1;
//			for (RemoteDevice remoteDevice : getBluetoothDevices())
//			{
//				try
//				{
//					logger.info(count + ". "
//							   + remoteDevice.getBluetoothAddress() + " ("
//							   + remoteDevice.getFriendlyName(false) + ")");
//					count++; //if query succeeds, then increment count
//				}
//				catch (IOException ix) {
//					//not within range anymore
//					logger.error(remoteDevice.getBluetoothAddress() + " is no longer in range");
//				}
//				catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//
//	}
//
	public static boolean connectService(ServiceRecord serviceRecord)
	{
		logger.info("Connecting service [" + serviceRecord.toString() + "]");
		try
		{
			String url = serviceRecord.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);

			StreamConnection conn = (StreamConnection) Connector.open(url, Connector.READ_WRITE);

			logger.debug(url + " ----=" + conn);

			DataInputStream din = new DataInputStream(conn.openDataInputStream());

			synchronized (lock)
			{
				try { lock.wait(10); }
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}

			String response = "";
			while (din.available() != 0)
				response += din.readChar();

			logger.debug("RESPONSE: " + response);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

//	public void start()
//	{
//		setServiceRunning(true);
//	}

//	@Override
//	public void run()
//	{
//		try
//		{
//			if (isServiceRunning())
//			{
//				enquireDevices();
//
//				enquireServices();
//
//				printDevices();
//
//				connectServices();
//			}
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//		}
//	}


	//make this a Singleton
	protected BluetoothManager() {
		System.setProperty("bluecove.stack", "widcomm");
		//System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public static String getLocalBluetoothAddress() {
		return localBluetoothAddress;
	}

	protected static void setLocalBluetoothAddress(String localBluetoothAddress) {
		BluetoothManager.localBluetoothAddress = localBluetoothAddress;
	}

	public static String getLocalBluetoothName() {
		return localBluetoothName;
	}

	protected static void setLocalBluetoothName(String localBluetoothName) {
		BluetoothManager.localBluetoothName = localBluetoothName;
	}

	public static boolean isServiceRunning() {
		return serviceRunning;
	}

	public static void setServiceRunning(boolean serviceRunning) {
		BluetoothManager.serviceRunning = serviceRunning;
	}

	protected static LinkedList<ServiceRecord> getRemoteServices() {
		return remoteServices;
	}

	public static LinkedList<BluetoothDevice> getBluetoothDevices() {
		return remoteDevices;
	}
}
