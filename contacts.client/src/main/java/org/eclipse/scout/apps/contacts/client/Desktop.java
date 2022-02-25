package org.eclipse.scout.apps.contacts.client;

import org.eclipse.scout.apps.contacts.client.contact.ContactOutline;
import org.eclipse.scout.apps.contacts.client.search.SearchOutline;
import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.NativeNotificationDefaults;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

import java.util.List;

/**
 * @author RicoHahn
 */
public class Desktop extends AbstractDesktop {

  public Desktop() {
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected String getConfiguredLogoId() {
    return Icons.AppLogo;
  }

  @Override
  protected NativeNotificationDefaults getConfiguredNativeNotificationDefaults() {
    return super.getConfiguredNativeNotificationDefaults().withIconId("application_logo.png");
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    return CollectionUtility.<Class<? extends IOutline>>arrayList(ContactOutline.class, SearchOutline.class);
  }

  @Override
  protected void execDefaultView() {
    selectFirstVisibleOutline();
  }

  protected void selectFirstVisibleOutline() {
    for (IOutline outline : getAvailableOutlines()) {
      if (outline.isEnabled() && outline.isVisible()) {
        setOutline(outline.getClass());
        return;
      }
    }
  }

  @Order(1)
  public class ContactOutlineViewButton extends AbstractOutlineViewButton {

    public ContactOutlineViewButton() {
      this(ContactOutline.class);
    }

    protected ContactOutlineViewButton(Class<? extends ContactOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F2;
    }
  }

  @Order(2)
  public class SearchOutlineViewButton extends AbstractOutlineViewButton {

    public SearchOutlineViewButton() {
      this(SearchOutline.class);
    }

    protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected DisplayStyle getConfiguredDisplayStyle() {
      return DisplayStyle.TAB;
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F3;
    }
  }

  @Order(10)
  public class QuickAccessMenu extends org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("QuickAccess");
    }

    @Override
    protected void execAction() {

    }
  }

  @Order(20)
  public class OptionsMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Options");
    }

    @Override
    protected String getConfiguredIconId() {
      return Icons.Gear;
    }

  }

  @Order(30)
  public class MyMenu extends AbstractMenu {
    @Override
    protected String getConfiguredIconId() {
      return Icons.PersonSolid;
    }

    @Override
    protected String getConfiguredCssClass() {
      return "profile-menu";
    }

  }

}
