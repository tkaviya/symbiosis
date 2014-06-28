package net.blaklizt.symbiosis.sym_bluetooth;

public enum UUIDs
{
	SDP			(0x0001L),	//Bluetooth Core Specification
	RFCOMM		(0x0003L),	//RFCOMM with TS 07.10
	OBEX		(0x0008L),	//IrDA Interoperability OBject EXchange protocol
	HIDP		(0x0011L),	//Human Interface Device Profile (HID)
	AVCTP		(0x0017L),	//Audio/Video Control Transport Protocol (AVCTP)
	AVDTP		(0x0019L),	//Audio/Video Distribution Transport Protocol (AVDTP)
	L2CAP		(0x0100L),	//Bluetooth Core Specification
	BNEP		(0x000FL),	//Bluetooth Network Encapsulation Protocol
	SerialPort	(0x1101L),	//Serial Port Profile (SPP)
	ServiceDiscoveryServerServiceClassID	(0x1000L),	//Bluetooth Core Specification
	BrowseGroupDescriptorServiceClassID		(0x1001L),	//Bluetooth Core Specification
	PublicBrowseGroup			(0x1002L),	//Bluetooth Core Specification
	OBEXFileTransfer			(0x1109L),	//File Transfer Profile
	AudioSource					(0x110AL),	//Advanced Audio Distribution Profile (A2DP)
	AudioSink					(0x110BL),	//Advanced Audio Distribution Profile (A2DP)
	A_V_RemoteControlTarget		(0x110CL),	//Audio/Video Remote Control Profile (AVRCP)
	AdvancedAudioDistribution	(0x110DL),	//Advanced Audio Distribution Profile (A2DP)
	A_V_RemoteControl			(0x110EL),	//Audio/Video Remote Control Profile (AVRCP)
	A_V_RemoteControlController	(0x110FL),	//Audio/Video Remote Control Profile (AVRCP)
	OBEXObjectPushProfile		(0x1105L),	//OBEX Object Push Profile
	OBEXFileTransferProfile		(0x1106L),	//OBEX File Transfer Profile
	PersonalAreaNetworkingUser	(0x1115L),	//Personal Area NetworkingUser
	NetworkAccessPoint			(0x1116L),	//Network Access Point
	GroupNetwork				(0x1117L),	//Group Network
	Handsfree					(0x111EL),	//Hands-Free Profile
	HandsfreeAudioGateway		(0x111FL),	//Hands-Free Profile
	Headset1108					(0x1108L),	//Headset Profile
	HeadsetAudioGateway			(0x1112L),	//Headset Profile
	Headset1131					(0x1131L);	//Headset Profile

	public final Long value;

	UUIDs(Long value)
	{
		this.value = value;
	}
}
