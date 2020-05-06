package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.UserNotFoundException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.UsersApi;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersTests {

    UsersApi usersApi;

    @Before
    public void before() {
        usersApi = mock(UsersApi.class);
    }

    @Test
    public void getUser_itShouldGetAUser() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        when(usersApi.getUser("alice@example.com")).thenReturn(alice);

        Users usersClient = new Users(usersApi);
        User user = usersClient.get("alice@example.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).containsMatch("alice@example.com");
    }

    @Test(expected = UnauthorizedException.class)
    public void getUser_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(401, null));

        Users usersClient = new Users(usersApi);
        usersClient.get("alice@example.com");
    }

    @Test(expected = UserNotFoundException.class)
    public void getUser_itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(404, null));

        Users usersClient = new Users(usersApi);
        usersClient.get("alice@example.com");
    }

    @Test(expected = InternalErrorException.class)
    public void getUser_itShouldThrowInternalErrorException() throws Exception {
        when(usersApi.getUser("alice@example.com")).thenThrow(new ApiException(500, null));

        Users usersClient = new Users(usersApi);
        usersClient.get("alice@example.com");
    }

    @Test(expected = UnauthorizedException.class)
    public void findUsers_itShouldReturnUnauthorizedException() throws Exception {
        when(usersApi.findUsers(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Users usersClient = new Users(usersApi);
        usersClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findUsers_itShouldReturnInternalErrorException() throws Exception {
        when(usersApi.findUsers(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Users usersClient = new Users(usersApi);
        usersClient.find();
    }

    @Test
    public void findUsers_itShouldReturnAllUsers() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(List.of(alice));
        usersPage.setCount(List.of(alice).size());
        usersPage.setNextPageToken(null);
        when(usersApi.findUsers(null, null, null, null, null, null, null, null, null))
                .thenReturn(usersPage);

        Users usersClient = new Users(usersApi);
        UsersPage results = usersClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void findUsers_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(List.of(alice));
        usersPage.setCount(List.of(alice).size());
        usersPage.setNextPageToken(null);
        when(usersApi.findUsers(null, null, null, null, null, null, null, "25", null))
                .thenReturn(usersPage);

        Users usersClient = new Users(usersApi);
        UsersPage results = usersClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void findUsers_itShouldFindUserByEmail() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(List.of(alice));
        usersPage.setCount(List.of(alice).size());
        usersPage.setNextPageToken(null);
        when(usersApi.findUsers("alice@example.com", null, null, null, null, null, null, null, null))
                .thenReturn(usersPage);

        Users usersClient = new Users(usersApi);
        Where where = new Where();
        UsersPage results = usersClient.find(where.text().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    public void findUsers_itShouldFindUserByEmailAndPage() throws Exception {
        org.catalytic.sdk.generated.model.User alice = new org.catalytic.sdk.generated.model.User();
        alice.setEmail("alice@example.com");
        org.catalytic.sdk.generated.model.UsersPage usersPage = new org.catalytic.sdk.generated.model.UsersPage();
        usersPage.setUsers(List.of(alice));
        usersPage.setCount(List.of(alice).size());
        usersPage.setNextPageToken(null);
        when(usersApi.findUsers("alice@example.com", null, null, null, null, null, null, "25", null))
                .thenReturn(usersPage);

        Users usersClient = new Users(usersApi);
        Where where = new Where();
        UsersPage results = usersClient.find(where.text().is("alice@example.com"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getUsers().get(0).getEmail()).isEqualTo("alice@example.com");
    }
}
