package net.blaklizt.symbiosis.sym_bluetooth;

import org.apache.log4j.Logger;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/06/25
 * Time: 11:19 PM
 */
public class BluetoothServer
{
	Logger log4j = Logger.getLogger(this.getClass().getSimpleName());

	private void initDevice()
	{
		try
		{ // make the server's device discoverable
			LocalDevice local = LocalDevice.getLocalDevice();
			log4j.info("Device name: " + local.getFriendlyName());
			log4j.info("Bluetooth Address: " + local.getBluetoothAddress());
			boolean res = local.setDiscoverable(DiscoveryAgent.GIAC);
			log4j.info("Discoverability set: " + res);
		}
		catch (BluetoothStateException e) {
			e.printStackTrace();
			log4j.error(e.getMessage());
			System.exit(1);
		}
	} // end of initDevice()
}
