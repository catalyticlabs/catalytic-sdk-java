package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Field;
import org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange;
import org.catalytic.sdk.generated.model.FieldUpdateRequest;
import org.catalytic.sdk.search.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class meant to be extended.
 *
 * Contains common methods to various clients
 */
public class BaseClient {

    /**
     * Create external Fields from internal Fields
     *
     * @param internalFields    The internal fields to create external fields from
     * @return                  External fields
     */
    List<Field> createFields(List<org.catalytic.sdk.generated.model.Field> internalFields) {
        List<Field> fields = new ArrayList<>();

        // Create external outputFields from internal outputFields
        if (internalFields != null) {
            for (org.catalytic.sdk.generated.model.Field internalField : internalFields) {
                Field field = createField(internalField);
                fields.add(field);
            }
        }

        return fields;
    }

    /**
     * Create an external Field from an internal Field
     *
     * @param internalField The internal field to create an external field from
     * @return              An external field
     */
    private Field createField(org.catalytic.sdk.generated.model.Field internalField) {
        Field field = new Field(
                internalField.getId(),
                internalField.getName(),
                internalField.getReferenceName(),
                internalField.getDescription(),
                internalField.getPosition(),
                internalField.getDisplay(),
                internalField.getFieldType().getValue(),
                internalField.getValue(),
                internalField.getDefaultValue()
        );
        return field;
    }

    /**
     * Create FieldUpdateRequest's from Fields
     *
     * @param fields    The fields to create FieldUpdateRequest's from
     * @return          FieldUpdateRequest's created from Field's
     */
    List<FieldUpdateRequest> createFieldUpdateRequests(List<Field> fields) {
        List<FieldUpdateRequest> fieldUpdateRequests = new ArrayList<>();

        if (fields != null) {
            for (Field field : fields) {
                FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
                fieldUpdateRequest.setName(field.getName());
                fieldUpdateRequest.setReferenceName(field.getName());
                fieldUpdateRequest.setValue(field.getValue());
                fieldUpdateRequests.add(fieldUpdateRequest);
            }
        }
        return fieldUpdateRequests;
    }

    /**
     * Create an internal GuidSearchExpression from an external GuidSearchExpression
     *
     * @param guidSearchExpression  The external GuidSearchExpression to create an internal one from
     * @return                      An internal GuidSearchExpression
     */
    org.catalytic.sdk.generated.model.GuidSearchExpression createInternalGuidSearchExpression(GuidSearchExpression guidSearchExpression) {
        org.catalytic.sdk.generated.model.GuidSearchExpression internalGuidSearchExpression = null;

        if (guidSearchExpression != null) {
            internalGuidSearchExpression = new org.catalytic.sdk.generated.model.GuidSearchExpression();
            internalGuidSearchExpression.setIsEqualTo(guidSearchExpression.getIsEqualTo());
        }
        return internalGuidSearchExpression;
    }

    /**
     * Create an internal StringSearchExpression from an external StringSearchExpression
     *
     * @param stringSearchExpression    The external StringSearchExpression to create an internal one from
     * @return                          An internal StringSearchExpression
     */
    org.catalytic.sdk.generated.model.StringSearchExpression createInternalStringSearchExpression(StringSearchExpression stringSearchExpression) {
        org.catalytic.sdk.generated.model.StringSearchExpression internalStringSearchExpression = null;
        
        if (stringSearchExpression != null) {
            internalStringSearchExpression = new org.catalytic.sdk.generated.model.StringSearchExpression();
            internalStringSearchExpression.setIsEqualTo(stringSearchExpression.getIsEqualTo());
            internalStringSearchExpression.setContains(stringSearchExpression.getContains());
            org.catalytic.sdk.generated.model.StringRange internalStringRange = createInternalStringRange(stringSearchExpression.getBetween());
            internalStringSearchExpression.setBetween(internalStringRange);
        }
        return internalStringSearchExpression;
    }

