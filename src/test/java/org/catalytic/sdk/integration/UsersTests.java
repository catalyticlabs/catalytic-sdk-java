package org.catalytic.sdk.integration;

public class UsersTests {

//    @Test
//    public void itShouldGetAUser() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        User user = catalytic.users().get("tcaflisch@catalytic.com");
////        User user = catalytic.users().get("f62d0251-cb1b-4ad2-aa51-deb8461be7db");
////        User user = catalytic.users().search().getUsers().get(0);
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
//    public void itShouldListAllUsers() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        UsersPage results = catalytic.users().list();
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldSearchAllUsers() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        UsersPage results = catalytic.users().search(null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        System.out.println("Page 1");
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//
//        results = catalytic.users().search(null, results.getNextPageToken());
//
//        System.out.println("Page 2");
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
////
//    @Test
//    public void itShouldsearchUserById() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.email("tcaflisch@catalytic.com");
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByEmail() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        StringSearchExpression email = new StringSearchExpression();
//        email.setIsEqualTo("tcaflisch@catalytic.com");
//        UsersPage results = catalytic.users().search(null, null, null, email, null, null, null, null, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
////
//    @Test
//    public void itShouldsearchUserByFullName() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        StringSearchExpression fullName = new StringSearchExpression();
//        fullName.setIsEqualTo("Tom Caflisch");
//        UsersPage results = catalytic.users().search(null, null, null, null, fullName, null, null, null, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByIsTeamAdmin() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isTeamAdmin = new BooleanSearchExpression();
//        isTeamAdmin.setIsEqualTo(true);
//        UsersPage results = catalytic.users().search(null, null, null, null, null, isTeamAdmin, null, null, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByIsDeactivated() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isDeactivated = new BooleanSearchExpression();
//        isDeactivated.setIsEqualTo(true);
//        UsersPage results = catalytic.users().search(null, null, null, null, null, null, isDeactivated, null, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByIsLockedOut() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isLockedOut = new BooleanSearchExpression();
//        isLockedOut.setIsEqualTo(false);
//        UsersPage results = catalytic.users().search(null, null, null, null, null, null, null, isLockedOut, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByEmailAndPage() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
////        StringSearchExpression email = new StringSearchExpression();
////        email.setIsEqualTo("tcaflisch@catalytic.com");
//        UsersPage results = catalytic.users().search(null, null, null, null, null, null, null, null, null, null, 1);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println("first page of users");
//            System.out.println(user.toString());
//        }
//
//        // Get second page
//        results = catalytic.users().search(null, null, null, null, null, null, null, null, null, results.getNextPageToken(), 1);
//
//        for (User user : results.getUsers()) {
//            System.out.println("second page of users");
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserWithAndClause() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
////        StringSearchExpression email = new StringSearchExpression();
////        email.setIsEqualTo("tcaflisch@catalytic.com");
//        UsersPage results = catalytic.users().search(null, null, null, null, null, null, null, null, null, null, 1);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println("first page of users");
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchUserByIsTeamAdmin() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isTeamAdmin = new BooleanSearchExpression();
//        isTeamAdmin.setIsEqualTo(true);
//        UsersPage results = catalytic.users().search(null, null, null, isTeamAdmin, null, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }
//
//    @Test
//    public void itShouldsearchUserByIsDeactivated() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isDeactivated = new BooleanSearchExpression();
//        isDeactivated.setIsEqualTo(true);
//        UsersPage results = catalytic.users().search(null, null, null, null, isDeactivated, null, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }
//
//    @Test
//    public void itShouldsearchUserByIsLockedOut() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        BooleanSearchExpression isLockedOut = new BooleanSearchExpression();
//        isLockedOut.setIsEqualTo(true);
//        UsersPage results = catalytic.users().search(null, null, null, null, null, isLockedOut, null);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }

//    @Test
//    public void itShouldsearchUserByCreatedDate() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        DateTimeSearchExpression createdDate = new DateTimeSearchExpression();
//        createdDate.setIsEqualTo(OffsetDateTime.now());
//        UsersPage results = catalytic.users().search(null, null, null, null, null, null, createdDate);
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }

//    @Test
//    public void itShouldsearchUserByIdOrEmail() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.or(
//                UsersWhere.id("f62d0251-cb1b-4ad2-aa51-deb8461be7db"),
//                UsersWhere.email("tcaflisch@catalytic.com")
//        );
//
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchUserByEmailBetween() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.emailBetween("a", "z");
////        UserSearchClause searchCriteria = UsersWhere.emailContains("tcaflisch");
//
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
//
//    @Test
//    public void itShouldsearchAllUsers() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UsersPage results = catalytic.users().search();
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchForUserByEmailBetween() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.emailBetween("tcaflisch", "tcafliscz");
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchForUserByIsTeamAdmin() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.isTeamAdmin(true);
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchForUserByCreatedDateBetween() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.createdDateBetween(
//                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
//                OffsetDateTime.of(2020, 02, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
//        );
//
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchForUserByEmail() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.email("tcaflisch@catalytic.com");
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }

//    @Test
//    public void itShouldsearchUsersViaOrClause() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//
//        UserSearchClause searchCriteria = UsersWhere.or(
//                UsersWhere.email("andy@catalytic.com"),
//                UsersWhere.email("james@catalytic.com")
//        );
//
//        UsersPage results = catalytic.users().search(searchCriteria);
//
//        assertThat(results.getUsers()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//
//        for (User user : results.getUsers()) {
//            System.out.println(user.toString());
//        }
//    }
}
