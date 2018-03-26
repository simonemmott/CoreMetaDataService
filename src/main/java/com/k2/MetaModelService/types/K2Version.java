package com.k2.MetaModelService.types;

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
	
	@Column(name="MAJOR_VER", nullable=false)
	private int major;
	
	@Column(name="MINOR_VER", nullable=false)
	private int minor;
	
	@Column(name="POINT_VER", nullable=false)
	private int point;
	
	public K2Version() {}
	public K2Version(int major, int minor, int point) {
		this.major = major;
		this.minor = minor;
		this.point = point;
	}

	public K2Version(Version version) {
		this.major = version.major();
		this.minor = version.minor();
		this.point = version.point();
	}
	
	@Override
	public void increment(Increment inc) {
		switch(inc) {
		case MAJOR:
			major++;
			minor=0;
			point=0;
			break;
		case MINOR:
			minor++;
			point=0;
			break;
		case POINT:
			point++;
			break;
		default:
			break;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + major;
		result = prime * result + minor;
		result = prime * result + point;
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
		if (major != other.major())
			return false;
		if (minor != other.minor())
			return false;
		if (point != other.point())
			return false;
		return true;
	}

	/**
	 * Return a sensible rendering of the version as a String, e.g. "v1.2.3"
	 */
	@Override
	public String toString() {
		return "v" + major + "." + minor + "." + point;
	}
	@Override
	public boolean includes(Version ver) {
		if (ver.major() < major) return true;
		if (ver.major() > major) return false;
		if (ver.minor() < minor) return true;
		if (ver.minor() > minor) return false;
		if (ver.point() < point) return true;
		if (ver.point() > point) return false;
		if (equals(ver)) return true;
		return false;
	}

	@Override
	public int major() {return major;}

	@Override
	public int minor() { return minor; }

	@Override
	public int point() { return point; }

}
