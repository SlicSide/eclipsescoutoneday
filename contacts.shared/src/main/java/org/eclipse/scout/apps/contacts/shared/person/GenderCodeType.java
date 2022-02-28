package org.eclipse.scout.apps.contacts.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

@ClassId("9dbfe2f5-2f97-4005-bbf9-40031fffb7b6")
public class GenderCodeType extends AbstractCodeType<String, String> {
  public static final String ID = "Gender";
  private static final long serialVersionUID = 1L;

  @Override
  public String getId() {
    return ID;
  }


  @Order(1000)
  @ClassId("8893e1e4-7b6c-46c2-8c84-42c914ec29d5")
  public static class MaleCode extends AbstractCode<String> {

    public static final String ID = "M";
    private static final long serialVersionUID = 1L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Male");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000)
  @ClassId("23e1540e-2914-401f-9f42-e409ac2fb605")
  public static class FemaleCode extends AbstractCode<String> {

    public static final String ID = "F";
    private static final long serialVersionUID = 1L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Female");
    }

    @Override
    public String getId() {
      return ID;
    }
  }
}

