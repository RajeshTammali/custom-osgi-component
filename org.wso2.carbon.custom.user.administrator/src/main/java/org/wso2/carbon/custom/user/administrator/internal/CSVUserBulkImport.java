package org.wso2.carbon.custom.user.administrator.internal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.custom.user.administrator.CustomUserAdministrator;
import org.wso2.carbon.user.core.UserStoreException;

public class CSVUserBulkImport {
  private static final Log log = LogFactory.getLog(CSVUserBulkImport.class);
  String userName = "";
  Object credential = "";
  String[] roleList = new String[] { "" };
  String email = "";
  String country = "";
  String mobile = "";
  String givenName = "";
  Map<String, String> claims = new HashMap<>();
  
  public void csvReader() {
    try {
      log.info("************************************************");
      String line = "";
      String splitBy = ",";
      int headerRow = 0;
      CustomUserAdministrator administrator = new CustomUserAdministrator();
      BufferedReader br = new BufferedReader(new FileReader("C:\\Home\\Test.csv")); /*CSV file location*/
      while ((line = br.readLine()) != null) {
        if (headerRow == 0) {
          headerRow++;
          continue;
        } 
        String[] employee = line.split(splitBy);
        userName = employee[0];
        if (userName == null || userName.isEmpty())
          log.info("Invalid User Name"); 
        credential = employee[1];
        if (credential == null || ((String)credential).isEmpty())
          log.info("Invalid Password"); 
        givenName = employee[2];
        if (givenName == null || givenName.isEmpty())
          log.info("Invalid GivenName"); 
        email = employee[4];
        if (email == null || email.isEmpty())
          log.info("Invalid email"); 
        country = employee[5];
        if (country != null)
          country.isEmpty(); 
        mobile = employee[3];
        if (mobile == null || mobile.isEmpty())
          log.info("Invalid mobile"); 
        claims.put("http://wso2.org/claims/givenname", givenName);
        claims.put("http://wso2.org/claims/emailaddress", email);
        claims.put("http://wso2.org/claims/country", country);
        claims.put("http://wso2.org/claims/mobile", mobile);
        try {
          administrator.addUser(userName, credential, roleList, claims, null);
        } catch (UserStoreException exception) {
          log.error(exception);
        } 
        log.info("Custom component is triggered.");
      } 
    } catch (Exception exception) {
      log.debug(exception);
    } 
  }
}
