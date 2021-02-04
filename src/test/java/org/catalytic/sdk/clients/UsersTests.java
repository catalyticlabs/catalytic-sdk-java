package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.UserNotFoundException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.UsersApi;
import org.catalytic.sdk.search.UserSearchClause;
import org.catalytic.sdk.search.UsersWhere;
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

public class UsersTests {

    UsersApi usersApi;

    @Before
    public void before() {
        usersApi = mock(UsersApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void get_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Users usersClient = new Users(nullString);
        usersClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void get_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(401, null));

        Users usersClient = new Users("1234", usersApi);
        usersClient.get("alice@example.com");
    }

    @Test(expected = UserNotFoundException.class)
    public void get_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(404, null));

        Users usersClient = new Users("1234", usersApi);
        usersClient.get("alice@example.com");
    }

    @Test(expected = InternalErrorException.class)
    public void get_itShouldThrowInternalErrorException() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(500, null));

        Users usersClient = new Users("1234", usersApi);
        usersClient.get("alice@example.com");
    }

    @Test
    public void get_itShouldGetAUser() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        when(usersApi.getUser("alice@example.com")).thenReturn(alice);

        Users usersClient = new Users("1234", usersApi);
        User user = usersClient.get("alice@example.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).containsMatch("alice@example.com");
    }

    @Test(expected = UnauthorizedException.class)
    public void search_itShouldReturnUnauthorizedException() throws Exception {
        when(usersApi.searchUsers(null, null, new org.catalytic.sdk.generated.model.UserSearchClause()))
                .thenThrow(new ApiException(401, null));

        Users usersClient = new Users("1234", usersApi);
        usersClient.search(null);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void search_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Users usersClient = new Users(null);
        usersClient.search(null);
    }

    @Test(expected = InternalErrorException.class)
    public void search_itShouldReturnInternalErrorException() throws Exception {
        when(usersApi.searchUsers(null, null, new org.catalytic.sdk.generated.model.UserSearchClause()))
                .thenThrow(new ApiException(500, null));

        Users usersClient = new Users("1234", usersApi);
        usersClient.search(null);
    }

    @Test
    public void list_itShouldReturnAllUsers() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        org.catalytic.sdk.generated.model.User bob = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        bob.setEmail("bob@example.com");
        org.catalytic.sdk.generated.model.UsersPage firstPage = new org.catalytic.sdk.generated.model.UsersPage();
        org.catalytic.sdk.generated.model.UsersPage secondPage = new org.catalytic.sdk.generated.model.UsersPage();
        firstPage.setUsers(Arrays.asList(alice));
        firstPage.setCount(Arrays.asList(alice).size());
        firstPage.setNextPageToken("123");
        secondPage.setUsers(Arrays.asList(bob));
        secondPage.setCount(Arrays.asList(bob).size());
        secondPage.setNextPageToken("123");
        secondPage.setNextPageToken("");
        when(usersApi.searchUsers(null, null, new org.catalytic.sdk.generated.model.UserSearchClause()))
                .thenReturn(firstPage);
        when(usersApi.searchUsers("123", null, new org.catalytic.sdk.generated.model.UserSearchClause()))
                .thenReturn(secondPage);

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.list();

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
        assertThat(results.getUsers().get(1).getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    public void searchUsers_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");
        when(usersApi.searchUsers("abc123", null, new org.catalytic.sdk.generated.model.UserSearchClause()))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(null, "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchUsers_itShouldFindUserById() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        userSearchClause.setId(id);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        UserSearchClause searchCriteria = UsersWhere.id(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }

    @Test
    public void searchUsers_itShouldFindUserByEmail() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression email = new org.catalytic.sdk.generated.model.StringSearchExpression();
        email.setIsEqualTo("alice@example.com");
        userSearchClause.setEmail(email);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        UserSearchClause searchCriteria = UsersWhere.email("alice@example.com");

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchUsers_itShouldFindUserByPartialEmail() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression email = new org.catalytic.sdk.generated.model.StringSearchExpression();
        email.setContains("alice");
        userSearchClause.setEmail(email);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.emailContains("alice");
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchUsers_itShouldFindUserBetweenEmails() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression email = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange emailRange = new org.catalytic.sdk.generated.model.StringRange();
        emailRange.setLowerBoundInclusive("alic");
        emailRange.setUpperBoundInclusive("aliz");
        email.setBetween(emailRange);
        userSearchClause.setEmail(email);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.emailBetween("alic", "aliz");
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchUsers_itShouldFindUserByFullName() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setFullName("alice example");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        fullName.setIsEqualTo("alice example");
        userSearchClause.setFullName(fullName);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        UserSearchClause searchCriteria = UsersWhere.fullName("alice example");

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getFullName()).isEqualTo("alice example");
    }

    @Test
    public void searchUsers_itShouldFindUserByPartialFullName() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setFullName("alice example");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        fullName.setContains("alice");
        userSearchClause.setFullName(fullName);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.fullNameContains("alice");
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getFullName()).isEqualTo("alice example");
    }

    @Test
    public void searchUsers_itShouldFindUserBetweenFullNames() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setFullName("alice example");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange fullNameRange = new org.catalytic.sdk.generated.model.StringRange();
        fullNameRange.setLowerBoundInclusive("alic");
        fullNameRange.setUpperBoundInclusive("aliz");
        fullName.setBetween(fullNameRange);
        userSearchClause.setFullName(fullName);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.fullNameBetween("alic", "aliz");
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getFullName()).isEqualTo("alice example");
    }

    @Test
    public void searchUsers_itShouldFindUserByIsTeamAdmin() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setIsTeamAdmin(true);
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.BoolSearchExpression isTeamAdmin = new org.catalytic.sdk.generated.model.BoolSearchExpression();
        isTeamAdmin.isEqualTo(true);
        userSearchClause.setIsTeamAdmin(isTeamAdmin);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.isTeamAdmin(true);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getIsTeamAdmin()).isEqualTo(true);
    }

    @Test
    public void searchUsers_itShouldFindUserByIsDeactivated() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setIsDeactivated(true);
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.BoolSearchExpression isDeactivated = new org.catalytic.sdk.generated.model.BoolSearchExpression();
        isDeactivated.isEqualTo(true);
        userSearchClause.setIsDeactivated(isDeactivated);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.isDeactivated(true);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getIsDeactivated()).isEqualTo(true);
    }

    @Test
    public void searchUsers_itShouldFindUserByIsLockedOut() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setIsLockedOut(true);
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.BoolSearchExpression isLockedOut = new org.catalytic.sdk.generated.model.BoolSearchExpression();
        isLockedOut.isEqualTo(true);
        userSearchClause.setIsLockedOut(isLockedOut);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.isLockedOut(true);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getIsLockedOut()).isEqualTo(true);
    }

    @Test
    public void searchUsers_itShouldFindUserByCreatedDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        userSearchClause.setCreatedDate(createdDate);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.createdDate("2020-01-01T00:00:00.000-06:00");
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchUsers_itShouldFindUserByCreatedDate() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        userSearchClause.setCreatedDate(createdDate);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.createdDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchUsers_itShouldFindUsersByCreatedDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        userSearchClause.setCreatedDate(createdDate);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.createdDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchUsers_itShouldFindUsersByCreatedDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        userSearchClause.setCreatedDate(createdDate);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        Users usersClient = new Users("1234", usersApi);
        UserSearchClause searchCriteria = UsersWhere.createdDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchUsers_itShouldFindUserByEmailAndFullName() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(Arrays.asList(alice));
        usersPage.setCount(Arrays.asList(alice).size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();

        List<org.catalytic.sdk.generated.model.UserSearchClause> and = new ArrayList<>();
        org.catalytic.sdk.generated.model.UserSearchClause emailClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.UserSearchClause fullNameClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression email = new org.catalytic.sdk.generated.model.StringSearchExpression();
        email.setIsEqualTo("alice@example.com");
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        fullName.setIsEqualTo("alice example");
        emailClause.setEmail(email);
        fullNameClause.setFullName(fullName);
        and.add(emailClause);
        and.add(fullNameClause);

        userSearchClause.setAnd(and);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        UserSearchClause emailSearchClause = UsersWhere.email("alice@example.com");
        UserSearchClause fullNameSearchClause = UsersWhere.fullName("alice example");
        UserSearchClause searchCriteria = UsersWhere.and(emailSearchClause, fullNameSearchClause);

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchUsers_itShouldFindUserByEmailOrEmail() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        org.catalytic.sdk.generated.model.User marvin = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        marvin.setEmail("marvin@aol.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        List<org.catalytic.sdk.generated.model.User> users = Arrays.asList(alice, marvin);
        usersPage.setUsers(users);
        usersPage.setCount(users.size());
        usersPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.UserSearchClause userSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();

        List<org.catalytic.sdk.generated.model.UserSearchClause> or = new ArrayList<>();
        org.catalytic.sdk.generated.model.UserSearchClause emailAliceClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.UserSearchClause emailMarvinClause = new org.catalytic.sdk.generated.model.UserSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression emailAlice = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringSearchExpression emailMarvin = new org.catalytic.sdk.generated.model.StringSearchExpression();
        emailAlice.setIsEqualTo("alice@example.com");
        emailMarvin.setIsEqualTo("marvin@aol.com");
        emailAliceClause.setEmail(emailAlice);
        emailMarvinClause.setEmail(emailMarvin);
        or.add(emailAliceClause);
        or.add(emailMarvinClause);

        userSearchClause.setOr(or);
        when(usersApi.searchUsers(null, null, userSearchClause))
                .thenReturn(usersPage);

        UserSearchClause aliceEmail = UsersWhere.email("alice@example.com");
        UserSearchClause marvinEmail = UsersWhere.email("marvin@aol.com");
        UserSearchClause searchCriteria = UsersWhere.or(aliceEmail, marvinEmail);

        Users usersClient = new Users("1234", usersApi);
        UsersPage results = usersClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
        assertThat(results.getUsers().get(1).getEmail()).isEqualTo("marvin@aol.com");
    }
}
