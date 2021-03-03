package Utils;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredUtils {
    private static String BaseURI = "https://";
    private static Logger logger = Logger.getLogger(RestAssuredUtils.class);
    private static RequestSpecification APIrequest;

    public void setBaseURIForRestAssured(String APIendpoint, String IPaddress) {
        StringBuilder url = new StringBuilder();
        url.append(BaseURI).append(IPaddress);
        RestAssured.baseURI = url.toString();
        RestAssured.basePath = APIendpoint;
        logger.info("Base URI - " + RestAssured.baseURI + RestAssured.basePath);
    }

    public void setBaseURIForRestAssured(String APIendpoint, String IPaddress, String BaseURI) {
        StringBuilder url = new StringBuilder();
        url.append(BaseURI).append(IPaddress).append(APIendpoint);
        RestAssured.baseURI = url.toString();
        logger.info("Base URI - " + url.toString());
    }

    public void setHeaders(Map<String, String> headers) {
        APIrequest = RestAssured.given();
        APIrequest.headers(headers);
    }

    public void setBodywithFile(String fileName) throws FileNotFoundException, URISyntaxException {
        File file = new File("src/test/resources/data/" + fileName);
        APIrequest.body(file);
        logger.info("Setting File " + fileName + " as Body of the Request");
    }

    public Response putRequest() {
        Response response = APIrequest.given().relaxedHTTPSValidation().put();
        logger.info("<----Sending PUT Request---->");
        return response;
    }

    public Response postRequest() {
        Response response = APIrequest.given().relaxedHTTPSValidation().post();
        logger.info("<----Sending POST Request---->");
        return response;
    }

    public Response postRequest(Map<String, String> headers, String jsonString) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(jsonString);
        Response response = null;
        try {
            response = request.post();
            // logger.info("Request Body -> "+jsonString);
            logger.info("Request Headers -> " + headers);
        } catch (Exception e) {
            logger.info("Post Request Failed with Exception - " + e);
            //logger.info("Request Body -> "+jsonString);
            // logger.info("Request Headers -> "+headers);
        }

        return response;
    }


    public Response postRequest(Map<String, String> headers, JSONObject jsonObject) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(jsonObject.toString());
        request.relaxedHTTPSValidation();
        Response response = null;
        try {
            response = request.post();
        } catch (Exception e) {
            logger.info("Post Request Failed with Exception - " + e);
        }

        return response;
    }

    public Response postRequest(Map<String, String> headers, JSONObject jsonObject, String userName, String pass) {
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(jsonObject.toString());
        request.auth().basic(userName, pass);

        Response response = request.post();
        return response;
    }

    public Response postRequest(Map<String, String> headers, String jsonString, String userName, String pass) {
        RestAssured.defaultParser = Parser.JSON;
        RequestSpecification request = RestAssured.given();
        request.headers(headers);
        request.body(jsonString);
        request.auth().basic("userName", pass);

        Response response = request.post();
        return response;
    }

    public Response postRequest(Map<String, String> headers) {
        RequestSpecification request = RestAssured.given();
        headers.put("Content-Type", "application/json");
        request.headers(headers);
        request.relaxedHTTPSValidation();
        request.queryParams("path", "_cluster/health");
        request.queryParams("method", "GET");
        logger.info("Header came -" + headers);
        Response response = null;
        try {
            response = request.post();
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();

        }

        return response;
    }


}
