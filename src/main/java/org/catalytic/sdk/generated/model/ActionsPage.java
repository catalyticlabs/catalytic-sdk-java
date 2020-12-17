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


package org.catalytic.sdk.generated.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.catalytic.sdk.generated.model.Action;
import org.catalytic.sdk.generated.model.PagingOptions;

/**
 * A page of Actions returned from a FindAsync request
 */
@ApiModel(description = "A page of Actions returned from a FindAsync request")

public class ActionsPage {
  public static final String SERIALIZED_NAME_ACTIONS = "actions";
  @SerializedName(SERIALIZED_NAME_ACTIONS)
  private List<Action> actions = null;

  public static final String SERIALIZED_NAME_NEXT_PAGE_OPTIONS = "nextPageOptions";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_OPTIONS)
  private PagingOptions nextPageOptions;

  public static final String SERIALIZED_NAME_NEXT_PAGE_TOKEN = "nextPageToken";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_TOKEN)
  private String nextPageToken;

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;


  public ActionsPage actions(List<Action> actions) {
    
    this.actions = actions;
    return this;
  }

  public ActionsPage addActionsItem(Action actionsItem) {
    if (this.actions == null) {
      this.actions = new ArrayList<>();
    }
    this.actions.add(actionsItem);
    return this;
  }

   /**
   * The List of Actions
   * @return actions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The List of Actions")

  public List<Action> getActions() {
    return actions;
  }


  public void setActions(List<Action> actions) {
    this.actions = actions;
  }


  public ActionsPage nextPageOptions(PagingOptions nextPageOptions) {
    
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


  public ActionsPage nextPageToken(String nextPageToken) {
    
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


  public ActionsPage count(Integer count) {
    
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
    ActionsPage actionsPage = (ActionsPage) o;
    return Objects.equals(this.actions, actionsPage.actions) &&
        Objects.equals(this.nextPageOptions, actionsPage.nextPageOptions) &&
        Objects.equals(this.nextPageToken, actionsPage.nextPageToken) &&
        Objects.equals(this.count, actionsPage.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actions, nextPageOptions, nextPageToken, count);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionsPage {\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
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

