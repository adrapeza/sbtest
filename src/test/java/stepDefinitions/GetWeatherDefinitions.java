package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.mkfl3x.jsondelta.JsonDeltaReport;
import ru.sber.core.task2.api.API;
import ru.sber.core.utils.FileUtils;
import ru.sber.core.utils.JsonComparator;
import ru.sber.core.utils.ResourcesUtils;

import java.util.ArrayList;
import java.util.List;

import static ru.sber.core.contants.Constants.LOG_FILE;


public class GetWeatherDefinitions {

    private SoftAssertions sa = new SoftAssertions();
    static String logFile = "";

    @BeforeAll
    public static void beforeALl() {
        logFile += LOG_FILE + "_" + System.currentTimeMillis();
    }

    @After
    public void after(Scenario scenario) {
        StringBuilder sb = new StringBuilder();
        if (!sa.errorsCollected().isEmpty()) {
            sb.append("====== Scenario '" + scenario.getName() + "' ====== \n");
            sa.errorsCollected().forEach(i -> sb.append(i + "\n"));
            sb.append("==================================================\n\n");
        }
        FileUtils.printToFile(logFile, sb.toString());
        sa.assertAll();

    }

    private Response response;
    List<Response> responseList = new ArrayList<>();

    @When("send bad request to get weather information")
    public void send_bad_request_to_get_weather(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> columns = rows.get(i);
            Response response = API.weather().getCurrent(columns.get(0), columns.get(1), columns.get(2));
            responseList.add(response);
        }
    }

    @Then("response status code should be \"200\"")
    public void responseHasFollowingResponseCode(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> columns = rows.get(i);
            sa.assertThat(responseList.get(i - 1).getStatusCode()).isEqualTo(columns.get(0));
        }
    }

    @When("send request to get weather information")
    public void send_request_to_get_weather_information(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> columns = rows.get(i);
            response = API.weather().getCurrent(columns.get(0));
            responseList.add(response);
        }
    }

    @Then("response should includes the following expected data")
    public void response_should_includes_the_following_expected_data(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> columns = rows.get(i);
            JSONObject json = new JSONObject(responseList.get(i - 1).asString());
            String name = json.getJSONObject("location").getString("name");
            String location = json.getJSONObject("location").getString("country");
            Double tempC = json.getJSONObject("current").getDouble("temp_c");
            String text = json.getJSONObject("current").getJSONObject("condition").getString("text");
            sa.assertThat(columns.get(0)).isEqualTo(name);
            sa.assertThat(columns.get(1)).isEqualTo(location);
            sa.assertThat(columns.get(2)).isEqualTo(tempC);
            sa.assertThat(columns.get(3)).isEqualTo(text);
        }
    }

    @Then("response should includes the following expected json data")
    public void response_should_includes_the_following_expected_json_data(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            JSONObject json = new JSONObject(responseList.get(i - 1).asString());
            List<String> columns = rows.get(i);
            String expectedJson = ResourcesUtils.getFileContentAsString("jsons/" + columns.get(0));
            JsonDeltaReport result = JsonComparator.compare(expectedJson, json.toString());
            sa.assertThat(result).isEqualTo(true);
        }
    }

    @Then("response has following error code and message")
    public void response_error_code_should_be_and_message(DataTable dt) {
        List<List<String>> rows = dt.asLists(String.class);
        for (int i = 1; i < rows.size(); i++) {
            List<String> columns = rows.get(i);
            JSONObject json = new JSONObject(responseList.get(i - 1).asString());
            String code = json.getJSONObject("error").getInt("code") + "";
            String message = json.getJSONObject("error").getString("message");
            sa.assertThat(columns.get(0)).isEqualTo(code);
            sa.assertThat(columns.get(1)).isEqualTo(message);
        }
    }


}
