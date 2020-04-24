package org.catalytic.sdk;

import org.catalytic.sdk.clients.*;
import org.catalytic.sdk.exceptions.CredentialsNotFoundException;

import java.io.IOException;

/**
 * Client for connecting to Catalytic
 */
public class Client {

    private Workflows workflows;
    private Instances instances;
    private Users users;
    private Files files;
    private DataTables dataTables;
    private org.catalytic.sdk.clients.Credentials credentials;

    /**
     * Syntactic sugar for `new Client(null)`
     *
     * @throws CredentialsNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public Client () throws CredentialsNotFoundException, IOException {
        this(null);
    }

    /**
     * Instantiate the individual clients
     *
     * @param tokenOrFile                   The token/name/path of a file to fetch
     *                                      a token to use for making API requests
     * @throws CredentialsNotFoundException If no token can be found
     * @throws IOException                  If any errors reading a file
     */
    public Client (String tokenOrFile) throws CredentialsNotFoundException, IOException {
        Credentials credentials = new Credentials();
        String token = credentials.fetchToken(tokenOrFile);
        this.workflows = new Workflows(token);
        this.instances = new Instances(token);
        this.users = new Users(token);
        this.files = new Files(token);
        this.dataTables = new DataTables(token);
        this.credentials = new org.catalytic.sdk.clients.Credentials(token);
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

    public org.catalytic.sdk.clients.Credentials credentials() {
        return credentials;
    }
}
