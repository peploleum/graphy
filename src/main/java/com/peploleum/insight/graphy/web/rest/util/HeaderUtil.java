package com.peploleum.insight.graphy.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Created by nicmir on 10/01/2019.
 */
public class HeaderUtil {
    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String APPLICATION_NAME = "insightApp";

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-alert", message);
        headers.add("X-" + APPLICATION_NAME + "-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-error", "error." + errorKey);
        headers.add("X-" + APPLICATION_NAME + "-params", entityName);
        return headers;
    }

    public static HttpHeaders createDashboardCreationAlert(String dashboardTitle) {
        return createAlert("A new dashboard is created with title " + dashboardTitle, dashboardTitle);
    }
}
