package org.eclipse.scout.apps.contacts.client.organization;

import org.eclipse.scout.apps.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contacts.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.apps.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.apps.contacts.shared.contact.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(OrganizationTablePageData.class)
@ClassId("3a6c88e3-66cb-4a87-b920-a81471ad1aa8")
public class OrganizationTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IOrganizationService.class).getOrganizationTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [RicoHahn] verify translation
        return TEXTS.get("Organizations");
    }

    @ClassId("e1b84f1c-245a-40f4-92bf-9d36a0e3432b")
    public class Table extends AbstractTable {

      public CityColumn getCityColumn() {
        return getColumnSet().getColumnByClass(CityColumn.class);
      }

      public CountryColumn getCountryColumn() {
        return getColumnSet().getColumnByClass(CountryColumn.class);
      }

      public HomepageColumn getHomepageColumn() {
        return getColumnSet().getColumnByClass(HomepageColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      @Order(1)
      @ClassId("db7680b4-fda9-4738-a4fd-18ec1dc561aa")
      public class OrganizationIdColumn extends AbstractStringColumn {
        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }

        @Override
        protected boolean getConfiguredPrimaryKey() {
          return true;
        }
      }

      @Order(2)
      @ClassId("6fd9f2b1-2ebe-456d-835a-75d598a4babb")
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Organization");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3)
      @ClassId("3d397406-d8e4-4422-997c-957be67cb08c")
      public class CityColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("City");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4)
      @ClassId("b8983e31-f911-46ce-8e6e-2743a39e8979")
      public class CountryColumn extends AbstractSmartColumn<String> {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Country");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }

        @Override
        protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
          return CountryLookupCall.class;
        }
      }

      @Order(2000)
      @ClassId("48be99c7-6be6-4fd0-8a3a-7ce3d6264b52")
      public class HomepageColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Homepage");
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }
    }
}
