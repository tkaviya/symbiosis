package net.blaklizt.symbiosis.sym_proximity;

import net.blaklizt.symbiosis.sym_bluetooth.BluetoothDevice;
import net.blaklizt.symbiosis.sym_bluetooth.BluetoothManager;
import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import net.blaklizt.symbiosis.sym_tts_engine.TextToSpeechEngine;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.bluetooth.ServiceRecord;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/06/25
 * Time: 9:11 PM
 */
public class ProximityScanner implements Observer, Runnable {

	@Autowired
	protected BluetoothManager bluetoothManager;

	@Autowired
	protected TextToSpeechEngine textToSpeechEngine;

	protected final Logger logger = Configuration.getNewLogger(this.getClass().getSimpleName());
	protected static Boolean running = false;
	protected static ProximityScanner proximityScanner = null;

	protected static BluetoothDevice masterBluetoothDevice;

	protected ProximityScanner() {}

	public static ProximityScanner getInstance()
	{
		if (proximityScanner == null)
		{
			proximityScanner = new ProximityScanner();

			if (Boolean.parseBoolean(Configuration.getProperty("bluetooth", "bluetoothProximityAutostart")))
			{
				proximityScanner.start();
			}
		}
		return proximityScanner;
	}

	public void start()
	{
		if (BluetoothManager.isServiceRunning())
		{
			logger.info("Bluetooth services started.");
			running = true;
			logger.info("Starting proximity watcher.");
			BluetoothManager.getBluetoothManagerInstance().addObserver(proximityScanner);
			run();
		}
		else
		{
			logger.info("Bluetooth services not started. No bluetooth dependant services will be started");
		}
	}

	public void run()
	{
		while (running)
		{
			try
			{
				if (masterBluetoothDevice == null)
				{
					//device not yet detected in range, lets do some scanning
					BluetoothManager.enquireDevices();
					BluetoothManager.enquireServices(Configuration.getProperty("bluetooth", "bluetoothMasterMac"));

					LinkedList<BluetoothDevice> bluetoothDevices = BluetoothManager.getBluetoothDevices();

					for (BluetoothDevice bluetoothDevice : bluetoothDevices)
					{
						if (bluetoothDevice.getBluetoothAddress().equalsIgnoreCase(
							Configuration.getProperty("bluetooth","bluetoothMasterMac")))
						{
							logger.info("Found master bluetooth device. Resolving friendly name...");
							if (bluetoothDevice.getFriendlyName() != null)
							{
								masterBluetoothDevice = bluetoothDevice;
								logger.info("Monitoring proximity of " + masterBluetoothDevice.getFriendlyName());
								logger.debug("--> Polling period: " + Configuration.getProperty("bluetooth", "bluetoothPollPeriod"));
								logger.debug("--> Proximity range: " + Configuration.getProperty("bluetooth", "bluetoothProximityRange"));

								if (!connectServices(masterBluetoothDevice))
								{
									//device no longer in range
									logger.debug("Failed to connect services. Assuming the device has left proximity");
									masterBluetoothDevice = null;
								}
							}
						}
					}
				}
				else if (!connectServices(masterBluetoothDevice))
				{
					//device no longer in range
					logger.debug("Failed to connect services. Assuming the device has left proximity");
					masterBluetoothDevice = null;
				}
				else
				{
					logger.debug("Device within proximity");
					//device in range, lets do some cool stuff.

					//unlock pc here

					//play music || steal audio via a2dp here

					//update master on developments.
				}

				Thread.sleep(Long.parseLong(Configuration.getProperty("bluetooth","bluetoothPollPeriod")));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				stop();
			}
		}
	}

	public boolean connectServices(BluetoothDevice remoteDevice)
	{
		if (remoteDevice == null || remoteDevice.getServices() == null) return false;

		synchronized (BluetoothManager.lock)
		{
			for (ServiceRecord serviceRecord : remoteDevice.getServices())
			{
				logger.debug("Connecting Service Record: " + serviceRecord.toString());
				boolean success = BluetoothManager.connectService(serviceRecord);
				if (success) return success;
			}
		}
		return false;
	}

	public void stop()
	{
		running = false;
	}

	@Override
	public void update(Observable o, Object arg) {

//		RemoteDevice remoteDevice = (RemoteDevice)arg;
//
//		logger.debug("Device discovered: " + remoteDevice.getBluetoothAddress());
//
//		if (remoteDevice.getBluetoothAddress().equalsIgnoreCase(configurationEngine.getBluetoothMasterMac()))
//		{
//			try
//			{
//				textToSpeechEngine.speak("Hi " + configurationEngine.getMasterName()
//									 + ". I'm here if you need me.");
//			}
//			catch (Exception ex)
//			{
//				ex.printStackTrace();
//				logger.error("Failed to get friendly name: " + ex.getMessage());
//			}
//		}
	}

//	public void setBluetoothManager(BluetoothManager bluetoothManager)
//	{
//		logger.debug("Assigning bluetoothManager to proximityScanner");
//		ProximityScanner.bluetoothManager = bluetoothManager;
//	}
//
//	public void setTextToSpeechEngine(TextToSpeechEngine textToSpeechEngine)
//	{
//		logger.debug("Assigning textToSpeechEngine to proximityScanner");
//		ProximityScanner.textToSpeechEngine = textToSpeechEngine;
//	}

}
