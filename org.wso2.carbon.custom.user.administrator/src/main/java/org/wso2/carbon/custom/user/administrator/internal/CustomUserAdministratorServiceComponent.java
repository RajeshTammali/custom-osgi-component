package org.wso2.carbon.custom.user.administrator.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.user.core.service.RealmService;

@Component(name = "org.wso2.carbon.identity.custom.user.add.component", immediate = true)
public class CustomUserAdministratorServiceComponent {
  private static final Log log = LogFactory.getLog(CustomUserAdministratorServiceComponent.class);
  
  @Activate
  protected void activate(ComponentContext context) {
    try {
      log.info("************************************************");
      CSVUserBulkImport userImport = new CSVUserBulkImport();
      userImport.csvReader();
      if (log.isDebugEnabled())
        log.debug("Custom component is activated."); 
    } catch (Throwable e) {
      log.error("Error activating the custom component", e);
    } 
  }
  
  @Deactivate
  protected void deactivate(ComponentContext cxt) {
    if (log.isDebugEnabled())
      log.debug("Custom component is deactivated."); 
  }
  
  @Reference(name = "realm.service", service = RealmService.class, cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC, unbind = "unsetRealmService")
  protected void setRealmService(RealmService realmService) {
    if (log.isDebugEnabled())
      log.debug("Setting the Realm Service"); 
    CustomUserAdministratorDataHolder.getInstance().setRealmService(realmService);
  }
  
  protected void unsetRealmService(RealmService realmService) {
    if (log.isDebugEnabled())
      log.debug("Unset the Realm Service."); 
    CustomUserAdministratorDataHolder.getInstance().setRealmService(null);
  }
}
