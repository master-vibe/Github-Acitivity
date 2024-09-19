import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.JSONArray;
import org.json.JSONObject;

public class github_activity {

   public static void main(String[] args) {
      // Check if arguments are provided
      if (args.length == 0 || args[0].equals("--help") || args[0].startsWith("-")) {
         printHelp();
         return;
      }

      String username = args[0];
      String apiURI = "https://api.github.com/users/" + username + "/events";
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURI)).GET().build();

      try {
         HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

         if (response.statusCode() >= 200 && response.statusCode() < 300) {
            JSONArray events = new JSONArray(response.body());

            if (events.length() == 0) {
               System.out.println("No recent events found for user: " + username);
            } else {
               for (int i = 0; i < events.length(); i++) {
                  JSONObject eventObject = events.getJSONObject(i);
                  String eventType = eventObject.getString("type");
                  String dateTime = eventObject.getString("created_at");
                  String repoName = eventObject.getJSONObject("repo").getString("name");

                  // Display event details based on event type
                  switch (eventType) {
                     case "CreateEvent":
                        System.out.println("Created a new Repository: " + repoName + " at: " + dateTime);
                        break;
                     case "PushEvent":
                        int commitCount = eventObject.getJSONObject("payload").getInt("size");
                        System.out.println("Pushed " + commitCount + " commits to " + repoName + " at: " + dateTime);
                        break;
                     case "DeleteEvent":
                        System.out.println("Deleted a Repository: " + repoName + " at: " + dateTime);
                        break;
                     default:
                        System.out.println("Performed an event of type: " + eventType + " on repository: " + repoName
                              + " at: " + dateTime);
                        break;
                  }
               }
            }
         } else {
            System.out.println("Failed to fetch events. GitHub API returned status code: " + response.statusCode() +"for user: "+username);
         }
      } catch (IOException | InterruptedException e) {
         System.out.println("An error occurred while fetching events for user: " + username);
         e.printStackTrace();
      }
   }

   private static void printHelp() {
      System.out.println("GitHub Activity Command-Line Tool");
      System.out.println("Usage:");
      System.out.println("  github-activity <username>");
      System.out.println("Options:");
      System.out.println("  --help         Display this help message");
      System.out.println("    Usage: github-activity --help");
      System.out.println("Example:");
      System.out.println("  github-activity master-vibe");
   }
}
