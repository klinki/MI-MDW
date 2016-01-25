import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class Transformer {
    private class Event
    {
        public String name;
        public String summary;
        public Date when;
        public String locationStreet;
        public String locationCity;
        public String locationZIP;
    }

    private Event readInput(String input) throws Exception {
        Event event = new Event();

        StringTokenizer tokenizer = new StringTokenizer(input);

        this.readToken("-Event", false, tokenizer, null);
        event.name = this.readToken("--name", true, tokenizer, "\n");
        String date = this.readToken("--start", true, tokenizer, "\n");
        String location = this.readToken("--location", true, tokenizer, "\n");
        event.summary = this.readToken("--sumary", true, tokenizer, "\n");

        String[] locationSplitted = location.split(", ");
        event.locationStreet = locationSplitted[0];
        event.locationCity = locationSplitted[2];
        event.locationZIP = locationSplitted[1];

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        event.when = dateFormat.parse(date.replaceAll("(?:st|nd|rd|th)", ""));

        this.readToken("-", false, tokenizer, null);

        return event;
    }

    public String readToken(String name, boolean hasValue, StringTokenizer tokenizer, String delimiter) throws Exception {
        String token = tokenizer.nextToken();

        if (token.equals(name)) {
            if (hasValue) {
                return tokenizer.nextToken(delimiter);
            }
        } else {
            throw new Exception("Expected token: " + name);
        }

        return token;
    }

    public String transform(String input) throws Exception {
        Event event = this.readInput(input);
        return this.manuallyFormatOutput(event);
    }

    // Pokud muzeme pouzit externi knihovny, uprednostnil bych pouziti JSONObjectu
    private String formatOutput(Event event) {
        JSONObject location = new JSONObject();
        location.put("street", event.locationStreet);
        location.put("city", event.locationCity);
        location.put("zip", event.locationZIP);

        JSONObject jsonEvent = new JSONObject();
        jsonEvent.put("what", event.name + " - " + event.summary);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy");

        jsonEvent.put("when", dateFormat.format(event.when));
        jsonEvent.put("where", location);

        return jsonEvent.toJSONString();
    }

    // Pokud ne, pouziji rucni metodu
    private String manuallyFormatOutput(Event event) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.M.yyyy");

        return String.format("{\n" +
                "  \"what\": \"%s\",\n" +
                "  \"when\": \"%s\",\n" +
                "  \"where\": {\n" +
                "    \"street\": \"%s\",\n" +
                "    \"city\": \"%s\",\n" +
                "    \"zip\": \"%s\"\n" +
                "  }\n" +
                "}",
                    event.name + " - " + event.summary,
                    dateFormat.format(event.when),
                    event.locationStreet,
                    event.locationCity,
                    event.locationZIP
                );
    }

    public static void main(String[] args) {
        Transformer transformer = new Transformer();
        try {
        System.out.println(transformer.transform("-Event\n" +
                "--name\n" +
                "Meeting in Prague\n" +
                "--start\n" +
                "19th January 2015\n" +
                "--location\n" +
                "Thákurova 9, 160 00, Prague 6\n" +
                "--sumary\n" +
                "Meeting with partners\n" +
                "-"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
