/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.cybersecurity.model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.cybersecurity.model.CybersecurityPackage;
import org.polarsys.capella.cybersecurity.model.Threat;

/**
 * @generated
 */
public class ThreatHelper {

  private static final ThreatHelper instance = new ThreatHelper();

  /**
   * @generated
   */
  public static ThreatHelper getInstance() {
    return instance;
  }

  /**
   * @generated NOT
   */
  public Object doSwitch(Threat object, EStructuralFeature feature) {
    // handle derivated feature

    if (feature.equals(CybersecurityPackage.Literals.THREAT__REALIZED_THREATS)) {
      return getRealizedThreats(object);
    } else if (feature.equals(CybersecurityPackage.Literals.THREAT__REALIZING_THREATS)) {
      return getRealizingThreats(object);
    }

    // delegate to parent helper
    return org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper.getInstance()
        .doSwitch(object, feature);

  }

  /**
   * @generated NOT
   */
  protected List<Threat> getRealizedThreats(Threat element) {
    List<Threat> ret = new ArrayList<>();
    for (AbstractTrace obj : element.getOutgoingTraces()) {
      if (obj instanceof TransfoLink) {
        TransfoLink link = (TransfoLink) obj;
        if (link.getTarget() instanceof Threat) {
          ret.add((Threat) link.getTarget());
        }
      }
    }
    return ret;
  }

  /**
   * @generated NOT
   */
  protected List<Threat> getRealizingThreats(Threat element) {
    List<Threat> ret = new ArrayList<>();
    for (AbstractTrace obj : element.getIncomingTraces()) {
      if (obj instanceof TransfoLink) {
        TransfoLink link = (TransfoLink) obj;
        if (link.getSource() instanceof Threat) {
          ret.add((Threat) link.getSource());
        }
      }
    }
    return ret;
  }

}