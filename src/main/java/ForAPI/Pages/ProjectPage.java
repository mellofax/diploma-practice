package ForAPI.Pages;
import ForAPI.QaseIO.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProjectPage {

    protected String BASE_URI = "https://api.qase.io/v1";
    protected String TOKEN = "bc16b53c3c1747ac0c23b3041f2f670d2fd08b20";
    protected  Project project;

    public ProjectPage(Project project) {
        this.project = project;
    }

    public void Create() {
        given().baseUri(BASE_URI).header("Token", TOKEN).when().body(project).post("project").then().statusCode(200);
    }

    public Response GetAll() {
        Response response = given().baseUri(BASE_URI).header("Token", TOKEN).when().get("project").then().statusCode(200).contentType(ContentType.JSON).extract().response();
        return response;
    }
}
