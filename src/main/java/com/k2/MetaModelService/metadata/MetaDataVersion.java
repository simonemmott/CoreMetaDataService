package com.k2.MetaModelService.metadata;

import com.k2.MetaModelService.types.K2Version;
import com.k2.Util.Version.Version;

public class MetaDataVersion {

	public static K2Version toData(Version version) {
		if (version == null)
			return null;
		K2Version v = new K2Version(version.major(), version.minor(), version.point());
		return v;
	}

}
