package org.catalytic.sdk.clients;

import org.catalytic.sdk.ApiClient;
import org.catalytic.sdk.ApiException;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.api.FilesApi;
import org.catalytic.sdk.entities.FileMetadata;
import org.catalytic.sdk.entities.FilesPage;
import org.catalytic.sdk.exceptions.FileNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Files client
 */
public class Files {

    private FilesApi filesApi;

    public Files(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.filesApi = new FilesApi(apiClient);
    }

    /**
     * Get a file by id
     *
     * @param id                        The id of the file to get
     * @return                          The File object
     * @throws InternalErrorException   If any errors fetching the file
     * @throws FileNotFoundException    If file with id does not exist
     */
    public FileMetadata get(UUID id) throws InternalErrorException, FileNotFoundException {
        return this.get(id.toString());
    }

    /**
     * Get a file by id
     *
     * @param id                        The id of the file to get
     * @return                          The File object
     * @throws InternalErrorException   If any errors fetching the file
     * @throws FileNotFoundException    If file with id does not exist
     */
    public FileMetadata get(String id) throws InternalErrorException, FileNotFoundException {
        FileMetadata file;
        try {
            file = this.filesApi.getFile(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new FileNotFoundException("File meta data with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get file meta data", e);
        }

        return file;
    }

    /**
     * Find all files
     *
     * @return                          A FilesPage which contains the results
     * @throws InternalErrorException   If any errors finding files
     */
    public FilesPage find() throws InternalErrorException {
        return this.find(null, null, null);
    }

    /**
     * Find files by a variety of criteria
     *
     * @param filter                    The filter criteria to search files by
     * @return                          A FilesPage which contains the results
     * @throws InternalErrorException   If any errors finding files
     */
    public FilesPage find(Filter filter) throws InternalErrorException {
        return this.find(filter, null, null);
    }

    /**
     * Find files by a variety of criteria
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A FilesPage which contains the results
     * @throws InternalErrorException   If any errors finding files
     */
    public FilesPage find(String pageToken) throws InternalErrorException {
        return this.find(null, pageToken, null);
    }

    /**
     * Find files by a variety of criteria
     *
     * @param filter                    The filter criteria to search files by
     * @param pageToken                 The token of the page to fetch
     * @return                          A FilesPage which contains the results
     * @throws InternalErrorException   If any errors finding files
     */
    public FilesPage find(Filter filter, String pageToken) throws InternalErrorException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Find files by a variety of criteria
     *
     * @param filter                    The filter criteria to search files by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of files per page to fetch
     * @return                          A FilesPage which contains the results
     * @throws InternalErrorException   If any errors finding files
     */
    public FilesPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException {
        String text = null;
        String owner = null;
        String workflowId = null;
        String instanceId = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
            owner = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "owner");
            workflowId = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "workflow_id");
            instanceId = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "instance_id");
        }

        org.catalytic.sdk.model.FilesPage results = null;
        List<FileMetadata> files = new ArrayList<>();

        try {
            results = this.filesApi.findFiles(text, null, workflowId, instanceId, owner, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to find files", e);
        }

        for (org.catalytic.sdk.model.FileMetadata internalFile : results.getFiles()) {
            FileMetadata fileMetadata = createFile(internalFile);
            files.add(fileMetadata);
        }

        FilesPage filesPage = new FilesPage(files, results.getCount(), results.getNextPageToken());
        return filesPage;
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
     */
    public File download(String id) throws InternalErrorException, FileNotFoundException {
        return this.download(id, null);
    }

    /**
     * Downloads a file to the users temp dir
     *
     * @param id                        The id of the file to download
     * @return                          An object containing the file info
     * @throws InternalErrorException   If any errors downloading the file
     * @throws FileNotFoundException    If file with id does not exist
     */
    public File download(UUID id) throws InternalErrorException, FileNotFoundException {
        return this.download(id.toString(), null);
    }

    /**
     * Downloads a file to the users temp dir or a specified dir if passed in
     *
     * @param id                        The id of the file to download
     * @param directory                 The dir to download the file to
     * @return                          An object containing the file info
     * @throws InternalErrorException   If any errors downloading the file
     * @throws FileNotFoundException    If file with id does not exist
     */
    public File download(UUID id, String directory) throws InternalErrorException, FileNotFoundException {
        return this.download(id.toString(), directory);
    }

    /**
     * Downloads a file to the users temp dir or a specified dir if passed in
     *
     * @param id                        The id of the file to download
     * @param directory                 The dir to download the file to
     * @return                          An object containing the file info
     * @throws InternalErrorException   If any errors downloading the file
     * @throws FileNotFoundException    If file with id does not exist
     */
    public File download(String id, String directory) throws InternalErrorException, FileNotFoundException {
        File file;

        try {
            file = this.filesApi.downloadFile(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
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
        file = new File(newPath.toString());
        return file;
    }

    /**
     * Uploads the passed in file
     *
     * @param fileToUpload              The file to upload
     * @return                          The meta data of the file that was uploaded
     * @throws InternalErrorException   If any errors uploading the file
     */
    public FileMetadata upload(java.io.File fileToUpload) throws Exception {
//        List<java.io.File> filesToUpload = new ArrayList<>();
//        filesToUpload.add(fileToUpload);
        org.catalytic.sdk.model.FilesPage internalFile;
        try {
            internalFile = this.filesApi.uploadFiles(List.of(fileToUpload));
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to upload file", e);
        }

        FileMetadata file = createFile(internalFile.getFiles().get(0));
        return file;
    }

    /**
     * Create a File object from an internal File object
     *
     * @param internalFile  The internal file to create a File object from
     * @return              The created File object
     */
    private FileMetadata createFile(FileMetadata internalFile) {
        FileMetadata fileMetadata = new FileMetadata(
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
