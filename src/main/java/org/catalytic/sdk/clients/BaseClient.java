package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.Field;
import org.catalytic.sdk.generated.model.FieldUpdateRequest;

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
                internalField.getRestrictions(),
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
}
