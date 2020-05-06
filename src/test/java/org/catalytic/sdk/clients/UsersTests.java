package org.catalytic.sdk.clients;

import org.catalytic.sdk.Client;
import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.UserNotFoundException;
import org.catalytic.sdk.search.Where;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class UsersTests {

    @Test
    public void itShouldGetAUser() throws Exception {
        Client catalytic = new Client();
        User user = catalytic.users().get("tcaflisch@catalytic.com");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).containsMatch("tcaflisch@catalytic.com");
    }

    @Test(expected = UserNotFoundException.class)
    public void itShouldThrowExceptionIfUserDoesNotExist() throws Exception {
        Client catalytic = new Client();
        User user = catalytic.users().get("foo@bar.com");
    }

    @Test
    public void itShouldFindAllUsers() throws Exception {
        Client catalytic = new Client();
        UsersPage results = catalytic.users().find();
        assertThat(results.getUsers()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindUserByEmail() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        UsersPage results = catalytic.users().find(
                where.text().matches("tcaflisch@catalytic.com")
        );
        assertThat(results.getUsers()).hasSize(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(1);
    }
}
