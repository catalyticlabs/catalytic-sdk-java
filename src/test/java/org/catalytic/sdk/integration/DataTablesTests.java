package org.catalytic.sdk.integration;

public class DataTablesTests {
//    @Test
//    public void itShouldGetADataTable() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        DataTable dataTable = catalytic.dataTables().get("7e77254c-d2d6-4271-965a-98390aefa50a");
//        assertThat(dataTable.getId().toString()).isEqualTo("7e77254c-d2d6-4271-965a-98390aefa50a");
//    }
//
//    @Test
//    public void itShouldFindAllDataTables() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        DataTablesPage results = catalytic.dataTables().find();
//        assertThat(results.getDataTables()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }
//
////    @Test
////    public void itShouldFindDataTablesByText() throws Exception {
////        Client catalytic = new Client();
////        DataTablesPage results = catalytic.dataTables().find(
////                new Where().text().is("Testing PHP SDK")
////        );
////        assertThat(results.getDataTables()).isNotEmpty();
////        assertThat(results.getNextPageToken()).isNull();
////        assertThat(results.getCount()).isEqualTo(5);
////    }
//
//    @Test
//    public void itShouldDownloadDataTableToTempDir() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a");
//        assertThat(dataTable).isNotNull();
//    }
//
//    @Test
//    public void itShouldDownloadDataTableAsXlsxToTempDir() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", "xlsx");
//        assertThat(dataTable).isNotNull();
//    }
//
//    @Test
//    public void itShouldDownloadDataTableAsCsvToSpecificDirWithoutTrailingSlash() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", null, "/users/tomcaflisch/Downloads");
//        assertThat(dataTable).isNotNull();
//        assertThat(dataTable.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + dataTable.getName());
//    }
//
//    @Test
//    public void itShouldDownloadDataTableAsCsvToSpecificDirWithTrailingSlash() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", null, "/users/tomcaflisch/Downloads/");
//        assertThat(dataTable).isNotNull();
//        assertThat(dataTable.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + dataTable.getName());
//    }
//
//    @Test
//    public void itShouldUploadADataTable() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File file = new File("/Users/tomcaflisch/Downloads/mycsv.csv");
//        DataTable dataTable = catalytic.dataTables().upload(file, "Toms test table");
//        assertThat(dataTable).isNotNull();
//        assertThat(dataTable.getName()).isEqualTo("Toms test table");
//    }
//
//    @Test
//    public void itShouldReplaceADataTable() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        File file = new File("/Users/tomcaflisch/Downloads/mycsv2.csv");
//        DataTable dataTable = catalytic.dataTables().replace("7f0594eb-9e02-46f1-b897-a1fc0a9786ca", file);
//        assertThat(dataTable).isNotNull();
//    }
}
