package com.jinseong.soft.jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class JIRAClientTest {
    static final String URL = "http://192.168.30.93:9090/";
    static final String USERNAME = "kimjs";
    static final String PASSWORD = "4112665aa";

    @Test
    void testLoginAndGetJIRAIssues() {
        JiraRestClient client = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(URI.create(URL), USERNAME, PASSWORD);
        Promise<SearchResult> searchResultPromise =
                client.getSearchClient().searchJql("assignee in (currentUser()) ORDER BY createdDate DESC maxResults 100");
        assertThat(searchResultPromise).isNotNull();
    }
}
