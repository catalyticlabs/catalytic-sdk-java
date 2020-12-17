/*
 * Catalytic SDK API
 *  ## API for the Catalytic SDK 
 *
 * The version of the OpenAPI document: 1.0.6
 * Contact: developers@catalytic.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.catalytic.sdk.generated.api;

import org.catalytic.sdk.generated.ApiCallback;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.ApiResponse;
import org.catalytic.sdk.generated.Configuration;
import org.catalytic.sdk.generated.Pair;
import org.catalytic.sdk.generated.ProgressRequestBody;
import org.catalytic.sdk.generated.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.catalytic.sdk.generated.model.AccessToken;
import org.catalytic.sdk.generated.model.AccessTokenCreationRequest;
import org.catalytic.sdk.generated.model.AccessTokenCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.generated.model.ProblemDetails;
import org.catalytic.sdk.generated.model.WaitForAccessTokenApprovalRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationApi {
    private ApiClient localVarApiClient;

    public AuthenticationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AuthenticationApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for createAccessToken
     * @param accessTokenCreationRequest Params required to create new AccessToken (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAccessTokenCall(AccessTokenCreationRequest accessTokenCreationRequest, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = accessTokenCreationRequest;

        // create path and map variables
        String localVarPath = "/api/auth";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAccessTokenValidateBeforeCall(AccessTokenCreationRequest accessTokenCreationRequest, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = createAccessTokenCall(accessTokenCreationRequest, _callback);
        return localVarCall;

    }

    /**
     * Create new AccessToken in the provided Catalytic team domain.  AccessToken must be approved prior to use.
     * 
     * @param accessTokenCreationRequest Params required to create new AccessToken (optional)
     * @return AccessToken
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public AccessToken createAccessToken(AccessTokenCreationRequest accessTokenCreationRequest) throws ApiException {
        ApiResponse<AccessToken> localVarResp = createAccessTokenWithHttpInfo(accessTokenCreationRequest);
        return localVarResp.getData();
    }

    /**
     * Create new AccessToken in the provided Catalytic team domain.  AccessToken must be approved prior to use.
     * 
     * @param accessTokenCreationRequest Params required to create new AccessToken (optional)
     * @return ApiResponse&lt;AccessToken&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AccessToken> createAccessTokenWithHttpInfo(AccessTokenCreationRequest accessTokenCreationRequest) throws ApiException {
        okhttp3.Call localVarCall = createAccessTokenValidateBeforeCall(accessTokenCreationRequest, null);
        Type localVarReturnType = new TypeToken<AccessToken>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create new AccessToken in the provided Catalytic team domain.  AccessToken must be approved prior to use. (asynchronously)
     * 
     * @param accessTokenCreationRequest Params required to create new AccessToken (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAccessTokenAsync(AccessTokenCreationRequest accessTokenCreationRequest, final ApiCallback<AccessToken> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAccessTokenValidateBeforeCall(accessTokenCreationRequest, _callback);
        Type localVarReturnType = new TypeToken<AccessToken>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for createAndApproveAccessToken
     * @param accessTokenCreationWithEmailAndPasswordRequest Params required to create and approve new AccessToken (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAndApproveAccessTokenCall(AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = accessTokenCreationWithEmailAndPasswordRequest;

        // create path and map variables
        String localVarPath = "/api/auth/create-and-approve";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createAndApproveAccessTokenValidateBeforeCall(AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = createAndApproveAccessTokenCall(accessTokenCreationWithEmailAndPasswordRequest, _callback);
        return localVarCall;

    }

    /**
     * Create new AccessToken using provided Catalytic team domain and Approve using provided email and password.
     * 
     * @param accessTokenCreationWithEmailAndPasswordRequest Params required to create and approve new AccessToken (optional)
     * @return AccessToken
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public AccessToken createAndApproveAccessToken(AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest) throws ApiException {
        ApiResponse<AccessToken> localVarResp = createAndApproveAccessTokenWithHttpInfo(accessTokenCreationWithEmailAndPasswordRequest);
        return localVarResp.getData();
    }

    /**
     * Create new AccessToken using provided Catalytic team domain and Approve using provided email and password.
     * 
     * @param accessTokenCreationWithEmailAndPasswordRequest Params required to create and approve new AccessToken (optional)
     * @return ApiResponse&lt;AccessToken&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<AccessToken> createAndApproveAccessTokenWithHttpInfo(AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest) throws ApiException {
        okhttp3.Call localVarCall = createAndApproveAccessTokenValidateBeforeCall(accessTokenCreationWithEmailAndPasswordRequest, null);
        Type localVarReturnType = new TypeToken<AccessToken>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create new AccessToken using provided Catalytic team domain and Approve using provided email and password. (asynchronously)
     * 
     * @param accessTokenCreationWithEmailAndPasswordRequest Params required to create and approve new AccessToken (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createAndApproveAccessTokenAsync(AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest, final ApiCallback<AccessToken> _callback) throws ApiException {

        okhttp3.Call localVarCall = createAndApproveAccessTokenValidateBeforeCall(accessTokenCreationWithEmailAndPasswordRequest, _callback);
        Type localVarReturnType = new TypeToken<AccessToken>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for waitForAccessTokenApproval
     * @param waitForAccessTokenApprovalRequest Params required to poll approved AccessToken (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call waitForAccessTokenApprovalCall(WaitForAccessTokenApprovalRequest waitForAccessTokenApprovalRequest, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = waitForAccessTokenApprovalRequest;

        // create path and map variables
        String localVarPath = "/api/auth/wait-for-approval";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json-patch+json", "application/json", "text/json", "application/_*+json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call waitForAccessTokenApprovalValidateBeforeCall(WaitForAccessTokenApprovalRequest waitForAccessTokenApprovalRequest, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = waitForAccessTokenApprovalCall(waitForAccessTokenApprovalRequest, _callback);
        return localVarCall;

    }

    /**
     * Wait until AccessToken is approved
     * 
     * @param waitForAccessTokenApprovalRequest Params required to poll approved AccessToken (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public Object waitForAccessTokenApproval(WaitForAccessTokenApprovalRequest waitForAccessTokenApprovalRequest) throws ApiException {
        ApiResponse<Object> localVarResp = waitForAccessTokenApprovalWithHttpInfo(waitForAccessTokenApprovalRequest);
        return localVarResp.getData();
    }

    /**
     * Wait until AccessToken is approved
     * 
     * @param waitForAccessTokenApprovalRequest Params required to poll approved AccessToken (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> waitForAccessTokenApprovalWithHttpInfo(WaitForAccessTokenApprovalRequest waitForAccessTokenApprovalRequest) throws ApiException {
        okhttp3.Call localVarCall = waitForAccessTokenApprovalValidateBeforeCall(waitForAccessTokenApprovalRequest, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Wait until AccessToken is approved (asynchronously)
     * 
     * @param waitForAccessTokenApprovalRequest Params required to poll approved AccessToken (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call waitForAccessTokenApprovalAsync(WaitForAccessTokenApprovalRequest waitForAccessTokenApprovalRequest, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = waitForAccessTokenApprovalValidateBeforeCall(waitForAccessTokenApprovalRequest, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
