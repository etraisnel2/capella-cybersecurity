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
package org.polarsys.capella.cybersecurity.test.activityExplorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class CyberActivityExplorerTestSuite extends BasicTestSuite {

  public static Test suite() {
    return new CyberActivityExplorerTestSuite();
  }

  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new CyberOABActivityExplorerTestCase());
    tests.add(new CyberLABActivityExplorerTestCase());
    tests.add(new CyberSABActivityExplorerTestCase());
    tests.add(new CyberPABActivityExplorerTestCase());
    tests.add(new OperationalTDBActivityExplorerTestCase());
    tests.add(new LogicalTDBActivityExplorerTestCase());
    tests.add(new SystemTDBActivityExplorerTestCase());
    tests.add(new PhysicalTDBActivityExplorerTestCase());
    tests.add(new CyberTransitionsFromOAActivityExplorerTestCase());
    tests.add(new CyberTransitionsFromSAActivityExplorerTestCase());
    tests.add(new CyberTransitionsFromLAActivityExplorerTestCase());
    
    // scenarios
    tests.add(new CyberLogicalESActivityExplorerTestCase());
    tests.add(new CyberLogicalFSActivityExplorerTestCase());
    tests.add(new CyberLogicalISActivityExplorerTestCase());
    tests.add(new CyberOASActivityExplorerTestCase());
    tests.add(new CyberOESActivityExplorerTestCase());
    tests.add(new CyberPhysicalESActivityExplorerTestCase());
    tests.add(new CyberPhysicalFSActivityExplorerTestCase());
    tests.add(new CyberPhysicalISActivityExplorerTestCase());
    tests.add(new CyberSystemESActivityExplorerTestCase());
    tests.add(new CyberSystemFSActivityExplorerTestCase());
    tests.add(new CyberSystemISActivityExplorerTestCase());
    
    return tests;
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CyberEmptyModel");
  }

}
