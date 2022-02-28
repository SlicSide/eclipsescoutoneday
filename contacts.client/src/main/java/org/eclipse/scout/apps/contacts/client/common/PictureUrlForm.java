package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.client.common.PictureUrlForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contacts.client.common.PictureUrlForm.MainBox.OkButton;
import org.eclipse.scout.apps.contacts.client.common.PictureUrlForm.MainBox.UrlBox.InfoField;
import org.eclipse.scout.apps.contacts.client.common.PictureUrlForm.MainBox.UrlBox.UrlField;
import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.apps.contacts.shared.common.PictureUrlFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.text.TEXTS;

@ClassId("d6ac2a33-4676-42c6-8312-7196021e5913")
@FormData(value = PictureUrlFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PictureUrlForm extends AbstractForm {
  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PictureUrl");
  }

  public UrlField getUrlField() {
    return getFieldByClass(UrlField.class);
  }

  public InfoField getInfoField() {
    return getFieldByClass(InfoField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  public void startModify() {
    startInternalExclusive(new ModifyHandler());
  }

  @Order(1000)
  @ClassId("05342273-5042-4ea5-9ba0-8f0f02088234")
  public class MainBox extends AbstractGroupBox {
    @Order(10)
    @ClassId("fdcc7087-a693-45e8-a889-3725b0995558")
    public class UrlBox extends AbstractGroupBox {

      @Order(10)
      @ClassId("32b71aa6-1109-4b39-996f-f35a677faa06")
      public class UrlField extends AbstractStringField {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredStatusVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }
      }

      @Order(20)
      @ClassId("999c32e9-ca87-4b5c-a907-29d7a7400abf")
      public class InfoField extends AbstractHtmlField {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredStatusVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected boolean getConfiguredGridUseUiHeight() {
          return true;
        }

        @Override
        protected void execInitField() {
          setValue(HTML.fragment(HTML.icon(Icons.Info), HTML.bold(" " + TEXTS.get("PleaseNote") + ": "), TEXTS.get("SecurityUrlRestrictedMsg")).toHtml());
        }
      }
    }


    @Order(2000)
    @ClassId("949b99a3-fbe0-4ca1-88a3-7893018ba7a9")
    public class OkButton extends AbstractOkButton {

    }

    @Order(3000)
    @ClassId("9fb7c85d-68e9-4733-8a74-85d40fff3d6b")
    public class CancelButton extends AbstractCancelButton {

    }
  }

  public class ModifyHandler extends AbstractFormHandler {
  }
}

