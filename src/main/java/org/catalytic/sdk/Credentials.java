package org.catalytic.sdk;

import org.catalytic.sdk.exceptions.CredentialsNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class containing various ways of fetching the Catalytic token
 */
public class Credentials {

    /**
     * Tries to find a token.
     *
     * tokenOrFile can either be null, the name of a file, or the path to a file.
     *
     * If $tokenOrFile is null, try to find a token in this order:
     *
     * 1. Read $CATALYTIC_CREDENTIALS env var
     * 2. Read ~/.catalytic/credentials/default
     *
     * If $tokenOrFile is not null, try to find a token in this order:
     *
     * 1. Read ~/.catalytic/credentials/$tokenOrFile
     * 2. Read tokenOrFile as the path to a file
     * 3. Assume it's an actual token that was passed in
     *
     * @param tokenOrFile                   The token, filename, or path to a file for fetching a token
     * @return                              The token
     * @throws IOException                  If any errors reading from a file
     * @throws CredentialsNotFoundException If a token can't be found
     */
    public String fetchToken(String tokenOrFile) throws CredentialsNotFoundException, IOException {
        if (tokenOrFile == null) {
            return fromDefault();
        } else {
            return fromFile(tokenOrFile);
        }
    }

    /**
     * Fetch the Catalytic token.
     *
     * First tries to fetch the token from the env var $CATALYTIC_CREDENTIALS,
     * then tries to fetch it from ~/.catalytic/credentials/default.
     *
     * @return                              The Catalytic access token
     * @throws CredentialsNotFoundException If a token can't be found
     * @throws IOException                  If any errors reading from a file
     */
    private String fromDefault() throws CredentialsNotFoundException, IOException {
        // Try to get the token from the env var
        String token = fetchTokenFromEnvVar();

        // If it didn't exist, try to get it from the default file
        if (token == null) {
            token = fetchTokenFromFile(null);
        }

        // If it wasn't found, throw an exception
        if (token == null) {
            String home = System.getProperty("user.home");
            throw new CredentialsNotFoundException("Cannot find credentials in $CATALYTIC_CREDENTIALS " +
                    "environment variable or in " + home + "/.catalytic/credentials/default");
        }

        return token;
    }

    /**
     * Fetch the Catalytic token from a named file in ~/.catalytic/credentials/<fileName>
     *     or the absolute path <fileName>
     *
     * @param fileName                      The name of the file to fetch the token from
     * @return string                       The Catalytic access token
     * @throws CredentialsNotFoundException If a token can't be found
     * @throws IOException                  If any errors reading from a file
     */
    private String fromFile(String fileName) throws CredentialsNotFoundException, IOException {
        String token = fetchTokenFromFile(fileName);

        // If it wasn't found, throw an exception
        if (token == null) {
            String home = System.getProperty("user.home");
            throw new CredentialsNotFoundException("Cannot find credentials in " + home + "/.catalytic/credentials/" + fileName + " or " + fileName);
        }

        return token;
    }

    /**
     * Fetch the token from the env var CATALYTIC_CREDENTIALS
     *
     * @return  The Catalytic access token
     */
    private String fetchTokenFromEnvVar() {
        String token = System.getenv("CATALYTIC_CREDENTIALS");
        return token;
    }

    /**
     * Fetch a Catalytic token from a file
     *
     * @param fileName      The name or path to the file to fetch the token from
     * @return              The Catalytic access token
     * @throws IOException  If any errors reading the file
     */
    private String fetchTokenFromFile(String fileName) throws IOException {
        String path;
        String token;

        // If it's a path to a file
        if (fileName != null) {
            Path fullPath = Paths.get(fileName);
            token = new String(Files.readAllBytes(fullPath));
            return token;
        }

        String home = System.getProperty("user.home");

        // If it's only the name of a file
        if (fileName != null) {
            path = home + "/.catalytic/credentials/" + fileName;
        } else {
            path = home + "/.catalytic/credentials/default";
        }

        Path fullPath = Paths.get(path);
        token = new String(Files.readAllBytes(fullPath));
        return token;
    }
}
