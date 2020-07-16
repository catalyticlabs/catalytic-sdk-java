## Internal Docs
> Documentation related to development of this project

## Regenerate

The `/src/main/java/org/catalytic/sdk/generated` folder is generated using [OpenAPI Generator](https://openapi-generator.tech).

To regenerate the code, you'll probably need to update the url in `./regenerate` and then run the command:

```sh
$ ./regenerate
```

**Important!**: There seem to be some bugs in the generated code so there are 2 methods that need to be added/replaced.

[https://github.com/OpenAPITools/openapi-generator/issues/5959](https://github.com/OpenAPITools/openapi-generator/issues/5959)

[https://github.com/OpenAPITools/openapi-generator/issues/6134](https://github.com/OpenAPITools/openapi-generator/issues/6134)

1. Add this method to `/src/main/java/org/catalytic/sdk/generated/ApiClient.java`
	
	```java
	/**
     * Helper method to set token for HTTP bearer authentication.
     * @param bearerToken the token
     */
    public void setBearerToken(String bearerToken) {
        for (Authentication auth : authentications.values()) {
            if (auth instanceof HttpBearerAuth) {
                ((HttpBearerAuth) auth).setBearerToken(bearerToken);
                return;
            }
        }
        throw new RuntimeException("No Bearer authentication configured!");
    }
	```
2. Replace the method `/src/main/java/org/catalytic/sdk/generated/ApiClient.java#buildRequestBodyMultipart` with this method

	```java
	/**
     * Build a multipart (file uploading) request body with the given form parameters,
     * which could contain text fields and file fields.
     *
     * @param formParams Form parameters in the form of Map
     * @return RequestBody
     */
    public RequestBody buildRequestBodyMultipart(Map<String, Object> formParams) {
        MultipartBody.Builder mpBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Entry<String, Object> param : formParams.entrySet()) {
            if (param.getValue() instanceof List) {
                for (Object value : (List) param.getValue()) {
                    if (value instanceof File) {
                        File file = (File) value;
                        Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + param.getKey() + "\"; filename=\"" + file.getName() + "\"");
                        MediaType mediaType = MediaType.parse(guessContentTypeFromFile(file));
                        mpBuilder.addPart(partHeaders, RequestBody.create(mediaType, file));
                    } else {
                        Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + param.getKey() + "\"");
                        mpBuilder.addPart(partHeaders, RequestBody.create(null, parameterToString(param.getValue())));
                    }
                }
            } else {
                if (param.getValue() instanceof File) {
                    File file = (File) param.getValue();
                    Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + param.getKey() + "\"; filename=\"" + file.getName() + "\"");
                    MediaType mediaType = MediaType.parse(guessContentTypeFromFile(file));
                    mpBuilder.addPart(partHeaders, RequestBody.create(mediaType, file));
                } else {
                    Headers partHeaders = Headers.of("Content-Disposition", "form-data; name=\"" + param.getKey() + "\"");
                    mpBuilder.addPart(partHeaders, RequestBody.create(null, parameterToString(param.getValue())));
                }
            }
        }
        return mpBuilder.build();
    }
    ```

## Publishing

1. Update the version in `build.gradle`
2. Build locally (before committing) so the generated `version` file gets updated
2. Update `CHANGELOG.md`
3. Create a release in github or a git tag manually
