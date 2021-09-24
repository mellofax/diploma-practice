package ForAPI.Pages;

import ForAPI.QaseIO.Milestone;
import ForAPI.QaseIO.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class MilestonePage extends ProjectPage{
    private Milestone milestone;

    public MilestonePage(Project project, Milestone milestone) {
        super(project);
        this.milestone = milestone;
    }
    @Override
    public void Create() {
        given().baseUri(BASE_URI).header("Token", TOKEN).when().body(milestone).post("milestone/" + project.getCode()).then().statusCode(200);
    }

    @Override
    public Response GetAll() {
        Response response = given().baseUri(BASE_URI).header("Token", TOKEN).when().get("milestone/" + project.getCode()).then().statusCode(200).contentType(ContentType.JSON).extract().response();
        return response;
    }

    public Response DeleteSuite() {
        given().baseUri(BASE_URI).header("Token", TOKEN).when().delete("milestone/" + project.getCode() +"/"+ 1).then().statusCode(200);
        return GetAll();
    }
}
