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
package org.polarsys.capella.cybersecurity.transition.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.attachment.AttachmentHelper;
import org.polarsys.capella.core.transition.common.handlers.contextscope.ContextScopeHandlerHelper;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;
import org.polarsys.capella.core.transition.system.topdown.handlers.transformation.TopDownTransformationHelper;
import org.polarsys.capella.cybersecurity.model.CybersecurityPackage;
import org.polarsys.capella.cybersecurity.model.PrimaryAsset;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IPremise;

public class PrimaryAssetRule extends AbstractCapellaElementRule {

  public PrimaryAssetRule() {
    registerUpdatedReference(CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_MEMBERS);
    registerUpdatedReference(CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_THREAT_APPLICATIONS);
  }

  @Override
  protected EClass getSourceType() {
    return CybersecurityPackage.Literals.PRIMARY_ASSET;
  }

  @Override
  protected void premicesRelated(EObject element, ArrayList<IPremise> needed) {
    super.premicesRelated(element, needed);
    needed.addAll(createDefaultPrecedencePremices(element, CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_MEMBERS));
    needed.addAll(createDefaultPrecedencePremices(element, CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_THREAT_APPLICATIONS));
  }

  @Override
  protected void attachRelated(EObject element, EObject result, IContext context) {
    super.attachRelated(element, result, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_MEMBERS, context);
    AttachmentHelper.getInstance(context).attachTracedElements(element, result,
        CybersecurityPackage.Literals.PRIMARY_ASSET__OWNED_THREAT_APPLICATIONS, context);
  }

  @Override
  protected void retrieveGoDeep(EObject source, List<EObject> result, IContext context) {
    super.retrieveGoDeep(source, result, context);
    result.addAll(((PrimaryAsset) source).getOwnedMembers());
    result.addAll(((PrimaryAsset) source).getOwnedThreatApplications());
    
    if (ContextScopeHandlerHelper.getInstance(context).contains(ITransitionConstants.SOURCE_SCOPE, source, context)) {
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          ((PrimaryAsset) source).getOwnedMembers(), context);
      ContextScopeHandlerHelper.getInstance(context).addAll(ITransitionConstants.SOURCE_SCOPE,
          ((PrimaryAsset) source).getOwnedThreatApplications(), context);
    }
  }

  @Override
  public EClass getTargetType(EObject element, IContext context) {
    if (!TopDownTransformationHelper.getInstance(context).retrieveTargetTracedElements(element, context).isEmpty()) {
      return TopDownTransformationHelper.getInstance(context).retrieveTargetTracedElements(element, context).stream()
          .collect(Collectors.toList()).get(0).eClass();
    }
    return super.getTargetType(element, context);
  }

}
