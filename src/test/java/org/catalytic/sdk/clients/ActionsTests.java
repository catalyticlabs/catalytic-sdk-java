package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Action;
import org.catalytic.sdk.entities.ActionInvocation;
import org.catalytic.sdk.entities.ActionsPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.ActionNotFoundException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.ActionsApi;
import org.catalytic.sdk.search.ActionSearchClause;
import org.catalytic.sdk.search.ActionsWhere;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActionsTests {

    ActionsApi actionsApi;

    @Before
    public void before() {
        actionsApi = mock(ActionsApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void get_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Actions actionsClient = new Actions(nullString);
        actionsClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void get_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(401, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.get("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = ActionNotFoundException.class)
    public void get_itShouldThrowActionNotFoundExceptionIfActionDoesNotExist() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(404, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.get("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = InternalErrorException.class)
    public void get_itShouldThrowInternalErrorException() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(500, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.get("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test
    public void get_itShouldGetAAction() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setId(UUID.fromString("a66804b7-5044-4b0a-abe3-25a52df6f43c"));
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenReturn(alice);

        Actions actionsClient = new Actions("1234", actionsApi);
        Action action = actionsClient.get("a66804b7-5044-4b0a-abe3-25a52df6f43c");
        assertThat(action).isNotNull();
        assertThat(action.getId()).isEqualTo("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = UnauthorizedException.class)
    public void search_itShouldReturnUnauthorizedException() throws Exception {
        when(actionsApi.searchActions(null, null, new org.catalytic.sdk.generated.model.ActionSearchClause()))
                .thenThrow(new ApiException(401, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.search(null);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void search_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Actions actionsClient = new Actions(null);
        actionsClient.search(null);
    }

    @Test(expected = InternalErrorException.class)
    public void search_itShouldReturnInternalErrorException() throws Exception {
        when(actionsApi.searchActions(null, null, new org.catalytic.sdk.generated.model.ActionSearchClause()))
                .thenThrow(new ApiException(500, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.search(null);
    }

    @Test
    public void search_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setId(UUID.fromString("a66804b7-5044-4b0a-abe3-25a52df6f43c"));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");
        when(actionsApi.searchActions("abc123", null, new org.catalytic.sdk.generated.model.ActionSearchClause()))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(null, "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getId()).isEqualTo("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test
    public void search_itShouldFindActionById() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        actionSearchClause.setId(id);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause searchCriteria = ActionsWhere.id(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }

    @Test
    public void search_itShouldFindActionByName() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setName("My action");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression action = new org.catalytic.sdk.generated.model.StringSearchExpression();
        action.setIsEqualTo("My action");
        actionSearchClause.setName(action);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause searchCriteria = ActionsWhere.name("My action");

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getName()).isEqualTo("My action");
    }

    @Test
    public void search_itShouldFindActionByNameContains() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setName("My action");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression action = new org.catalytic.sdk.generated.model.StringSearchExpression();
        action.setContains("action");
        actionSearchClause.setName(action);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.nameContains("action");
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getName()).isEqualTo("action");
    }

    @Test
    public void search_itShouldFindActionBetweenNames() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setName("My action");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression action = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange actionRange = new org.catalytic.sdk.generated.model.StringRange();
        actionRange.setLowerBoundInclusive("Ma");
        actionRange.setUpperBoundInclusive("Mz");
        action.setBetween(actionRange);
        actionSearchClause.setName(action);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.nameBetween("Ma", "Mz");
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getName()).isEqualTo("My action");
    }

    @Test
    public void search_itShouldFindActionByDescription() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setDescription("My action description");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setIsEqualTo("My action description");
        actionSearchClause.setDescription(description);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause searchCriteria = ActionsWhere.description("My action description");

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getDescription()).isEqualTo("My action description");
    }

    @Test
    public void search_itShouldFindActionByDescriptionContains() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setDescription("My action description");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setContains("action");
        actionSearchClause.setDescription(description);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.descriptionContains("action");
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getDescription()).isEqualTo("My action description");
    }

    @Test
    public void search_itShouldFindActionBetweenDescriptions() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setDescription("My action description");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange descriptionRange = new org.catalytic.sdk.generated.model.StringRange();
        descriptionRange.setLowerBoundInclusive("aa");
        descriptionRange.setUpperBoundInclusive("az");
        description.setBetween(descriptionRange);
        actionSearchClause.setDescription(description);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.descriptionBetween("aa", "az");
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getDescription()).isEqualTo("My action description");
    }

    @Test
    public void search_itShouldFindActionByOriginalActionId() throws Exception {
        org.catalytic.sdk.generated.model.Action internalAction = new org.catalytic.sdk.generated.model.Action();
        internalAction.setOriginalActionId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(internalAction));
        actionsPage.setCount(Arrays.asList(internalAction).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression originalActionId = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        originalActionId.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        actionSearchClause.setOriginalActionId(originalActionId);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause searchCriteria = ActionsWhere.originalActionId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getOriginalActionId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }


    @Test
    public void search_itShouldFindActionByCreatedByEmail() throws Exception {
        org.catalytic.sdk.generated.model.Action internalAction = new org.catalytic.sdk.generated.model.Action();
        internalAction.setCreatedByEmail("bob@aol.com");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(internalAction));
        actionsPage.setCount(Arrays.asList(internalAction).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression email = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        email.setIsEqualTo("bob@aol.com");
        actionSearchClause.setCreatedByEmail(email);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause searchCriteria = ActionsWhere.createdByEmail("bob@aol.com");

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getCreatedByEmail()).isEqualTo("bob@aol.com");
    }

    @Test
    public void search_itShouldFindActionByCreatedDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        actionSearchClause.setCreatedDate(createdDate);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.createdDate("2020-01-01T00:00:00.000-06:00");
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindActionByCreatedDate() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        actionSearchClause.setCreatedDate(createdDate);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.createdDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindActionsByCreatedDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        actionSearchClause.setCreatedDate(createdDate);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.createdDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindActionsByCreatedDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(alice));
        actionsPage.setCount(Arrays.asList(alice).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        actionSearchClause.setCreatedDate(createdDate);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionSearchClause searchCriteria = ActionsWhere.createdDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindActionByNameAndCreatedByEmail() throws Exception {
        org.catalytic.sdk.generated.model.Action internalAction = new org.catalytic.sdk.generated.model.Action();
        internalAction.setName("My action");
        internalAction.setCreatedByEmail("bob@aol.com");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        actionsPage.setActions(Arrays.asList(internalAction));
        actionsPage.setCount(Arrays.asList(internalAction).size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();

        List<org.catalytic.sdk.generated.model.ActionSearchClause> and = new ArrayList<>();
        org.catalytic.sdk.generated.model.ActionSearchClause nameClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.ActionSearchClause createdByEmailClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("My action");
        org.catalytic.sdk.generated.model.ExactStringSearchExpression createdByEmail = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        createdByEmail.setIsEqualTo("bob@aol.com");
        nameClause.setName(name);
        createdByEmailClause.setCreatedByEmail(createdByEmail);
        and.add(nameClause);
        and.add(createdByEmailClause);

        actionSearchClause.setAnd(and);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause nameSearchClause = ActionsWhere.name("My action");
        ActionSearchClause createdByEmailSearchClause = ActionsWhere.createdByEmail("bob@aol.com");
        ActionSearchClause searchCriteria = ActionsWhere.and(nameSearchClause, createdByEmailSearchClause);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getName()).isEqualTo("My action");
        assertThat(results.getActions().get(0).getCreatedByEmail()).isEqualTo("bob@aol.com");
    }

    @Test
    public void search_itShouldFindActionByCreatedByEmailOrCreatedByEmail() throws Exception {
        org.catalytic.sdk.generated.model.Action alicesAction = new org.catalytic.sdk.generated.model.Action();
        org.catalytic.sdk.generated.model.Action bobsAction = new org.catalytic.sdk.generated.model.Action();
        alicesAction.setCreatedByEmail("alice@hotmail.com");
        bobsAction.setCreatedByEmail("bob@aol.com");
        org.catalytic.sdk.generated.model.ActionsPage actionsPage = new org.catalytic.sdk.generated.model.ActionsPage();
        List<org.catalytic.sdk.generated.model.Action> actions = Arrays.asList(alicesAction, bobsAction);
        actionsPage.setActions(actions);
        actionsPage.setCount(actions.size());
        actionsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();

        List<org.catalytic.sdk.generated.model.ActionSearchClause> or = new ArrayList<>();
        org.catalytic.sdk.generated.model.ActionSearchClause createdByEmailAliceClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.ActionSearchClause createdByEmailMarvinClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression createdByEmailAlice = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression createdByEmailMarvin = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        createdByEmailAlice.setIsEqualTo("alice@hotmail.com");
        createdByEmailMarvin.setIsEqualTo("bob@aol.com");
        createdByEmailAliceClause.setCreatedByEmail(createdByEmailAlice);
        createdByEmailMarvinClause.setCreatedByEmail(createdByEmailMarvin);
        or.add(createdByEmailAliceClause);
        or.add(createdByEmailMarvinClause);

        actionSearchClause.setOr(or);
        when(actionsApi.searchActions(null, null, actionSearchClause))
                .thenReturn(actionsPage);

        ActionSearchClause aliceEmail = ActionsWhere.createdByEmail("alice@hotemail.com");
        ActionSearchClause marvinEmail = ActionsWhere.createdByEmail("bob@aol.com");
        ActionSearchClause searchCriteria = ActionsWhere.or(aliceEmail, marvinEmail);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionsPage results = actionsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getActions().get(0).getId()).isEqualTo("alice@hotmail.com");
        assertThat(results.getActions().get(1).getId()).isEqualTo("bob@aol.com");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void invoke_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Actions actionsClient = new Actions(nullString);
        actionsClient.invoke("1234", null);
    }

    @Test(expected = UnauthorizedException.class)
    public void invoke_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(401, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.invoke("a66804b7-5044-4b0a-abe3-25a52df6f43c", null);
    }

    @Test(expected = ActionNotFoundException.class)
    public void invoke_itShouldThrowActionNotFoundExceptionIfActionDoesNotExist() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(404, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.invoke("a66804b7-5044-4b0a-abe3-25a52df6f43c", null);
    }

    @Test(expected = InternalErrorException.class)
    public void invoke_itShouldThrowInternalErrorException() throws Exception {
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(500, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.invoke("a66804b7-5044-4b0a-abe3-25a52df6f43c", null);
    }

    @Test
    public void invoke_itShouldGetAAction() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setId(UUID.fromString("a66804b7-5044-4b0a-abe3-25a52df6f43c"));
        when(actionsApi.getAction("a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenReturn(alice);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionInvocation actionInvocation = actionsClient.invoke("a66804b7-5044-4b0a-abe3-25a52df6f43c", null);
        assertThat(actionInvocation).isNotNull();
        assertThat(actionInvocation.getId()).isEqualTo("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getActionInvocation_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Actions actionsClient = new Actions(nullString);
        actionsClient.getActionInvocation("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getActionInvocation_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(actionsApi.getActionInvocation("-", "a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(401, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.getActionInvocation("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = ActionNotFoundException.class)
    public void getActionInvocation_itShouldThrowActionNotFoundExceptionIfActionDoesNotExist() throws Exception {
        when(actionsApi.getActionInvocation("-", "a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(404, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.getActionInvocation("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test(expected = InternalErrorException.class)
    public void getActionInvocation_itShouldThrowInternalErrorException() throws Exception {
        when(actionsApi.getActionInvocation("-", "a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenThrow(new ApiException(500, null));

        Actions actionsClient = new Actions("1234", actionsApi);
        actionsClient.getActionInvocation("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }

    @Test
    public void getActionInvocation_itShouldGetAAction() throws Exception {
        org.catalytic.sdk.generated.model.Action alice = new org.catalytic.sdk.generated.model.Action();
        alice.setId(UUID.fromString("a66804b7-5044-4b0a-abe3-25a52df6f43c"));
        when(actionsApi.getActionInvocation("-", "a66804b7-5044-4b0a-abe3-25a52df6f43c")).thenReturn(alice);

        Actions actionsClient = new Actions("1234", actionsApi);
        ActionInvocation actionInvocation = actionsClient.getActionInvocation("a66804b7-5044-4b0a-abe3-25a52df6f43c");
        assertThat(actionInvocation).isNotNull();
        assertThat(actionInvocation.getId()).isEqualTo("a66804b7-5044-4b0a-abe3-25a52df6f43c");
    }
}
