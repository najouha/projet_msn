package dataLink;

import java.net.InetAddress;

public abstract class Protocol
{
	public abstract void sendMessage(String message, InetAddress adress, int port);

	public abstract void sendMessage(String message);

	public abstract String readMessage();
}
