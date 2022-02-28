package org.eclipse.scout.apps.contacts.client.person;

import org.eclipse.scout.apps.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.apps.contacts.client.person.PersonTablePage.Table;
import org.eclipse.scout.apps.contacts.shared.contact.PersonTablePageData;
import org.eclipse.scout.apps.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.apps.contacts.shared.person.IPersonService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.util.Set;

@Data(PersonTablePageData.class)
@ClassId("8f3f678a-c455-4621-b97b-bec45abf81c1")
public class PersonTablePage extends AbstractPageWithTable<Table> {
  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Persons");
  }

  @ClassId("194fe33e-3e7d-455e-b451-f42bf02b9440")
  public class Table extends AbstractTable {

    @Override
    protected Class<? extends IMenu> getConfiguredDefaultMenu() {
      return EditMenu.class;
    }

    public PersonIdColumn getPersonIdColumn() {
      return getColumnSet().getColumnByClass(PersonIdColumn.class);
    }

    @Order(10)
    @ClassId("9d1422d2-b868-47d6-8c3a-46d9ed078d21")
    public class EditMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Edit");
      }

      @Override
      protected void execAction() {
        PersonForm form = new PersonForm();
        form.setPersonId(getPersonIdColumn().getSelectedValue());
        form.addFormListener(new PersonFormListener());
        // start the form using its modify handler
        form.startModify();
      }
    }

    @Order(2000)
    @ClassId("42c4d237-0fba-46c2-b62e-3ff531eeac32")
    public class NewMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("New");
      }

      @Override
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType>hashSet(
          TableMenuType.EmptySpace, TableMenuType.SingleSelection);
      }

      @Override
      protected void execAction() {
        PersonForm form = new PersonForm();
        form.addFormListener(new PersonFormListener());
        // start the form using its new handler
        form.startNew();
      }
    }

    private class PersonFormListener implements FormListener {

      @Override
      public void formChanged(FormEvent e) {
        // reload page to reflect new/changed data after saving any changes
        if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(1)
    @ClassId("34a96171-4a87-487d-928e-033a69246033")
    public class PersonIdColumn extends AbstractStringColumn {

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
    @ClassId("4c68ffe1-2477-4783-8b59-4c9194e9ed92")
    public class FirstNameColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("FirstName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(3)
    @ClassId("e6c03810-dfce-421a-a5a5-bc4d4f5d48ec")
    public class LastNameColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("LastName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(4)
    @ClassId("49e65d8d-bdf7-4426-8181-b42f969c8d8b")
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

    @Order(5)
    @ClassId("3ea1b9e9-da0c-4163-9f60-cad1c070b43a")
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

    @Order(6)
    @ClassId("17ab0315-020e-418d-848f-16e9d18d3c4a")
    public class PhoneColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Phone");
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

    @Order(7)
    @ClassId("171254af-92d2-48ba-8958-c7e93e57c3f4")
    public class MobileColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Mobile");
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

    @Order(8)
    @ClassId("cecc1103-2070-4523-902c-c75fb40c43ad")
    public class EmailColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Email");
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

    @Order(9)
    @ClassId("2e53e50e-5bd5-421e-8bca-fc50f27d790b")
    public class OrganizationColumn extends AbstractSmartColumn<String> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Organization");
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return OrganizationLookupCall.class;
      }
    }
  }
}
