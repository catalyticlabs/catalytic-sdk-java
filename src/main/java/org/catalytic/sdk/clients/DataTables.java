package org.catalytic.sdk.clients;

import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.DataTable;
import org.catalytic.sdk.entities.DataTableColumn;
import org.catalytic.sdk.entities.DataTablesPage;
import org.catalytic.sdk.entities.FieldRestrictions;
import org.catalytic.sdk.exceptions.DataTableNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.DataTablesApi;
import org.catalytic.sdk.generated.model.DataTableExportFormat;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DataTables client
 */
public class DataTables {

    private DataTablesApi dataTablesApi;
    private Files files;

    public DataTables(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.files = new Files(secret.trim());
        this.dataTablesApi = new DataTablesApi(apiClient);
    }

    /**
     * Get a dataTable by id
     *
     * @param id                            The id of the dataTable to get
     * @return                              The DataTable object
     * @throws InternalErrorException       If any errors fetching the dataTable
     * @throws DataTableNotFoundException   If user with id doesn't exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable get(UUID id) throws InternalErrorException, DataTableNotFoundException, UnauthorizedException {
        return this.get(id.toString());
    }

    /**
     * Get a dataTable by id
     *
     * @param id                            The id of the dataTable to get
     * @return                              The DataTable object
     * @throws InternalErrorException       If any errors fetching the dataTable
     * @throws DataTableNotFoundException   If user with id doesn't exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable get(String id) throws InternalErrorException, DataTableNotFoundException, UnauthorizedException {
        org.catalytic.sdk.generated.model.DataTable internalDataTable;
        try {
            internalDataTable = this.dataTablesApi.getDataTable(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new DataTableNotFoundException("DataTable with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get dataTable", e);
        }

        DataTable dataTable = createDataTable(internalDataTable);
        return dataTable;
    }

    /**
     * Find all dataTables
     *
     * @return                          A DataTablesPage object which contains the results
     * @throws InternalErrorException   If any errors finding tables
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTablesPage find() throws InternalErrorException, UnauthorizedException {
        return this.find(null, null, null);
    }

    /**
     * Finds dataTables by a variety of filters
     *
     * @param filter                    The filter to search dataTables by
     * @return                          A DataTablesPage object which contains the results
     * @throws InternalErrorException   If any errors finding tables
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTablesPage find(Filter filter) throws InternalErrorException, UnauthorizedException {
        return this.find(filter, null, null);
    }

    /**
     * Finds dataTables by a variety of filters
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A DataTablesPage object which contains the results
     * @throws InternalErrorException   If any errors finding tables
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTablesPage find(String pageToken) throws InternalErrorException, UnauthorizedException {
        return this.find(null, pageToken, null);
    }

    /**
     * Finds dataTables by a variety of filters
     *
     * @param filter                    The filter to search dataTables by
     * @param pageToken                 The token of the page to fetch
     * @return                          A DataTablesPage object which contains the results
     * @throws InternalErrorException   If any errors finding tables
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTablesPage find(Filter filter, String pageToken) throws InternalErrorException, UnauthorizedException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Finds dataTables by a variety of filters
     *
     * @param filter                    The filter to search dataTables by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of dataTables per page to fetch
     * @return                          A DataTablesPage object which contains the results
     * @throws InternalErrorException   If any errors finding tables
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTablesPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException, UnauthorizedException {
        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        org.catalytic.sdk.generated.model.DataTablesPage internalDataTables = null;
        try {
            internalDataTables = this.dataTablesApi.findDataTables(text, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            }
            throw new InternalErrorException("Unable to find dataTables", e);
        }
        List<DataTable> dataTables = new ArrayList<>();

        for (org.catalytic.sdk.generated.model.DataTable internalInstance : internalDataTables.getDataTables()) {
            DataTable dataTable = createDataTable(internalInstance);
            dataTables.add(dataTable);
        }

        DataTablesPage dataTablesPage = new DataTablesPage(dataTables, internalDataTables.getCount(), internalDataTables.getNextPageToken());
        return dataTablesPage;
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @return                              An object containing the dataTable file info
     * @throws InternalErrorException       If any errors downloading and saving the file
     * @throws IOException                  If any errors saving the file to the temp dir
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException {
        return this.download(id, null, null);
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @param format                        The format of the dataTable to download
     * @return                              An object containing the dataTable file info
     * @throws InternalErrorException       If any errors downloading
     * @throws IOException                  If any errors saving the file to the temp dir
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id, String format) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException {
        return this.download(id, format, null);
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @param format                        The format of the dataTable to download
     * @param directory                     The dir to download the dataTable to
     * @return                              An object containing the dataTable file info
     * @throws InternalErrorException       If any errors downloading
     * @throws IOException                  If any errors saving the file to directory param
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id, String format, String directory) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException {
        File file;
        DataTableExportFormat downloadFormat = null;

        if (format != null) {
            downloadFormat = DataTableExportFormat.fromValue(format);
        }

        try {
            file = this.dataTablesApi.downloadDataTable(id, downloadFormat);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new DataTableNotFoundException("DataTable with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to download dataTable", e);
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
     * Uploads the passed in file as a dataTable
     *
     * @param dataTableFile             The file to upload as a dataTable
     * @param tableName                 A name to give to the table
     * @return                          The DataTable that was uploaded
     * @throws InternalErrorException   If any errors uploading the dataTable
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTable upload(File dataTableFile, String tableName) throws InternalErrorException, UnauthorizedException {
        return this.upload(dataTableFile, tableName, 1, 1);
//        return this.upload(dataTableFile, tableName, null, null);
    }

    /**
     * Uploads the passed in file as a dataTable
     *
     * @param dataTableFile             The file to upload as a dataTable
     * @param tableName                 A name to give to the table
     * @param headerRow                 The header row
     * @param sheetNumber               The sheet number of an excel file to use
     * @return                          The DataTable that was uploaded
     * @throws InternalErrorException   If any errors uploading the dataTable
     * @throws UnauthorizedException    If unauthorized
     */
    public DataTable upload(File dataTableFile, String tableName, Integer headerRow, Integer sheetNumber) throws InternalErrorException, UnauthorizedException {
        org.catalytic.sdk.generated.model.DataTable internalDataTable;
        List<File> files = new ArrayList<>();
        files.add(dataTableFile);
        try {
//            internalDataTable = this.dataTablesApi.uploadDataTable(tableName, headerRow, sheetNumber, List.of(dataTableFile));
            internalDataTable = this.dataTablesApi.uploadDataTable(tableName, headerRow, sheetNumber, files);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            }
            throw new InternalErrorException("Unable to upload dataTable", e);
        }

