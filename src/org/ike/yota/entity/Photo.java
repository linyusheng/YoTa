package org.ike.yota.entity;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields

	private Integer photoId;
	private Footprint footprint;
	private String photoStream;

	// Constructors

	/** default constructor */
	public Photo() {
	}

	/** full constructor */
	public Photo(Footprint footprint, String photoStream) {
		this.footprint = footprint;
		this.photoStream = photoStream;
	}

	// Property accessors

	public Integer getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Footprint getFootprint() {
		return this.footprint;
	}

	public void setFootprint(Footprint footprint) {
		this.footprint = footprint;
	}

	public String getPhotoStream() {
		return this.photoStream;
	}

	public void setPhotoStream(String photoStream) {
		this.photoStream = photoStream;
	}

}