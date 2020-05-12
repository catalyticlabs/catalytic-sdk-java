package org.catalytic.sdk.integration;

public class UsersTests {

//    @Test
//    public void itShouldGetAUser() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        User user = catalytic.users().get("tcaflisch@catalytic.com");
//        assertThat(user).isNotNull();
//        assertThat(user.getEmail()).containsMatch("tcaflisch@catalytic.com");
//    }
//
//    @Test(expected = UserNotFoundException.class)
//    public void itShouldThrowUserNotFoundExceptionIfUserDoesNotExist() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        catalytic.users().get("foo@bar.com");
//    }
//
//    @Test
//    public void itShouldFindAllUsers() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        UsersPage results = catalytic.users().find();
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }
//
//    @Test
//    public void itShouldFindUserByEmail() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        Where where = new Where();
//        UsersPage results = catalytic.users().find(
//                where.text().matches("tcaflisch@catalytic.com")
//        );
//        assertThat(results.getUsers()).hasSize(1);
//        assertThat(results.getNextPageToken()).isNull();
//        assertThat(results.getCount()).isEqualTo(1);
//    }
}
