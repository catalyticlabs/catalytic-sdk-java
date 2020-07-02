package org.catalytic.sdk;

import org.catalytic.sdk.clients.*;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;

import java.io.IOException;

/**
 * Client for connecting to Catalytic
 */
public class CatalyticClient {

    private Workflows workflows;
    private Instances instances;
    private Users users;
    private Files files;
    private DataTables dataTables;
    private AccessTokens accessTokens;

    /**
     * Syntactic sugar for `new Client(null)`
     *
     * @throws AccessTokenNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public CatalyticClient() throws AccessTokenNotFoundException, IOException {
        this(null);
    }

    /**
     * Instantiate the individual clients
     *
     * @param tokenOrFile                   The token/name/path of a file to fetch
     *                                      a token to use for making API requests
     * @throws AccessTokenNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public CatalyticClient(String tokenOrFile) throws AccessTokenNotFoundException, IOException {
        Credentials credentials = new Credentials();
        String token = credentials.fetchToken(tokenOrFile);
        this.workflows = new Workflows(token);
        this.instances = new Instances(token);
        this.users = new Users(token);
        this.files = new Files(token);
        this.dataTables = new DataTables(token);
        this.accessTokens = new AccessTokens(token);
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
}
