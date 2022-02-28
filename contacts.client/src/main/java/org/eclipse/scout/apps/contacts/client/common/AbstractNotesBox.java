package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@ClassId("ea38d806-a38b-4511-bbda-ecb2fe694e70")
@FormData(value = AbstractNotesBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractNotesBox extends AbstractGroupBox {

  public NotesField getNotesField() {
    return getFieldByClass(NotesField.class);
  }

  @Override
  protected String getConfiguredLabel() {
    return TEXTS.get("Notes");
  }

  @Order(10)
  @ClassId("ce791f14-fca6-4f11-8476-89cbf905eb2e")
  public class NotesField extends AbstractStringField {

    @Override
    protected int getConfiguredGridH() {
      return 4;
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredMultilineText() {
      return true;
    }
  }
}

