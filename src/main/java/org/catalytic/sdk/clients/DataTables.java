package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.DataTable;
import org.catalytic.sdk.entities.DataTableColumn;
import org.catalytic.sdk.entities.DataTablesPage;
import org.catalytic.sdk.entities.FieldDisplayOptions;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.DataTableNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.DataTablesApi;
import org.catalytic.sdk.generated.model.DataTableExportFormat;
import org.catalytic.sdk.search.DataTableSearchClause;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataTables client
 */
public class DataTables extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(DataTables.class);
    private DataTablesApi dataTablesApi;
    private Files files;

    public DataTables(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(this.token);

        if (this.token != null) {
            this.token = this.token.trim();
        }

        this.files = new Files(this.token);
        this.dataTablesApi = new DataTablesApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock DataTablesApi
     *
     * @param token         The token to be used
     * @param dataTablesApi The mocked DataTablesApi
     */
    public DataTables(String token, DataTablesApi dataTablesApi) {
        this.token = token;
        this.dataTablesApi = dataTablesApi;
    }

    /**
     * Get a dataTable by id
     *
     * @param id                            The id of the dataTable to get
     * @return                              The DataTable object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors fetching the dataTable
     * @throws DataTableNotFoundException   If user with id doesn't exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable get(String id) throws InternalErrorException, DataTableNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);
        org.catalytic.sdk.generated.model.DataTable internalDataTable;
        try {
            log.debug("Getting dataTable with id {}", () -> id);
            internalDataTable = this.dataTablesApi.getDataTable(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new DataTableNotFoundException("DataTable with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get dataTable", e);
        }

        DataTable dataTable = createDataTable(internalDataTable);
        return dataTable;
    }

    /**
     * Finds Data Tables by a variety of filters
     *
     * @param dataTableSearchClause         The search criteria to search Data Tables by
     * @return                              A DataTablesPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding DataTables
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTablesPage search(DataTableSearchClause dataTableSearchClause) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(dataTableSearchClause, null, null);
    }

    /**
     * Finds Data Tables by a variety of filters
     *
     * @param dataTableSearchClause         The search criteria to search Data Tables by
     * @param pageToken                     The token of the page to fetch
     * @return                              A DataTablesPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding DataTables
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTablesPage search(DataTableSearchClause dataTableSearchClause, String pageToken) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(dataTableSearchClause, pageToken, null);
    }

    /**
     * Finds Data Tables by a variety of filters
     *
     * @param dataTableSearchClause         The search criteria to search Data Tables by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of Data Tables to fetch per page
     * @return                              A DataTablesPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Data Tables
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTablesPage search(DataTableSearchClause dataTableSearchClause, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, InternalErrorException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.DataTablesPage results;
        List<DataTable> dataTables = new ArrayList<>();

        if (dataTableSearchClause == null) {
            dataTableSearchClause = new DataTableSearchClause();
        }

        org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(dataTableSearchClause.getId());
        org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(dataTableSearchClause.getName());
        org.catalytic.sdk.generated.model.BoolSearchExpression internalIsArchived = createInternalBooleanSearchExpression(dataTableSearchClause.getIsArchived());
        org.catalytic.sdk.generated.model.ExactStringSearchExpression internalCreatedByEmail = createInternalExactStringSearchExpression(dataTableSearchClause.getCreatedByEmail());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(dataTableSearchClause.getCreatedDate());

        List<org.catalytic.sdk.generated.model.DataTableSearchClause> internalAnd = createInternalDataTableSearchClause(dataTableSearchClause.getAnd());
        List<org.catalytic.sdk.generated.model.DataTableSearchClause> internalOr = createInternalDataTableSearchClause(dataTableSearchClause.getOr());

        org.catalytic.sdk.generated.model.DataTableSearchClause dataTableSearchRequest = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        dataTableSearchRequest.setAnd(internalAnd);
        dataTableSearchRequest.setOr(internalOr);
        dataTableSearchRequest.setId(internalId);
        dataTableSearchRequest.setName(internalName);
        dataTableSearchRequest.setIsArchived(internalIsArchived);
        dataTableSearchRequest.setCreatedByEmail(internalCreatedByEmail);
        dataTableSearchRequest.setCreatedDate(internalCreatedDate);

        try {
            log.debug("Finding Data Tables with criteria {}", () -> dataTableSearchRequest);
            results = this.dataTablesApi.searchDataTables(pageToken, pageSize, dataTableSearchRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find Data Tables", e);
        }

        for (org.catalytic.sdk.generated.model.DataTable internalDataTable : results.getDataTables()) {
            DataTable dataTable = createDataTable(internalDataTable);
            dataTables.add(dataTable);
        }

        DataTablesPage dataTablesPage = new DataTablesPage(dataTables, results.getCount(), results.getNextPageToken());
        return dataTablesPage;
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @return                              An object containing the dataTable file info
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors downloading and saving the file
     * @throws IOException                  If any errors saving the file to the temp dir
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        return this.download(id, null, null);
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @param format                        The format of the dataTable to download
     * @return                              An object containing the dataTable file info
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors downloading
     * @throws IOException                  If any errors saving the file to the temp dir
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id, String format) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        return this.download(id, format, null);
    }

    /**
     * Downloads a dataTable in the format passed in to the users temp dir or a specified dir if passed in
     *
     * @param id                            The id of the dataTable to download
     * @param format                        The format of the dataTable to download
     * @param directory                     The dir to download the dataTable to
     * @return                              An object containing the dataTable file info
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors downloading
     * @throws IOException                  If any errors saving the file to directory param
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public File download(String id, String format, String directory) throws InternalErrorException, IOException, DataTableNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        File file;
        DataTableExportFormat downloadFormat = null;

        if (format != null) {
            downloadFormat = DataTableExportFormat.fromValue(format);
        }

        try {
            log.debug("Downloading dataTable with id {} and format {}", id, downloadFormat);
            file = this.dataTablesApi.downloadDataTable(id, downloadFormat);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
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
     * @param fileToUpload                  The file to upload as a dataTable
     * @param tableName                     A name to give to the table
     * @return                              The DataTable that was uploaded
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors uploading the dataTable
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable upload(File fileToUpload, String tableName) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.upload(fileToUpload, tableName, null, null);
    }

    /**
     * Uploads the passed in file as a dataTable
     *
     * @param fileToUpload                  The file to upload as a dataTable
     * @param tableName                     A name to give to the table
     * @param headerRow                     The header row
     * @param sheetNumber                   The sheet number of an excel file to use
     * @return                              The DataTable that was uploaded
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors uploading the dataTable
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable upload(File fileToUpload, String tableName, Integer headerRow, Integer sheetNumber) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.DataTable internalDataTable;

        try {
            log.debug("Uploading dataTable with tableName {}", () -> tableName);
            internalDataTable = this.dataTablesApi.uploadDataTable(tableName, headerRow, sheetNumber, Arrays.asList(fileToUpload));
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
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
     * @param fileToUpload                  The dataTable to replace with
     * @return                              The newly replaced DataTable
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors replacing the dataTable
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable replace(String id, File fileToUpload) throws DataTableNotFoundException, InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.replace(id, fileToUpload, null, null);
    }

    /**
     * Replaces the dataTable with id with the passed in dataTableFile
     *
     * @param id                            The id of the dataTable to replace
     * @param fileToUpload                  The dataTable to replace with
     * @param headerRow                     The header row
     * @param sheetNumber                   The sheet number of an excel file to use
     * @return                              The newly replaced DataTable
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors replacing the dataTable
     * @throws DataTableNotFoundException   If dataTable with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public DataTable replace(String id, File fileToUpload, Integer headerRow, Integer sheetNumber) throws InternalErrorException, DataTableNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.DataTable internalDataTable;
        try {
            log.debug("Replacing dataTable with id {}", () -> id);
            internalDataTable = this.dataTablesApi.replaceDataTable(id, headerRow, sheetNumber, Arrays.asList(fileToUpload));
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
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
        String type = null;
        String visibility = null;

        if (internalDataTable.getColumns() != null) {
            for (org.catalytic.sdk.generated.model.DataTableColumn internalColumn : internalDataTable.getColumns()) {
                DataTableColumn column = createDataTableColumn(internalColumn);
                columns.add(column);
            }
        }

        if (internalDataTable.getType() != null) {
            type = internalDataTable.getType().getValue();
        }

        if (internalDataTable.getVisibility() != null) {
            visibility = internalDataTable.getVisibility().getValue();
        }

        DataTable dataTable = new DataTable(
                internalDataTable.getId(),
                internalDataTable.getReferenceName(),
                internalDataTable.getName(),
                internalDataTable.getTeamName(),
                internalDataTable.getDescription(),
                columns,
                internalDataTable.getIsArchived(),
                type,
                visibility,
                internalDataTable.getVisibleToUsers(),
                internalDataTable.getRowLimit(),
                internalDataTable.getColumnLimit(),
                internalDataTable.getCellLimit()
        );
        return dataTable;
    }

    /**
     * Create a DataTableColumn object from an internal DataTableColumn object
     *
     * @param internalColumn    The internal column to create a DataTableColumn object from
     * @return                  The created DataTableColumn object
     */
    private DataTableColumn createDataTableColumn(org.catalytic.sdk.generated.model.DataTableColumn internalColumn) {
        FieldDisplayOptions display = new FieldDisplayOptions(
                internalColumn.getDisplay().getChoices(),
                internalColumn.getDisplay().getValueRequired()
        );


        DataTableColumn column = new DataTableColumn(
                internalColumn.getName(),
                internalColumn.getFieldType(),
                internalColumn.getReferenceName(),
                display
        );

        return column;
    }

    /**
     * Create an internal DataTableSearchClause from an external DataTableSearchClause
     *
     * @param dataTableSearchClause The external DataTableSearchClause to create an internal one from
     * @return                      An internal DataTableSearchClause
     */
    private List<org.catalytic.sdk.generated.model.DataTableSearchClause> createInternalDataTableSearchClause(List<DataTableSearchClause> dataTableSearchClause) {
        List<org.catalytic.sdk.generated.model.DataTableSearchClause> internalDataTableSearchClauses = null;

        if (dataTableSearchClause != null) {

            internalDataTableSearchClauses = new ArrayList<>();

            for (DataTableSearchClause searchClause : dataTableSearchClause) {
                org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(searchClause.getId());
                org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(searchClause.getName());
                org.catalytic.sdk.generated.model.BoolSearchExpression internalIsArchived = createInternalBooleanSearchExpression(searchClause.getIsArchived());
                org.catalytic.sdk.generated.model.ExactStringSearchExpression internalCreatedByEmail = createInternalExactStringSearchExpression(searchClause.getCreatedByEmail());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(searchClause.getCreatedDate());

                org.catalytic.sdk.generated.model.DataTableSearchClause internalDataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
                internalDataTableSearchClause.setId(internalId);
                internalDataTableSearchClause.setName(internalName);
                internalDataTableSearchClause.setIsArchived(internalIsArchived);
                internalDataTableSearchClause.setCreatedByEmail(internalCreatedByEmail);
                internalDataTableSearchClause.setCreatedDate(internalCreatedDate);
                List<org.catalytic.sdk.generated.model.DataTableSearchClause> internalAnd = createInternalDataTableSearchClause(searchClause.getAnd());
                List<org.catalytic.sdk.generated.model.DataTableSearchClause> internalOr = createInternalDataTableSearchClause(searchClause.getOr());
                internalDataTableSearchClause.setAnd(internalAnd);
                internalDataTableSearchClause.setOr(internalOr);
                internalDataTableSearchClauses.add(internalDataTableSearchClause);
            }
        }
        return internalDataTableSearchClauses;
    }
}
