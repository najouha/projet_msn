package clientServer;

import java.math.BigInteger;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Class permettant de stocker les informations client. Comme son nom, son IP,
 * son port ouvert etc... Elle represente les informations que stock le serveur
 * pour un client.
 * 
 * @author raphael
 * 
 */
public class ClientServerData
{
	private String id;
	private String name;
	private InetAddress ip;
	private int port;

	public ClientServerData(String name, InetAddress ip, int port)
	{
		SecureRandom random = new SecureRandom();
		this.id = new BigInteger(130, random).toString(32);
		this.name = name;
		this.ip = ip;
		this.port = port;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public InetAddress getIp()
	{
		return ip;
	}

	public void setIp(InetAddress ip)
	{
		this.ip = ip;
	}

	public int getPort()
	{
		return port;
	}

	public void setPort(int port)
	{
		this.port = port;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof ClientServerData))
		{
			return false;
		} else if (obj == this)
		{
			return true;
		} else if (!this.name.equals(((ClientServerData) obj).name))
		{
			return false;
		} else if (this.port != ((ClientServerData) obj).port)
		{
			return false;
		} else if (!this.ip.equals(((ClientServerData) obj).ip))
		{
			return false;
		} else if (!this.id.equals(((ClientServerData) obj).id))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "Client: " + this.name + " " + this.ip + " " + this.port;
	}
}
