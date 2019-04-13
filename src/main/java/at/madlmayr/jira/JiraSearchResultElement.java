package at.madlmayr.jira;

import at.madlmayr.Account;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@DynamoDBTable(tableName = Account.TABLE_NAME)
public class JiraSearchResultElement implements Serializable {

    private String companyToolTimestamp;
    @JsonProperty("self")
    private String self;
    @JsonProperty("key")
    private String key;
    @JsonProperty("name")
    private String name;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("avatarUrls")
    private AvatarUrls avatarUrls;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("locale")
    private String locale;

    @DynamoDBHashKey(attributeName = Account.COLUMN_COMPANY_TOOL)
    public String getCompanyToolTimestamp() {
        return companyToolTimestamp;
    }

    public void setCompanyToolTimestamp(String companyToolTimestamp) {
        this.companyToolTimestamp = companyToolTimestamp;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @DynamoDBRangeKey(attributeName = Account.COLUMN_ID)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public AvatarUrls getAvatarUrls() {
        return avatarUrls;
    }


    public void setAvatarUrls(AvatarUrls avatarUrls) {
        this.avatarUrls = avatarUrls;
    }


    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }


    public String getTimeZone() {
        return timeZone;
    }


    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }


    public String getLocale() {
        return locale;
    }


    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean searchSearchString(final String searchString) {

        String lowerCaseSearchString = searchString.toLowerCase();

        // if search string is in one of the names
        if (getKey().toLowerCase().startsWith(lowerCaseSearchString) || getName().toLowerCase().startsWith(lowerCaseSearchString) || getDisplayName().toLowerCase().startsWith(lowerCaseSearchString) || getSelf().toLowerCase().startsWith(lowerCaseSearchString)) {
            return true;
        }

        // if search string is in one of the email components
        String[] emailComponents = getEmailAddress().split("\\.|@");

        for (String component : emailComponents) {
            if (component.toLowerCase().startsWith(lowerCaseSearchString)) {
                return true;
            }
        }

        return false;
    }

    @DynamoDBDocument
    public static class AvatarUrls {

        @JsonProperty("48x48")
        private String avatar48x48;

        @JsonProperty("24x24")
        private String avata24x24;

        @JsonProperty("16x16")
        private String avatar16x16;

        @JsonProperty("32x32")
        private String avatar32x32;

        public AvatarUrls() {
        }

        public String getAvatar48x48() {
            return avatar48x48;
        }

        public void setAvatar48x48(String avatar48x48) {
            this.avatar48x48 = avatar48x48;
        }

        public String getAvata24x24() {
            return avata24x24;
        }

        public void setAvata24x24(String avata24x24) {
            this.avata24x24 = avata24x24;
        }

        public String getAvatar16x16() {
            return avatar16x16;
        }

        public void setAvatar16x16(String avatar16x16) {
            this.avatar16x16 = avatar16x16;
        }

        public String getAvatar32x32() {
            return avatar32x32;
        }

        public void setAvatar32x32(String avatar32x32) {
            this.avatar32x32 = avatar32x32;
        }
    }


}