    /**
     * Create an internal ExactStringSearchExpression from an external ExactStringSearchExpression
     *
     * @param exactStringSearchExpression   The external ExactStringSearchExpression to create an internal one from
     * @return                              An internal ExactStringSearchExpression
     */
    org.catalytic.sdk.generated.model.ExactStringSearchExpression createInternalExactStringSearchExpression(ExactStringSearchExpression exactStringSearchExpression) {
        org.catalytic.sdk.generated.model.ExactStringSearchExpression internalExactStringSearchExpression = null;

        if (exactStringSearchExpression != null) {
            internalExactStringSearchExpression = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
            internalExactStringSearchExpression.setIsEqualTo(exactStringSearchExpression.getIsEqualTo());
        }
        return internalExactStringSearchExpression;
    }

    /**
     * Create an internal BooleanSearchExpression from an external BoolSearchExpression
     *
     * @param booleanSearchExpression   The external BooleanSearchExpression to create an internal one from
     * @return                          An internal BooleanSearchExpression
     */
    org.catalytic.sdk.generated.model.BoolSearchExpression createInternalBooleanSearchExpression(BooleanSearchExpression booleanSearchExpression) {
        org.catalytic.sdk.generated.model.BoolSearchExpression internalBooleanSearchExpression = null;

        if (booleanSearchExpression != null) {
            internalBooleanSearchExpression = new org.catalytic.sdk.generated.model.BoolSearchExpression();
            internalBooleanSearchExpression.setIsEqualTo(booleanSearchExpression.getIsEqualTo());
        }
        return internalBooleanSearchExpression;
    }

    /**
     * Create an internal DateTimeSearchExpression from an external DateTimeSearchExpression
     *
     * @param dateTimeSearchExpression  The external DateTimeSearchExpression to create an internal one from
     * @return                          An internal DateTimeSearchExpression
     */
    org.catalytic.sdk.generated.model.DateTimeSearchExpression createInternalDateTimeSearchExpression(DateTimeSearchExpression dateTimeSearchExpression) {
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalDateTimeSearchExpression = null;

        if (dateTimeSearchExpression != null) {
            internalDateTimeSearchExpression = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
            internalDateTimeSearchExpression.setContains(dateTimeSearchExpression.getContains());
            internalDateTimeSearchExpression.setIsEqualTo(dateTimeSearchExpression.getIsEqualTo());
            DateTimeOffsetNullableRange internalDateTimeRange = createInternalDateTimeOffsetRange(dateTimeSearchExpression.getBetween());
            internalDateTimeSearchExpression.setBetween(internalDateTimeRange);
        }
        return internalDateTimeSearchExpression;
    }

    /**
     * Create an internal DateTimeOffsetNullableRange from an external DateTimeRange
     *
     * @param dateTimeOffsetRange   The external DateTimeRange to create an internal DateTimeOffsetNullable range from
     * @return                      An internal DateTimeOffsetNullableRange
     */
    private DateTimeOffsetNullableRange createInternalDateTimeOffsetRange(DateTimeOffsetRange dateTimeOffsetRange) {
        DateTimeOffsetNullableRange internalDateTimeRange = null;

        if (dateTimeOffsetRange != null) {
            internalDateTimeRange = new DateTimeOffsetNullableRange();
            internalDateTimeRange.setLowerBoundInclusive(dateTimeOffsetRange.getLowerBoundInclusive());
            internalDateTimeRange.setUpperBoundInclusive(dateTimeOffsetRange.getUpperBoundInclusive());
        }
        return internalDateTimeRange;
    }

    /**
     * Create an internal StringRange from an external one
     *
     * @param stringRange   The external StringRange to create an internal one from
     * @return              An internal StringRange
     */
    private org.catalytic.sdk.generated.model.StringRange createInternalStringRange(StringRange stringRange) {
        org.catalytic.sdk.generated.model.StringRange internalStringRange = null;

        if (stringRange != null) {
            internalStringRange = new org.catalytic.sdk.generated.model.StringRange();
            internalStringRange.setLowerBoundInclusive(stringRange.getLowerBoundInclusive());
            internalStringRange.setUpperBoundInclusive(stringRange.getUpperBoundInclusive());
        }
        return internalStringRange;
    }
}
