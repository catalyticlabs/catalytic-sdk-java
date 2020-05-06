/*
 * Catalytic SDK API
 *  ## API for the Catalytic SDK 
 *
 * The version of the OpenAPI document: v1.0.0
 * Contact: developers@catalytic.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.catalytic.sdk.generated.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WorkflowsPage
 */

public class WorkflowsPage {
  public static final String SERIALIZED_NAME_WORKFLOWS = "workflows";
  @SerializedName(SERIALIZED_NAME_WORKFLOWS)
  private List<Workflow> workflows = null;

  public static final String SERIALIZED_NAME_NEXT_PAGE_OPTIONS = "nextPageOptions";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_OPTIONS)
  private PagingOptions nextPageOptions;

  public static final String SERIALIZED_NAME_NEXT_PAGE_TOKEN = "nextPageToken";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_TOKEN)
  private String nextPageToken;

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;


  public WorkflowsPage workflows(List<Workflow> workflows) {
    
    this.workflows = workflows;
    return this;
  }

  public WorkflowsPage addWorkflowsItem(Workflow workflowsItem) {
    if (this.workflows == null) {
      this.workflows = new ArrayList<>();
    }
    this.workflows.add(workflowsItem);
    return this;
  }

   /**
   * Get workflows
   * @return workflows
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Workflow> getWorkflows() {
    return workflows;
  }


  public void setWorkflows(List<Workflow> workflows) {
    this.workflows = workflows;
  }


  public WorkflowsPage nextPageOptions(PagingOptions nextPageOptions) {
    
    this.nextPageOptions = nextPageOptions;
    return this;
  }

   /**
   * Get nextPageOptions
   * @return nextPageOptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PagingOptions getNextPageOptions() {
    return nextPageOptions;
  }


  public void setNextPageOptions(PagingOptions nextPageOptions) {
    this.nextPageOptions = nextPageOptions;
  }


  public WorkflowsPage nextPageToken(String nextPageToken) {
    
    this.nextPageToken = nextPageToken;
    return this;
  }

   /**
   * Get nextPageToken
   * @return nextPageToken
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getNextPageToken() {
    return nextPageToken;
  }


  public void setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
  }


  public WorkflowsPage count(Integer count) {
    
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getCount() {
    return count;
  }


  public void setCount(Integer count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowsPage workflowsPage = (WorkflowsPage) o;
    return Objects.equals(this.workflows, workflowsPage.workflows) &&
        Objects.equals(this.nextPageOptions, workflowsPage.nextPageOptions) &&
        Objects.equals(this.nextPageToken, workflowsPage.nextPageToken) &&
        Objects.equals(this.count, workflowsPage.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workflows, nextPageOptions, nextPageToken, count);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkflowsPage {\n");
    sb.append("    workflows: ").append(toIndentedString(workflows)).append("\n");
    sb.append("    nextPageOptions: ").append(toIndentedString(nextPageOptions)).append("\n");
    sb.append("    nextPageToken: ").append(toIndentedString(nextPageToken)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

