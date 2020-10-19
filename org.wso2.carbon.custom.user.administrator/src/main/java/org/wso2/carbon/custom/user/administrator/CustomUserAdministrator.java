package org.wso2.carbon.custom.user.administrator;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.custom.user.administrator.internal.CustomUserAdministratorDataHolder;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;

public class CustomUserAdministrator {
  private static final Log log = LogFactory.getLog(CustomUserAdministrator.class);
  
  private String[] rolesList;
  
  public void addUser(String userName, Object credential, String[] rolesList, Map<String, String> hm, String profileName) throws UserStoreException {
    log.info("Starting the log");
    RealmService realmService = CustomUserAdministratorDataHolder.getInstance().getRealmService();
    int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
    UserStoreManager userStoreManager = realmService.getTenantUserRealm(tenantId).getUserStoreManager();
    userStoreManager.addUser(userName, credential, rolesList, hm, profileName);
  }
}
