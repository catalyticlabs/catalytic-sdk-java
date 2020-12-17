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


import org.catalytic.sdk.generated.model.Instance;
import org.catalytic.sdk.generated.model.InstanceSearchClause;
import org.catalytic.sdk.generated.model.InstancesPage;
import org.catalytic.sdk.generated.model.ProblemDetails;
import org.catalytic.sdk.generated.model.StartInstanceRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstancesApi {
    private ApiClient localVarApiClient;

    public InstancesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public InstancesApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for findInstances
     * @param query Free text query terms to search all attributes for (optional)
     * @param status Run or task status to search for (optional)
     * @param processId Process ID (aka Pushbot ID or Workflow ID) to search for (optional)
     * @param runId RunID (aka Instance ID) to search for (optional)
     * @param owner Run or task owner to search for (optional)
     * @param category Category of process or run to search for (optional)
     * @param participatingUsers Task assignee to search for (optional)
     * @param startedBefore Latest start date of the task or run to search for (optional)
     * @param startedAfter Earliest start date of the task or run to search for (optional)
     * @param endedBefore Latest end date of the task or run to search for (optional)
     * @param endedAfter Earliest end date of the task or run to search for (optional)
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call findInstancesCall(String query, String status, String processId, String runId, String owner, String category, String participatingUsers, String startedBefore, String startedAfter, String endedBefore, String endedAfter, String pageToken, Integer pageSize, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/instances";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (query != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("query", query));
        }

        if (status != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("status", status));
        }

        if (processId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("process_id", processId));
        }

        if (runId != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("run_id", runId));
        }

        if (owner != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("owner", owner));
        }

        if (category != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("category", category));
        }

        if (participatingUsers != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("participating_users", participatingUsers));
        }

        if (startedBefore != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("started_before", startedBefore));
        }

        if (startedAfter != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("started_after", startedAfter));
        }

        if (endedBefore != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("ended_before", endedBefore));
        }

        if (endedAfter != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("ended_after", endedAfter));
        }

        if (pageToken != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page_token", pageToken));
        }

        if (pageSize != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page_size", pageSize));
        }

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
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @Deprecated
    @SuppressWarnings("rawtypes")
    private okhttp3.Call findInstancesValidateBeforeCall(String query, String status, String processId, String runId, String owner, String category, String participatingUsers, String startedBefore, String startedAfter, String endedBefore, String endedAfter, String pageToken, Integer pageSize, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = findInstancesCall(query, status, processId, runId, owner, category, participatingUsers, startedBefore, startedAfter, endedBefore, endedAfter, pageToken, pageSize, _callback);
        return localVarCall;

    }

    /**
     * Find Instances
     * 
     * @param query Free text query terms to search all attributes for (optional)
     * @param status Run or task status to search for (optional)
     * @param processId Process ID (aka Pushbot ID or Workflow ID) to search for (optional)
     * @param runId RunID (aka Instance ID) to search for (optional)
     * @param owner Run or task owner to search for (optional)
     * @param category Category of process or run to search for (optional)
     * @param participatingUsers Task assignee to search for (optional)
     * @param startedBefore Latest start date of the task or run to search for (optional)
     * @param startedAfter Earliest start date of the task or run to search for (optional)
     * @param endedBefore Latest end date of the task or run to search for (optional)
     * @param endedAfter Earliest end date of the task or run to search for (optional)
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @return InstancesPage
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public InstancesPage findInstances(String query, String status, String processId, String runId, String owner, String category, String participatingUsers, String startedBefore, String startedAfter, String endedBefore, String endedAfter, String pageToken, Integer pageSize) throws ApiException {
        ApiResponse<InstancesPage> localVarResp = findInstancesWithHttpInfo(query, status, processId, runId, owner, category, participatingUsers, startedBefore, startedAfter, endedBefore, endedAfter, pageToken, pageSize);
        return localVarResp.getData();
    }

    /**
     * Find Instances
     * 
     * @param query Free text query terms to search all attributes for (optional)
     * @param status Run or task status to search for (optional)
     * @param processId Process ID (aka Pushbot ID or Workflow ID) to search for (optional)
     * @param runId RunID (aka Instance ID) to search for (optional)
     * @param owner Run or task owner to search for (optional)
     * @param category Category of process or run to search for (optional)
     * @param participatingUsers Task assignee to search for (optional)
     * @param startedBefore Latest start date of the task or run to search for (optional)
     * @param startedAfter Earliest start date of the task or run to search for (optional)
     * @param endedBefore Latest end date of the task or run to search for (optional)
     * @param endedAfter Earliest end date of the task or run to search for (optional)
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @return ApiResponse&lt;InstancesPage&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public ApiResponse<InstancesPage> findInstancesWithHttpInfo(String query, String status, String processId, String runId, String owner, String category, String participatingUsers, String startedBefore, String startedAfter, String endedBefore, String endedAfter, String pageToken, Integer pageSize) throws ApiException {
        okhttp3.Call localVarCall = findInstancesValidateBeforeCall(query, status, processId, runId, owner, category, participatingUsers, startedBefore, startedAfter, endedBefore, endedAfter, pageToken, pageSize, null);
        Type localVarReturnType = new TypeToken<InstancesPage>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Find Instances (asynchronously)
     * 
     * @param query Free text query terms to search all attributes for (optional)
     * @param status Run or task status to search for (optional)
     * @param processId Process ID (aka Pushbot ID or Workflow ID) to search for (optional)
     * @param runId RunID (aka Instance ID) to search for (optional)
     * @param owner Run or task owner to search for (optional)
     * @param category Category of process or run to search for (optional)
     * @param participatingUsers Task assignee to search for (optional)
     * @param startedBefore Latest start date of the task or run to search for (optional)
     * @param startedAfter Earliest start date of the task or run to search for (optional)
     * @param endedBefore Latest end date of the task or run to search for (optional)
     * @param endedAfter Earliest end date of the task or run to search for (optional)
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     * @deprecated
     */
    @Deprecated
    public okhttp3.Call findInstancesAsync(String query, String status, String processId, String runId, String owner, String category, String participatingUsers, String startedBefore, String startedAfter, String endedBefore, String endedAfter, String pageToken, Integer pageSize, final ApiCallback<InstancesPage> _callback) throws ApiException {

        okhttp3.Call localVarCall = findInstancesValidateBeforeCall(query, status, processId, runId, owner, category, participatingUsers, startedBefore, startedAfter, endedBefore, endedAfter, pageToken, pageSize, _callback);
        Type localVarReturnType = new TypeToken<InstancesPage>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getInstance
     * @param id The ID of the Instance to get (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getInstanceCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/instances/{id}"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

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
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getInstanceValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getInstance(Async)");
        }
        

        okhttp3.Call localVarCall = getInstanceCall(id, _callback);
        return localVarCall;

    }

    /**
     * Gets details of a specific Instance
     * 
     * @param id The ID of the Instance to get (required)
     * @return Instance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public Instance getInstance(String id) throws ApiException {
        ApiResponse<Instance> localVarResp = getInstanceWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Gets details of a specific Instance
     * 
     * @param id The ID of the Instance to get (required)
     * @return ApiResponse&lt;Instance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Instance> getInstanceWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = getInstanceValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets details of a specific Instance (asynchronously)
     * 
     * @param id The ID of the Instance to get (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getInstanceAsync(String id, final ApiCallback<Instance> _callback) throws ApiException {

        okhttp3.Call localVarCall = getInstanceValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchInstances
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param instanceSearchClause  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchInstancesCall(String pageToken, Integer pageSize, InstanceSearchClause instanceSearchClause, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = instanceSearchClause;

        // create path and map variables
        String localVarPath = "/api/instances/search";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (pageToken != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page_token", pageToken));
        }

        if (pageSize != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("page_size", pageSize));
        }

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

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call searchInstancesValidateBeforeCall(String pageToken, Integer pageSize, InstanceSearchClause instanceSearchClause, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = searchInstancesCall(pageToken, pageSize, instanceSearchClause, _callback);
        return localVarCall;

    }

    /**
     * Search through workflow instances on team
     * 
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param instanceSearchClause  (optional)
     * @return InstancesPage
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public InstancesPage searchInstances(String pageToken, Integer pageSize, InstanceSearchClause instanceSearchClause) throws ApiException {
        ApiResponse<InstancesPage> localVarResp = searchInstancesWithHttpInfo(pageToken, pageSize, instanceSearchClause);
        return localVarResp.getData();
    }

    /**
     * Search through workflow instances on team
     * 
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param instanceSearchClause  (optional)
     * @return ApiResponse&lt;InstancesPage&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<InstancesPage> searchInstancesWithHttpInfo(String pageToken, Integer pageSize, InstanceSearchClause instanceSearchClause) throws ApiException {
        okhttp3.Call localVarCall = searchInstancesValidateBeforeCall(pageToken, pageSize, instanceSearchClause, null);
        Type localVarReturnType = new TypeToken<InstancesPage>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search through workflow instances on team (asynchronously)
     * 
     * @param pageToken The token representing the result page to get (optional)
     * @param pageSize The page size requested (optional)
     * @param instanceSearchClause  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchInstancesAsync(String pageToken, Integer pageSize, InstanceSearchClause instanceSearchClause, final ApiCallback<InstancesPage> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchInstancesValidateBeforeCall(pageToken, pageSize, instanceSearchClause, _callback);
        Type localVarReturnType = new TypeToken<InstancesPage>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for startInstance
     * @param startInstanceRequest The details of the Instance to start (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call startInstanceCall(StartInstanceRequest startInstanceRequest, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = startInstanceRequest;

        // create path and map variables
        String localVarPath = "/api/instances";

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

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call startInstanceValidateBeforeCall(StartInstanceRequest startInstanceRequest, final ApiCallback _callback) throws ApiException {
        

        okhttp3.Call localVarCall = startInstanceCall(startInstanceRequest, _callback);
        return localVarCall;

    }

    /**
     * Starts a new Instance
     * 
     * @param startInstanceRequest The details of the Instance to start (optional)
     * @return Instance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public Instance startInstance(StartInstanceRequest startInstanceRequest) throws ApiException {
        ApiResponse<Instance> localVarResp = startInstanceWithHttpInfo(startInstanceRequest);
        return localVarResp.getData();
    }

    /**
     * Starts a new Instance
     * 
     * @param startInstanceRequest The details of the Instance to start (optional)
     * @return ApiResponse&lt;Instance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Instance> startInstanceWithHttpInfo(StartInstanceRequest startInstanceRequest) throws ApiException {
        okhttp3.Call localVarCall = startInstanceValidateBeforeCall(startInstanceRequest, null);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Starts a new Instance (asynchronously)
     * 
     * @param startInstanceRequest The details of the Instance to start (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call startInstanceAsync(StartInstanceRequest startInstanceRequest, final ApiCallback<Instance> _callback) throws ApiException {

        okhttp3.Call localVarCall = startInstanceValidateBeforeCall(startInstanceRequest, _callback);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for stopInstance
     * @param id The ID of the Instance to stop (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call stopInstanceCall(String id, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/instances/{id}:stop"
            .replaceAll("\\{" + "id" + "\\}", localVarApiClient.escapeString(id.toString()));

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
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "Bearer" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call stopInstanceValidateBeforeCall(String id, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling stopInstance(Async)");
        }
        

        okhttp3.Call localVarCall = stopInstanceCall(id, _callback);
        return localVarCall;

    }

    /**
     * Stops a specific Instance
     * 
     * @param id The ID of the Instance to stop (required)
     * @return Instance
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public Instance stopInstance(String id) throws ApiException {
        ApiResponse<Instance> localVarResp = stopInstanceWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Stops a specific Instance
     * 
     * @param id The ID of the Instance to stop (required)
     * @return ApiResponse&lt;Instance&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Instance> stopInstanceWithHttpInfo(String id) throws ApiException {
        okhttp3.Call localVarCall = stopInstanceValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Stops a specific Instance (asynchronously)
     * 
     * @param id The ID of the Instance to stop (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Not Found </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Success </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call stopInstanceAsync(String id, final ApiCallback<Instance> _callback) throws ApiException {

        okhttp3.Call localVarCall = stopInstanceValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<Instance>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
