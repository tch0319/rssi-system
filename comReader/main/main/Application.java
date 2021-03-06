/*
 * 
 * 
 */
package main;

import gui.MainFrame;

import java.util.ArrayList;
import java.util.logging.Logger;

import utilities.Utilities;
import algorithm.PositionLocalizationAlgorithm;

import components.Receiver;
import components.RoomMap;

import dao.HardcodedMapDAO;
import dao.HardcodedReceiverDAO;
import dao.MapDAO;
import dao.ReceiverDAO;
import data.COMPortDataReader;
import data.Controller;
import data.DataProcessor;
import data.DataReader;
import data.DataWriter;
import data.DatabaseDataWriter;

/**
 * This is the starting point of the java application. It contains references to: <br>
 * <br>
 * <ul>
 * <li><code>MainFrame</code> that represents graphical user interface for the application.</li>
 * <li><code>Controller</code> that handles flow of batches of <code>Reading</code>s from one queue to another.</li>
 * <li><code>MapDAO</code> that handles reading and writing of <code>RoomMap</code>s to and from data source.</li>
 * <li><code>ReceiverDAO</code> that handles reading and writing of <code>Receiver</code>s to and from data source.</li>
 * <li><code>PositionLocalizationAlgorithm</code> that calculates <code>WatchPositionData</code>.</li>
 * </ul>
 * It is implemented as a Singleton.
 * 
 * @author Danilo
 * @see MainFrame
 * @see Controller
 * @see MapDAO
 * @see ReceiverDAO
 * @see PositionLocalizationAlgorithm
 */
public final class Application {

	/** Application singleton instance. */
	private static Application application;

	/** List of receivers. */
	private ArrayList<Receiver> receivers;

	/** The controller. */
	private Controller controller;

	/** The room map. */
	private RoomMap roomMap;

	/** The algorithm. */
	private PositionLocalizationAlgorithm algorithm;

	/** The logger. */
	private Logger logger;

	/** Main frame of the application. Shown when application is started. */
	private MainFrame mainFrame;

	/** The map dao. */
	private MapDAO mapDAO;

	/** The receiver dao. */
	private ReceiverDAO receiverDAO;

	private DataReader dataReader;
	private DataWriter dataWriter;
	private DataProcessor dataProcessor;
	
	/**
	 *  Private constructor of Singleton class. To instantiate an object of type <code>Application</code>, static method
	 * <code>getApplication</code> should be called.
	 * 
	 * @see #getApplication()
	 */
	private Application() {

		logger = Utilities.initializeLogger(this.getClass().getName());
		receiverDAO = new HardcodedReceiverDAO();
		mapDAO = new HardcodedMapDAO();
		//readConfigurationFile();
		controller = new Controller();
		logger.info("Application started.");
		//algorithm = new ProbabilityBasedAlgorithm(roomMap, receivers);
		dataReader = new COMPortDataReader();
		dataWriter = new DatabaseDataWriter();
		dataProcessor = new DataProcessor();
	}

	/**
	 * Initializes graphical user interface.
	 */
	public void initializeGUI() {
		mainFrame = new MainFrame();
	}

	

	/**
	 * Gets the application Singleton object. It is also lazy-initialized.
	 * 
	 * @return the application
	 */
	public static Application getApplication() {

		if (application == null) {
			application = new Application();
		}

		return application;
	}

	/**
	 * Gets the list of receivers.
	 *
	 * @return <code>List</code> of receivers
	 */
	public ArrayList<Receiver> getReceivers() {
		return receivers;
	}

	/**
	 * Sets the list of receivers.
	 *
	 * @param receivers <code>List</code> of <code>Receiver</code>s
	 */
	public void setReceivers(ArrayList<Receiver> receivers) {
		this.receivers = receivers;
	}

	/**
	 * Gets the controller.
	 *
	 * @return <code>Controller</code> object
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Gets the room map.
	 *
	 * @return <code>RoomMap</code> object
	 */
	public RoomMap getRoomMap() {
		return roomMap;
	}

	/**
	 * Sets the room map.
	 *
	 * @param roomMap <code>RoomMap</code> object
	 */
	public void setRoomMap(RoomMap roomMap) {
		this.roomMap = roomMap;
	}

	/**
	 * Gets the algorithm.
	 *
	 * @return <code>PositionLocalizationAlgorithm</code>
	 */
	public PositionLocalizationAlgorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm <code>PositionLocalizationAlgorithm</code>
	 */
	public void setAlgorithm(PositionLocalizationAlgorithm algorithm) {

		this.algorithm = algorithm;
	}

	/**
	 * Gets the main frame of the application.
	 *
	 * @return <code>MainFrame</code> of the application
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * Gets the map dao.
	 *
	 * @return <code>MapDao</code>
	 */
	public MapDAO getMapDAO() {
		return mapDAO;
	}

	/**
	 * Gets the receiver dao.
	 *
	 * @return <code>ReceiverDAO</code>
	 */
	public ReceiverDAO getReceiverDAO() {
		return receiverDAO;
	}
	
	public void startReadingsAndWritings() {
		
		// start readings from source (COM port or file)
		dataReader.readData();
		// start writing to destination (database or console or file)
		dataWriter.writeData();
		
		dataProcessor.processData();
	}
	
	public void stopReadingsAndWritings() {
		
		// stop readings from COM port
		dataReader.stopReading();
		// stop writing to destination (database or console or file)
		dataWriter.stopWriting();
		dataProcessor.stopReading();
	}

}