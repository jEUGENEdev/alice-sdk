package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jeugenedev.alice.core.entity.request.interfaces.UserInterfaceContainer;

public class Meta {
    private String locale, timezone;
    @JsonProperty("client_id") private String clientId;
    private UserInterfaceContainer interfaces;

    public Meta() {}

    public Meta(String locale, String timezone, String clientId, UserInterfaceContainer interfaces) {
        this.locale = locale;
        this.timezone = timezone;
        this.clientId = clientId;
        this.interfaces = interfaces;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public UserInterfaceContainer getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(UserInterfaceContainer interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "locale='" + locale + '\'' +
                ", timezone='" + timezone + '\'' +
                ", clientId='" + clientId + '\'' +
                ", interfaces=" + interfaces +
                '}';
    }
}
