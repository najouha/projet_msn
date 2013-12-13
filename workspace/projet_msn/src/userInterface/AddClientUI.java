package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;

import model.Client;
import model.ClientDialog;
import model.ClientServerData;

/**
 * 
 * @author Dorian, Mickaël, Raphaël, Thibault
 * 
 */
public class AddClientUI
{
	private static JFrame mainFrameAdd;
	public static Client client;
	public static ClientDialog dialog;

	public static HashMap<String, String[]> clientListAdd;
	private static Set<String> keyClientListAdd;
	private static Vector<JListData> simpleClientListAdd;
	public static JList<JListData> displayListAdd;

	/**
	 * Création de la fenêtre principale.
	 */
	public AddClientUI(Client clientRef, ClientDialog dialogRef)
	{
		client = clientRef;
		dialog = dialogRef;
		clientListAdd = client.getClientList();
		keyClientListAdd = clientListAdd.keySet();
		initialize();

		new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				HashMap<String, String[]> listTemp = new HashMap<String, String[]>(client.getClientList());
				while (true)
				{
					if (!listTemp.equals(client.getClientList()))
					{
						listTemp = new HashMap<String, String[]>(client.getClientList());
						refreshClient();
					}
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e)
					{
						// e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void refreshClient()
	{
		clientListAdd = client.getClientList();
		keyClientListAdd = clientListAdd.keySet();
		simpleClientListAdd = new Vector<JListData>();

		for (ClientServerData client : dialog.getClients())
		{
			keyClientListAdd.remove(client.getId());
		}

		for (String key : keyClientListAdd)
		{
			JListData clientListData = new JListData(key, clientListAdd.get(key)[0] + " " + clientListAdd.get(key)[1]);
			simpleClientListAdd.add(clientListData);
		}
		System.out.println("nouvelle list" + simpleClientListAdd);
		displayListAdd.setListData(simpleClientListAdd);
		getMainFrameAdd().getContentPane().add(displayListAdd, BorderLayout.CENTER);
		displayListAdd.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		setMainFrameAdd(new JFrame("Ajout"));

		displayListAdd = new JList<JListData>();
		displayListAdd.setListData(new JListData[0]);
		displayListAdd.updateUI();
		displayListAdd.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{

			}

			@Override
			public void mousePressed(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					System.out.println("dbclick");
					JListData clientList = AddClientUI.displayListAdd.getSelectedValue();
					if (clientList != null)
					{
						ClientServerUI.client.addClientToDialog(clientList.getKey(), dialog);
					}
					mainFrameAdd.dispose();
				}
			}
		});

		getMainFrameAdd().setLocation(400, 300);
		getMainFrameAdd().setMinimumSize(new Dimension(200, 300));
		getMainFrameAdd().setResizable(false);
		getMainFrameAdd().setVisible(true);
		getMainFrameAdd().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		refreshClient();
	}

	public static JFrame getMainFrameAdd()
	{
		return mainFrameAdd;
	}

	public static void setMainFrameAdd(JFrame frame)
	{
		AddClientUI.mainFrameAdd = frame;
	}
}
