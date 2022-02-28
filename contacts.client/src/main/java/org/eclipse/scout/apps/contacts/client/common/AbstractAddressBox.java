package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.client.common.AbstractAddressBox.LocationBox.CityField;
import org.eclipse.scout.apps.contacts.client.common.AbstractAddressBox.LocationBox.CountryField;
import org.eclipse.scout.apps.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@ClassId("d03c9495-530b-4bad-8b05-414ff7edce7a")
@FormData(value = AbstractAddressBoxData.class, // <1>
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public abstract class AbstractAddressBox extends AbstractGroupBox {

  @Override
  protected boolean getConfiguredBorderVisible() {
    return false;
  }

  @Override
  protected int getConfiguredGridH() { // <1>
    return 3;
  }

  @Override
  protected int getConfiguredGridW() { // <1>
    return 1;
  }

  @Override
  protected int getConfiguredGridColumnCount() { // <2>
    return 1;
  }
  //end::addressBox[]

  public StreetField getStreetField() {
    return getFieldByClass(StreetField.class);
  }

  public LocationBox getLocationBox() {
    return getFieldByClass(LocationBox.class);
  }

  public CityField getCityField() {
    return getFieldByClass(CityField.class);
  }

  public CountryField getCountryField() {
    return getFieldByClass(CountryField.class);
  }

  protected void validateAddressFields() {
    boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
    boolean hasCity = StringUtility.hasText(getCityField().getValue());

    getCityField().setMandatory(hasStreet);
    getCountryField().setMandatory(hasStreet || hasCity);
  }

  @Order(10)
  @ClassId("8bc1b9dd-6adf-4a48-8ed7-a7c0f0367987")
  public class StreetField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Street");
    }

    @Override
    protected void execChangedValue() {
      validateAddressFields();
    }
  }

  @Order(20)
  @ClassId("08c8e8f0-b6fc-4503-b9a0-ae03481ff5b6")
  public class LocationBox extends AbstractSequenceBox {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Location");
    }

    @Override
    protected boolean getConfiguredAutoCheckFromTo() {
      return false;
    }

    @Order(20)
    @ClassId("9df5daba-9b77-442d-aadd-18d5d1bd59e2")
    public class CityField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("City");
      }

      @Override
      protected byte getConfiguredLabelPosition() {
        return LABEL_POSITION_ON_FIELD;
      }

      @Override
      protected void execChangedValue() {
        validateAddressFields();
      }
    }

    @Order(20)
    @ClassId("ed60b3a6-c3aa-4cd3-b82e-341427f744a3")
    public class CountryField extends AbstractSmartField<String> {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Country");
      }

      @Override
      protected byte getConfiguredLabelPosition() {
        return LABEL_POSITION_ON_FIELD;
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return CountryLookupCall.class;
      }

      @Override
      protected void execChangedValue() {
        validateAddressFields();
      }
    }

  }

}
