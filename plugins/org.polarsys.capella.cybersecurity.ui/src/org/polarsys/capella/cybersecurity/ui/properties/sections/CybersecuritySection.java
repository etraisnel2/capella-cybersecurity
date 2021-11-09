/*******************************************************************
* Copyright � 2021 Thales Global Services SAS
*
* Licensed under the Thales Inner Source Software License:
* Version 1.2, InnerOpen - Distribution Controlled
*
* You may not use this file except in compliance with the License.
* You may obtain a copy of the License at https://gitlab.thalesdigital.io/Tiss-Organization/tiss-licenses
* See the License for the specific language governing permissions and limitations under the License.
*******************************************************************/
package org.polarsys.capella.cybersecurity.ui.properties.sections;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.ui.properties.controllers.AbstractMultipleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

public abstract class CybersecuritySection extends AbstractSection {

  protected CyberMultipleSemanticField realizedWidget;
  protected boolean showRealizations;
  
  public CybersecuritySection() {
    this(true);
  }
  
  public CybersecuritySection(boolean showRealizations) {
    this.showRealizations = showRealizations;
  }

  protected void addRealizedWidget(String label) {
    if (showRealizations) {
      realizedWidget = new CyberMultipleSemanticField(getReferencesGroup(), ICommonConstants.EMPTY_STRING,
          getWidgetFactory(), getController());
      realizedWidget.setLabel(label);
      realizedWidget.setDisplayedInWizard(isDisplayedInWizard());
    }
  }
  
  protected abstract AbstractMultipleSemanticFieldController getController();

  public void loadRealizedWidget(EObject capellaElement, EReference feature) {
    if(showRealizations) {
      realizedWidget.loadData(capellaElement, feature);
    }
  }

  public void addRealizedFields(List<AbstractSemanticField> fields) {
    if(showRealizations) {
      fields.add(realizedWidget);
    }
  }
  
  protected boolean isRealizedArchitecture(EObject capellaElement) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(capellaElement);
    Type aType = BlockArchitectureExt.getBlockArchitectureType(architecture);
    return aType != Type.OA && aType != Type.EPBS;
  }
}
