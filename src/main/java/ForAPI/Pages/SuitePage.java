package ForAPI.Pages;

import ForAPI.QaseIO.Project;
import ForAPI.QaseIO.Suite;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SuitePage extends ProjectPage{

    private Suite suite;

    public SuitePage(Project project, Suite suite) {
        super(project);
        this.suite = suite;
    }

    @Override
    public void Create() {
        given().baseUri(BASE_URI).header("Token", TOKEN).when().body(suite).post("suite/" + project.getCode()).then().statusCode(200);
    }

    @Override
    public Response GetAll() {
        Response response = given().baseUri(BASE_URI).header("Token", TOKEN).when().get("suite/" + project.getCode()).then().statusCode(200).contentType(ContentType.JSON).extract().response();
        return response;
    }

    public Response DeleteSuite() {
        given().baseUri(BASE_URI).header("Token", TOKEN).when().delete("suite/" + project.getCode() +"/"+ suite.getParent_id()).then().statusCode(200);
        return GetAll();
    }
}
