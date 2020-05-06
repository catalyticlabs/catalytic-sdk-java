package org.catalytic.sdk.clients;

import org.catalytic.sdk.Client;
import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.entities.FilesPage;
import org.catalytic.sdk.search.Where;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class FilesTests {

    @Test
    public void itShouldGetAFile() throws Exception {
        Client catalytic = new Client();
        File file = catalytic.files().get("924cd388-addb-42f7-913e-24c9beb17635");
        assertThat(file).isNotNull();
        assertThat(file.getId()).isEqualTo("924cd388-addb-42f7-913e-24c9beb17635");
    }

    @Test
    public void itShouldFindAllFiles() throws Exception {
        Client catalytic = new Client();
        FilesPage results = catalytic.files().find();
        assertThat(results.getFiles()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindFilesByText() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        FilesPage results = catalytic.files().find(
                where.text().matches("mycsv")
        );
        assertThat(results.getFiles()).hasSize(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(1);
    }

    @Test
    public void itShouldFindFilesByOwner() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        FilesPage results = catalytic.files().find(
                where.owner().is("tcaflisch@catalytic.com")
        );
        assertThat(results.getFiles()).hasSize(101);
        assertThat(results.getNextPageToken()).isEqualTo("25");
        assertThat(results.getCount()).isEqualTo(25);
    }

    @Test
    public void itShouldFindFilesByWorkflowId() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        FilesPage results = catalytic.files().find(
                where.workflowId().is("7e77254c-d2d6-4271-965a-98390aefa50a")
        );
        assertThat(results.getFiles()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindFilesByInstanceId() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        FilesPage results = catalytic.files().find(
                where.instanceId().is("f956af4a-6e2c-4bee-9f6e-09d3387b7c8c")
        );
        assertThat(results.getFiles()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldUploadAFile() throws Exception {
        Client catalytic = new Client();
        java.io.File fileToUpload = new java.io.File("/users/tomcaflisch/Downloads/mytest.txt");
        File file = catalytic.files().upload(fileToUpload);
        assertThat(file).isNotNull();
        assertThat(file.getName()).isEqualTo("mytest.txt");
    }

    @Test
    public void itShouldDownloadFileToTempDir() throws Exception {
        Client catalytic = new Client();
        java.io.File File = catalytic.files().download("924cd388-addb-42f7-913e-24c9beb17635");
        assertThat(File).isNotNull();
    }

    @Test
    public void itShouldDownloadFileToSpecificDirWithoutTrailingSlash() throws Exception {
        Client catalytic = new Client();
        java.io.File File = catalytic.files().download("924cd388-addb-42f7-913e-24c9beb17635", "/users/tomcaflisch/Downloads");
        assertThat(File).isNotNull();
        assertThat(File.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + File.getName());
    }

    @Test
    public void itShouldDownloadFileToSpecificDirWithTrailingSlash() throws Exception {
        Client catalytic = new Client();
        java.io.File File = catalytic.files().download("924cd388-addb-42f7-913e-24c9beb17635", "/users/tomcaflisch/Downloads/");
        assertThat(File).isNotNull();
        assertThat(File.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + File.getName());
    }
}
