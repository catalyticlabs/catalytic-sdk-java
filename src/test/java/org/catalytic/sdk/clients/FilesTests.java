package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.FileNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.FilesApi;
import org.catalytic.sdk.generated.model.FileMetadata;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilesTests {

    FilesApi filesApi;

    @Before
    public void before() {
        filesApi = mock(FilesApi.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getFile_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Files filesClient = new Files(nullString);
        filesClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getFile_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(filesApi.getFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(401, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = FileNotFoundException.class)
    public void getFile_itShouldThrowFileNotFoundExceptionIfFileDoesNotExist() throws Exception {
        when(filesApi.getFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(404, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void getFile_itShouldThrowInternalErrorException() throws Exception {
        when(filesApi.getFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(500, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test
    public void getFile_itShouldGetAFile() throws Exception {
        org.catalytic.sdk.generated.model.FileMetadata internalFile = new org.catalytic.sdk.generated.model.FileMetadata();
        internalFile.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        when(filesApi.getFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenReturn(internalFile);

        Files filesClient = new Files("1234", filesApi);
        File file = filesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(file).isNotNull();
        assertThat(file.getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void downloadFile_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Files filesClient = new Files(nullString);
        filesClient.download("1234");
    }
    
    @Test(expected = UnauthorizedException.class)
    public void downloadFile_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(filesApi.downloadFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(401, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = FileNotFoundException.class)
    public void downloadFile_itShouldThrowFileNotFoundExceptionIfFileDoesNotExist() throws Exception {
        when(filesApi.downloadFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(404, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void downloadFile_itShouldThrowInternalErrorException() throws Exception {
        when(filesApi.downloadFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(500, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test
    public void downloadFile_itShouldReturnTheDownloadedFile() throws Exception {
        java.io.File internalFile = new java.io.File("foobar");
        when(filesApi.downloadFile("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenReturn(internalFile);

        Files filesClient = new Files("1234", filesApi);
        java.io.File file = filesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(file).isNotNull();
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void uploadFile_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        Files filesClient = new Files(nullString);
        filesClient.upload(null);
    }

    @Test(expected = UnauthorizedException.class)
    public void uploadFile_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(filesApi.uploadFiles(Arrays.asList(file))).thenThrow(new ApiException(401, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.upload(file);
    }

    @Test(expected = InternalErrorException.class)
    public void uploadFile_itShouldThrowInternalErrorException() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(filesApi.uploadFiles(Arrays.asList(file))).thenThrow(new ApiException(500, null));

        Files filesClient = new Files("1234", filesApi);
        filesClient.upload(file);
    }

    @Test
    public void uploadFile_itShouldUploadFile() throws Exception {
        java.io.File fileToUpload = new java.io.File("foobar");
        org.catalytic.sdk.generated.model.FileMetadataPage internalFile = new org.catalytic.sdk.generated.model.FileMetadataPage();
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setName("My Table");
        internalFile.setFiles(Arrays.asList(fileMetadata));
        when(filesApi.uploadFiles(Arrays.asList(fileToUpload))).thenReturn(internalFile);

        Files filesClient = new Files("1234", filesApi);
        File file = filesClient.upload(fileToUpload);
        assertThat(file.getName()).isEqualTo("My Table");
    }
}
