package API;

import ForAPI.Pages.MilestonePage;
import ForAPI.Pages.ProjectPage;
import ForAPI.Pages.SuitePage;
import ForAPI.QaseIO.Milestone;
import ForAPI.QaseIO.Project;
import ForAPI.QaseIO.Suite;
import ForUI.Static.Log;
import jdk.jfr.Description;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Tests {

    private Project project;
    private Suite suite;
    private Milestone milestone;

    private ProjectPage projectPage;
    private SuitePage suitePage;
    private MilestonePage milestonePage;

    @BeforeGroups(groups = "ApiTESTS")
    public void SetUp() {
        project = new Project("some", "true");
        suite = new Suite("Name", 1, "des", "prec");
        milestone = new Milestone("milestone", "description");
        Log.getLogger().info("============================API=TESTS=STARTING===========================");
    }

    @Test(groups = "ApiTESTS", priority = 1)
    @Description("Create project")
    public void CreateProjectTest() {
        projectPage = new ProjectPage(project);
        projectPage.Create();
    }

    @Test(groups = "ApiTESTS", priority = 2)
    @Description("Get project")
    public void GetProjectTest() {
        Assert.assertTrue(projectPage.GetAll().asString().contains(project.getTitle()));
        Log.getLogger().info("Project " + project.getTitle() + " successfully created!");
    }

    @Test(groups = "ApiTESTS", priority = 3)
    @Description("Create suite")
    public void CreateSuiteTest() {
        suitePage = new SuitePage(project, suite);
        suitePage.Create();
    }

    @Test(groups = "ApiTESTS", priority = 4)
    @Description("Get suite")
    public void GetSuiteTest() {
        Assert.assertTrue(suitePage.GetAll().asString().contains(suite.getTitle()));
        Log.getLogger().info("Suite " + suite.getTitle() + " successfully created!");
    }

    @Test(groups = "ApiTESTS", priority = 5)
    @Description("Delete suite")
    public void DeleteSuiteTest() {
        Assert.assertFalse(suitePage.DeleteSuite().asString().contains(suite.getTitle()));
        Log.getLogger().info("Suite " + suite.getTitle() + " successfully deleted!");
    }

    @Test(groups = "ApiTESTS", priority = 6)
    @Description("Create milestone ")
    public void CreateMilestoneTest() {
        milestonePage = new MilestonePage(project, milestone);
        milestonePage.Create();
    }

    @Test(groups = "ApiTESTS", priority = 7)
    @Description("Get milestone")
    public void GetMilestoneTest() {
        Assert.assertTrue(milestonePage.GetAll().asString().contains(milestone.getTitle()));
        Log.getLogger().info("Suite " + milestone.getTitle() + " successfully created!");
    }

    @Test(groups = "ApiTESTS", priority = 8)
    @Description("Delete milestone")
    public void DeleteMilestoneTest() {
        Assert.assertFalse(milestonePage.DeleteSuite().asString().contains(milestone.getTitle()));
        Log.getLogger().info("Suite " + milestone.getTitle() + " successfully deleted!");
    }

    @AfterGroups(groups = "ApiTESTS")
    public void After() {
        Log.getLogger().info("=========================================================================");
    }
}
