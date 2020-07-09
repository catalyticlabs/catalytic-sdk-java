package org.catalytic.sdk;

import org.catalytic.sdk.clients.*;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;

import java.io.IOException;

/**
 * Client for connecting to Catalytic
 */
public class CatalyticClient {

    private String token;
    private Workflows workflows;
    private Instances instances;
    private Users users;
    private Files files;
    private DataTables dataTables;
    private AccessTokens accessTokens;

    /**
     * Instantiate this instance of CatalyticClient
     *
     * @throws AccessTokenNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public CatalyticClient() throws AccessTokenNotFoundException, IOException {
        this(null);
    }

    /**
     * Instantiate this instance of CatalyticClient
     *
     * @param tokenOrFile                   The token, name or a file, or path of a file to fetch
     *                                      a token to use for making API requests
     * @throws AccessTokenNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public CatalyticClient(String tokenOrFile) throws AccessTokenNotFoundException, IOException {
        Credentials credentials = new Credentials();
        this.token = credentials.fetchToken(tokenOrFile);
        initialize();
    }

    public Workflows workflows() {
        return workflows;
    }

    public Instances instances() {
        return instances;
    }

    public Users users() {
        return users;
    }

    public Files files() {
        return files;
    }

    public DataTables dataTables() {
        return dataTables;
    }

    public AccessTokens credentials() {
        return accessTokens;
    }

    /**
     * Get the AccessToken used to instantiate this instance of CatalyticClient
     *
     * @return  The Access Token used to instantiate this instance of CatalyticClient
     */
    public String getAccessToken() {
        return this.token;
    }

    /**
     * Sets the AccessToken and initializes this instance of CatalyticClient
     *
     * @param token The token to initialize this instance of CatalyticClient with
     */
    public void setAccessToken(String token) {
        this.token = token;
        initialize();
    }

    /**
     * Initialize all the clients
     */
    private void initialize() {
        this.workflows = new Workflows(this.token);
        this.instances = new Instances(this.token);
        this.users = new Users(this.token);
        this.files = new Files(this.token);
        this.dataTables = new DataTables(this.token);
        this.accessTokens = new AccessTokens(this.token);
    }
}
