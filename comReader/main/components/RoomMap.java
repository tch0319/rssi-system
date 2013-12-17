
/*
 * File: RoomMap.java
 * Date				Author				Changes
 * 08 Nov 2013		Tommy Griese		create version 1.0
 * 					Yentran Tran
 * 03 Dec 2013		Tommy Griese		Code refactoring
 */
package components;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import algorithm.helper.PointRoomMap;

/**
 * The class RoomMap represents the map of a room.
 * 
 * @version 1.1 03 Dec 2013
 * @author Tommy Griese
 */
public class RoomMap {
	
	/** The default granularity constant for the roommap. */
	public static final double GRANULARITY_DEFAULT = 0.25;
	
	private static final double DEFAULT_SCALING = 100.0; // pixel to meter ratio
	
	/** The actual granularity for the roommap. */
	private double granularity;
	
	/** The start value for the room map in x. */
	private double xFrom;
	
	/** The end value for the room map in x. */
	private double xTo;
	
	/** The start value for the room map in y. */
	private double yFrom;
	
	/** The end value for the room map in y. */
	private double yTo;
	
	/** The path to the image of the room map. */
	private Image image;
	
	/** Each point of this list contains a weighted point in a room ({@link algorithm.helper.PointRoomMap}). */
	private ArrayList<PointRoomMap> pointsRoomMap;
	
	private double zeroOffsetXInMeters;
	private double zeroOffsetYInMeters;
	private String title;
	private List<Receiver> receivers;
	private double scaling;
	
	/**
	 * Instantiates a new room map.
	 *
	 * @param xFrom the start value for the room map in x
	 * @param xTo the end value for the room map in x
	 * @param yFrom the start value for the room map in y
	 * @param yTo the end value for the room map in y
	 * @param granularity the granularity of the room map
	 * @param pathToImage the path to the image
	 */
	public RoomMap(double xFrom, double xTo, double yFrom, double yTo, double granularity, Image image) {
		super();
		this.xFrom = xFrom;
		this.xTo = xTo;
		
		this.yFrom = yFrom;
		this.yTo = yTo;
		this.image = image;
		this.granularity = granularity;
		
		
		initialize();
	}
	
	/**
	 * Instantiates a new room map.
	 *
	 * @param xFrom the start value for the room map in x
	 * @param xTo the end value for the room map in x
	 * @param yFrom the start value for the room map in y
	 * @param yTo the end value for the room map in y
	 * @param pathToImage the path to the image of the room map
	 */
	public RoomMap(double xFrom, double xTo, double yFrom, double yTo, Image image) {
		super();
		this.xFrom = xFrom;
		this.xTo = xTo;
		
		this.yFrom = yFrom;
		this.yTo = yTo;
		this.image = image;
		
		this.granularity = RoomMap.GRANULARITY_DEFAULT;
		
		initialize();
	}
	
	public RoomMap(Image image, double zeroOffsetXInMeters,
			double zeroOffsetYInMeters, String title, List<Receiver> receivers) {
		super();
		this.image = image;
		this.zeroOffsetXInMeters = zeroOffsetXInMeters;
		this.zeroOffsetYInMeters = zeroOffsetYInMeters;
		this.title = title;
		this.receivers = receivers;
		this.scaling = 1;
	}

	/**
	 * Gets the start value for the room map in x.
	 *
	 * @return the x from
	 */
	public double getXFrom() {
		return this.xFrom;
	}
	
	/**
	 * Gets the end value for the room map in x.
	 *
	 * @return the x to
	 */
	public double getXTo() {
		return this.xTo;
	}
	
	/**
	 * Gets the start value for the room map in y.
	 *
	 * @return the y from
	 */
	public double getYFrom() {
		return this.yFrom;
	}
	
	/**
	 * Gets the end value for the room map in y.
	 *
	 * @return the y to
	 */
	public double getYTo() {
		return this.yTo;
	}
	
	/**
	 * Gets the image.
	 *
	 * @return image Image object
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image Image object
	 */
	public void setPathToImage(Image image) {
		this.image = image;
	}
	
	/**
	 * Creates an array list of the room map with weighted points ({@link algorithm.helper.PointRoomMap}).
	 */
	public void initialize() {

		this.pointsRoomMap = new ArrayList<PointRoomMap>();
		for (double i = this.xFrom; i <= this.xTo; i += this.granularity) { // x-Axe
			for (double j = this.yFrom; j <= this.yTo; j += this.granularity) { // y-Axe
				this.pointsRoomMap.add(new PointRoomMap(i, j));
			}
		}
	}
	
	/**
	 * Gets the room map points.
	 *
	 * @return the room map points
	 */
	public ArrayList<PointRoomMap> getRoomMapPoints() {
		return this.pointsRoomMap;
	}
	
	/**
	 * Gets the granularity.
	 *
	 * @return the granularity
	 */
	public double getGranularity() {
		return this.granularity;
	}

	public String getTitle() {
		return title;
	}

	public List<Receiver> getReceivers() {
		return receivers;
	}

}