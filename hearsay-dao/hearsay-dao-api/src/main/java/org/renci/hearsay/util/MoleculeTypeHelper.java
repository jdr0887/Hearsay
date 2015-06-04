package org.renci.hearsay.util;

import org.renci.hearsay.dao.model.MoleculeType;

/**
 * @author jdr0887
 */
public class MoleculeTypeHelper {

    public static MoleculeType accessionPrefix2MoleculeType(String prefix) {

        for (MoleculeType type : MoleculeType.values()) {
            if (type.getPrefixes().contains(prefix)) {
                return type;
            }
        }

        return null;
    }

}
