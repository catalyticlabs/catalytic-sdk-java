package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.exceptions.FileNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.FilesApi;
import org.catalytic.sdk.generated.model.FileMetadata;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * Files client
 */
public class Files {

    private static final Logger log = CatalyticLogger.getLogger(Files.class);
    private FilesApi filesApi;

    public Files(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.filesApi = new FilesApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock FilesApi
     *
     * @param filesApi  The mocked FilesApi
     */
    public Files(FilesApi filesApi) {
        this.filesApi = filesApi;
    }

    /**
     * Get a file by id
     *
     * @param id                        The id of the file to get
     * @return                          The File object
     * @throws InternalErrorException   If any errors fetching the file
     * @throws FileNotFoundException    If file with id does not exist
     * @throws UnauthorizedException    If unauthorized
     */
    public File get(String id) throws InternalErrorException, FileNotFoundException, UnauthorizedException {
        FileMetadata internalFile;
        try {
            log.debug("Getting file with id {}", () -> id);
            internalFile = this.filesApi.getFile(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new FileNotFoundException("File meta data with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get file meta data", e);
        }

        File file = createFile(internalFile);
        return file;
    }

//    public Stream getFileStream(String id) {
//        // TODO: Implement
//    }

    /**
     * Downloads a file to the users temp dir
     *
     * @param id                        The id of the file to download
     * @return                          An object containing the file info
     * @throws InternalErrorException   If any errors downloading the file
     * @throws FileNotFoundException    If file with id does not exist
     * @throws IOException              If any errors saving the file to temp dir
     * @throws UnauthorizedException    If unauthorized
     */
    public java.io.File download(String id) throws InternalErrorException, FileNotFoundException, IOException, UnauthorizedException {
        return this.download(id, null);
    }

    /**
     * Downloads a file to the users temp dir or a specified dir if passed in
     *
     * @param id                        The id of the file to download
     * @param directory                 The dir to download the file to
     * @return                          An object containing the file info
     * @throws InternalErrorException   If any errors downloading the file
     * @throws FileNotFoundException    If file with id does not exist
     * @throws IOException              If any errors saving the file to directory param
     * @throws UnauthorizedException    If unauthorized
     */
    public java.io.File download(String id, String directory) throws InternalErrorException, FileNotFoundException, IOException, UnauthorizedException {
        java.io.File file;

        try {
            log.debug("Downloading file with id {}", () -> id);
            file = this.filesApi.downloadFile(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new FileNotFoundException("File with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to download file", e);
        }

        // If no directory was passed in, return the downloaded
        // data table object which is saved in the temp dir
        if (directory == null) {
            return file;
        }

        // If the dir doesn't end with a slash, add one
        if (!directory.endsWith("/")) {
            directory += "/";
        }

        // Move the file from the temp dir
        Path source = file.toPath();
        String targetPath = directory + file.getName();
        Path target = Paths.get(targetPath);
        Path newPath = java.nio.file.Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
        file = new java.io.File(newPath.toString());
        return file;
    }

    /**
     * Uploads the passed in file
     *
     * @param fileToUpload              The file to upload
     * @return                          The meta data of the file that was uploaded
     * @throws InternalErrorException   If any errors uploading the file
     * @throws UnauthorizedException    If unauthorized
     */
    public File upload(java.io.File fileToUpload) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.FileMetadataPage results;
        try {
            log.debug("Uploading file");
            results = this.filesApi.uploadFiles(Arrays.asList(fileToUpload));
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            }
            throw new InternalErrorException("Unable to upload file", e);
        }

        File file = createFile(results.getFiles().get(0));
        return file;
    }

    /**
     * Create a File object from an internal File object
     *
     * @param internalFile  The internal file to create a File object from
     * @return              The created File object
     */
    private File createFile(FileMetadata internalFile) {
        File fileMetadata = new File(
                internalFile.getId(),
                internalFile.getName(),
                internalFile.getTeamName(),
                internalFile.getContentType(),
                internalFile.getSizeInBytes(),
                internalFile.getDisplaySize(),
                internalFile.getIsPublic(),
                internalFile.getMd5Hash(),
                internalFile.getReferenceName()
        );
        return fileMetadata;
    }
}
