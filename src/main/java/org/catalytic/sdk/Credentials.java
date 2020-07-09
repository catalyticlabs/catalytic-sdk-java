package org.catalytic.sdk;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class containing various ways of fetching the Catalytic token
 */
public class Credentials {

    private static final Logger log = CatalyticLogger.getLogger(Credentials.class);

    /**
     * Tries to find a token.
     *
     * tokenOrFile can either be null, the name of a file, or the path to a file.
     *
     * If $tokenOrFile is null, try to find a token in this order:
     *
     * 1. Read $CATALYTIC_TOKEN env var
     * 2. Read ~/.catalytic/tokens/default
     *
     * If $tokenOrFile is not null, try to find a token in this order:
     *
     * 1. Read ~/.catalytic/tokens/$tokenOrFile
     * 2. Read tokenOrFile as the path to a file
     * 3. Assume it's an actual token that was passed in
     *
     * @param tokenOrFile                   The token, filename, or path to a file for fetching a token
     * @return                              The token
     * @throws IOException                  If any errors reading from a file
     * @throws AccessTokenNotFoundException If a token can't be found
     */
    public String fetchToken(String tokenOrFile) throws AccessTokenNotFoundException, IOException {
        if (tokenOrFile == null) {
            return fromDefault();
        } else {
            String token = fromFile(tokenOrFile);

            // If a token was not found in a file, then a token was passed in so use it
            if (token == null) {
                return tokenOrFile;
            }

            return token;
        }
    }

    /**
     * Fetch the Catalytic token.
     *
     * First tries to fetch the token from the env var $CATALYTIC_TOKEN,
     * then tries to fetch it from ~/.catalytic/tokens/default.
     *
     * @return                              The Catalytic access token
     * @throws AccessTokenNotFoundException If a token can't be found
     * @throws IOException                  If any errors reading from a file
     */
    private String fromDefault() throws AccessTokenNotFoundException, IOException {
        // Try to get the token from the env var
        String token = fetchTokenFromEnvVar();

        // If it didn't exist, try to get it from the default file
        if (token == null) {
            log.debug("CATALYTIC_TOKEN is null/empty");
            token = fetchTokenFromFile(null);
        }

        // If it wasn't found, return null
        if (token == null) {
            String home = System.getProperty("user.home");
            log.debug("Cannot find Access Token in $CATALYTIC_TOKEN " +
                    "environment variable or in " + home + "/.catalytic/tokens/default");
        }

        return token;
    }

    /**
     * Fetch the Catalytic token from a named file in ~/.catalytic/tokens/<fileName>
     *     or the absolute path <fileName>
     *
     * @param fileName                      The name of the file to fetch the token from
     * @return string                       The Catalytic access token
     * @throws AccessTokenNotFoundException If a token can't be found
     * @throws IOException                  If any errors reading from a file
     */
    private String fromFile(String fileName) throws AccessTokenNotFoundException, IOException {
        String token = fetchTokenFromFile(fileName);

        // If it wasn't found, throw an exception
        if (token == null) {
            String home = System.getProperty("user.home");
            log.debug("Cannot find Access Token in " + home + "/.catalytic/tokens/" + fileName + " or " + fileName);
            return null;
        }

        return token;
    }

    /**
     * Fetch the token from the env var CATALYTIC_TOKEN
     *
     * @return  The Catalytic access token
     */
    private String fetchTokenFromEnvVar() {
        log.debug("Looking for Access Token on environment variable CATALYTIC_TOKEN");
        String token = System.getenv("CATALYTIC_TOKEN");
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
            token = getTokenFromFile(fullPath);
            return token;
        }

        String home = System.getProperty("user.home");

        // If it's only the name of a file
        if (fileName != null) {
            path = home + "/.catalytic/tokens/" + fileName;
        } else {
            path = home + "/.catalytic/tokens/default";
        }

        log.debug("Looking for tokens file " + path);
        Path fullPath = Paths.get(path);
        token = getTokenFromFile(fullPath);

        return token;
    }

    /**
     * Tries to get a token from the passed in path
     *
     * @param path          The path to try and get a token from
     * @return              The token if the path was found and readable
     * @throws IOException
     */
    private String getTokenFromFile(Path path) throws IOException {
        String token = null;
        try {
            token = new String(Files.readAllBytes(path));
        } catch (NoSuchFileException e) {
            log.debug("Cannot find Access Token in " + path);
        }

        return token;
    }
}
