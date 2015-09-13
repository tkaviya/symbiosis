package net.blaklizt.symbiosis.sym_bluetooth;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/06/25
 * Time: 11:19 PM
 */
public class BluetoothServer
{
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());

	private void initDevice()
	{
		try
		{ // make the server's device discoverable
			LocalDevice local = LocalDevice.getLocalDevice();
			logger.info("Device name: " + local.getFriendlyName());
			logger.info("Bluetooth Address: " + local.getBluetoothAddress());
			boolean res = local.setDiscoverable(DiscoveryAgent.GIAC);
			logger.info("Discoverability set: " + res);
		}
		catch (BluetoothStateException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			System.exit(1);
		}
	} // end of initDevice()
}
