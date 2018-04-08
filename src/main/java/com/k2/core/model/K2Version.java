package com.k2.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEmbeddable;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.Util.Version.Increment;
import com.k2.Util.Version.Version;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEmbeddable
@Embeddable
@Table(name="VERSIONS")
public class K2Version implements Version {
	
	@Column(name="MAJORVER", nullable=false)
	private int majorVer;
	public int getMajor() { return majorVer; }
	
	@Column(name="MINORVER", nullable=false)
	private int minorVer;
	public int getMinor() { return minorVer; }
	
	@Column(name="POINTVER", nullable=false)
	private int pointVer;
	public int getPoint() { return pointVer; }
	
	@Column(name="BUILDNUMBER", nullable=false)
	private int buildNumber;
	public int getBuildNumber() { return buildNumber; }
	
	public K2Version() {}
	public K2Version(int major, int minor, int point) {
		this.majorVer = major;
		this.minorVer = minor;
		this.pointVer = point;
	}

	public K2Version(Version version) {
		this.majorVer = (version==null) ? 0 : version.major();
		this.minorVer = (version==null) ? 0 : version.minor();
		this.pointVer = (version==null) ? 0 : version.point();
	}
	
	@Override
	public void increment(Increment inc) {
		switch(inc) {
		case MAJOR:
			majorVer++;
			minorVer=0;
			pointVer=0;
			break;
		case MINOR:
			minorVer++;
			pointVer=0;
			break;
		case POINT:
			pointVer++;
			break;
		default:
			break;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + majorVer;
		result = prime * result + minorVer;
		result = prime * result + pointVer;
		return result;
	}

	/**
	 * Check for version equality based on the major, minor and point version numbers only.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (majorVer != other.major())
			return false;
		if (minorVer != other.minor())
			return false;
		if (pointVer != other.point())
			return false;
		return true;
	}

	/**
	 * Return a sensible rendering of the version as a String, e.g. "v1.2.3"
	 */
	@Override
	public String toString() {
		return "v" + majorVer + "." + minorVer + "." + pointVer;
	}
	@Override
	public boolean includes(Version ver) {
		if (ver.major() < majorVer) return true;
		if (ver.major() > majorVer) return false;
		if (ver.minor() < minorVer) return true;
		if (ver.minor() > minorVer) return false;
		if (ver.point() < pointVer) return true;
		if (ver.point() > pointVer) return false;
		if (equals(ver)) return true;
		return false;
	}

	@Override
	public int major() {return majorVer;}

	@Override
	public int minor() { return minorVer; }

	@Override
	public int point() { return pointVer; }

}