        DataTable dataTable = createDataTable(internalDataTable);
        return dataTable;
    }

    /**
     * Replaces the dataTable with id with the passed in dataTableFile
     *
     * @param id                            The id of the dataTable to replace
     * @param dataTableFile                 The dataTable to replace with
     * @return                              The newly replaced DataTable
     * @throws InternalErrorException       If any errors replacing the dataTable
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable replace(String id, File dataTableFile) throws DataTableNotFoundException, InternalErrorException, UnauthorizedException {
        return this.replace(id, dataTableFile, null, null);
    }

    /**
     * Replaces the dataTable with id with the passed in dataTableFile
     *
     * @param id                            The id of the dataTable to replace
     * @param dataTableFile                 The dataTable to replace with
     * @param headerRow                     The header row
     * @param sheetNumber                   The sheet number of an excel file to use
     * @return                              The newly replaced DataTable
     * @throws InternalErrorException       If any errors replacing the dataTable
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable replace(String id, File dataTableFile, Integer headerRow, Integer sheetNumber) throws InternalErrorException, DataTableNotFoundException, UnauthorizedException {
        org.catalytic.sdk.generated.model.DataTable internalDataTable;
        try {
            internalDataTable = this.dataTablesApi.replaceDataTable(id, headerRow, sheetNumber, List.of(dataTableFile));
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new DataTableNotFoundException("DataTable with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to replace dataTable", e);
        }

        DataTable dataTable = createDataTable(internalDataTable);
        return dataTable;
    }

    /**
     * Creates a DataTable object from an internal DataTable object
     *
     * @param internalDataTable The internal dataTable to create a DataTable object from
     * @return                  The created DataTable object
     */
    private DataTable createDataTable(org.catalytic.sdk.generated.model.DataTable internalDataTable) {
        List<DataTableColumn> columns = new ArrayList<>();

        for (org.catalytic.sdk.generated.model.DataTableColumn internalColumn : internalDataTable.getColumns()) {

            FieldRestrictions restrictions = new FieldRestrictions(
                    internalColumn.getRestrictions().getChoices(),
                    internalColumn.getRestrictions().getValueRequired()
            );

            DataTableColumn column = new DataTableColumn(
                    internalColumn.getName(),
                    internalColumn.getType().getValue(),
                    internalColumn.getReferenceName(),
                    restrictions
            );
            columns.add(column);
        }

        DataTable dataTable = new DataTable(
                internalDataTable.getId(),
                internalDataTable.getReferenceName(),
                internalDataTable.getName(),
                internalDataTable.getTeamName(),
                internalDataTable.getDescription(),
                columns,
                internalDataTable.getIsArchived(),
                internalDataTable.getType().getValue(),
                internalDataTable.getVisibility().getValue(),
                internalDataTable.getVisibleToUsers(),
                internalDataTable.getRowLimit(),
                internalDataTable.getColumnLimit(),
                internalDataTable.getCellLimit()
        );
        return dataTable;
    }
}
