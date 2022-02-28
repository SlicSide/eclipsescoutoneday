package org.eclipse.scout.apps.contacts.client.organization;


import org.eclipse.scout.apps.contacts.client.person.PersonTablePage;
import org.eclipse.scout.apps.contacts.shared.organization.OrganizationNodeTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.classid.ClassId;

import java.util.List;

@Data(OrganizationNodeTablePageData.class)
@ClassId("3773fffe-1510-452d-8237-094c68c5d75a")
public class OrganizationNodePage extends AbstractPageWithNodes {
  private String organizationId;

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    PersonTablePage personTablePage = new PersonTablePage();
    personTablePage.setOrganizationId(getOrganizationId());
    pageList.add(personTablePage);
  }
}

