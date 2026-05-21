package com.jnetai.worldtimes.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * City data matching the web app: all cities with IANA timezone IDs.
 * Organized by region for display.
 */
public class CityData {

    // A city entry
    public static class City {
        public final String displayName;
        public final String timezoneId;
        public final double latitude;
        public final double longitude;

        public City(String displayName, String timezoneId, double lat, double lon) {
            this.displayName = displayName;
            this.timezoneId = timezoneId;
            this.latitude = lat;
            this.longitude = lon;
        }
    }

    // All cities as a list, organized by region
    public static final Map<String, City> CITIES = new LinkedHashMap<>();
    public static final String[] REGIONS = {
            "North America", "Central & South America", "Europe",
            "Asia", "Africa", "Australia & Pacific", "Atlantic, Arctic & Antarctica"
    };

    static {
        // -- North America --
        add("New York", "America/New_York", 40.7128, -74.0060);
        add("Chicago", "America/Chicago", 41.8781, -87.6298);
        add("Denver", "America/Denver", 39.7392, -104.9903);
        add("Phoenix", "America/Phoenix", 33.4484, -112.0740);
        add("Los Angeles", "America/Los_Angeles", 34.0522, -118.2437);
        add("Anchorage", "America/Anchorage", 61.0169, -149.7374);
        add("Honolulu", "Pacific/Honolulu", 21.3069, -157.8583);
        add("Toronto", "America/Toronto", 43.6511, -79.3830);
        add("Vancouver", "America/Vancouver", 49.2827, -123.1207);
        add("Montreal", "America/Montreal", 45.5017, -73.5673);
        add("Halifax", "America/Halifax", 44.6488, -63.5752);
        add("St. John's", "America/St_Johns", 47.5615, -52.7126);
        add("Mexico City", "America/Mexico_City", 19.4326, -99.1332);
        add("Guatemala", "America/Guatemala", 14.6349, -90.5069);
        add("Detroit", "America/Detroit", 42.3314, -83.0458);
        add("Atlanta", "America/New_York", 33.7490, -84.3880);
        add("Miami", "America/New_York", 25.7743, -80.1937);
        add("Dallas", "America/Chicago", 32.7767, -96.7970);
        add("Houston", "America/Chicago", 29.7604, -95.3698);
        add("Boise", "America/Boise", 43.6150, -116.2023);
        add("Juneau", "America/Juneau", 58.3019, -134.4197);
        add("Portland", "America/Los_Angeles", 45.5155, -122.6793);
        add("Seattle", "America/Los_Angeles", 47.6062, -122.3321);
        add("San Francisco", "America/Los_Angeles", 37.7749, -122.4194);
        add("Las Vegas", "America/Los_Angeles", 36.1699, -115.1398);
        add("Salt Lake City", "America/Denver", 40.7608, -111.8910);
        add("Minneapolis", "America/Chicago", 44.9778, -93.2650);
        add("Nashville", "America/Chicago", 36.1627, -86.7816);
        add("Boston", "America/New_York", 42.3601, -71.0589);
        add("Philadelphia", "America/New_York", 39.9526, -75.1652);
        add("Washington DC", "America/New_York", 38.9072, -77.0369);
        add("Charlotte", "America/New_York", 35.2271, -80.8431);
        add("Fairbanks", "America/Anchorage", 64.8378, -147.7164);
        add("Nome", "America/Nome", 64.5000, -165.4064);
        add("Adak", "America/Adak", 51.8803, -176.6580);

        // -- Central & South America --
        add("Bogot\u00e1", "America/Bogota", 4.7110, -74.0721);
        add("Lima", "America/Lima", -12.0464, -77.0428);
        add("Santiago", "America/Santiago", -33.4489, -70.6693);
        add("Buenos Aires", "America/Buenos_Aires", -34.6037, -58.3816);
        add("S\u00e3o Paulo", "America/Sao_Paulo", -23.5505, -46.6333);
        add("Rio Branco", "America/Rio_Branco", -9.9740, -67.8070);
        add("Caracas", "America/Caracas", 10.4806, -66.9036);
        add("La Paz", "America/La_Paz", -16.5000, -68.1500);
        add("Asunci\u00f3n", "America/Asuncion", -25.2637, -57.5759);
        add("Montevideo", "America/Montevideo", -34.9011, -56.1645);
        add("Guayaquil", "America/Guayaquil", -2.2037, -79.8972);
        add("Panama", "America/Panama", 8.9824, -79.5199);
        add("San Jos\u00e9", "America/Costa_Rica", 9.9281, -84.0907);
        add("San Salvador", "America/El_Salvador", 13.6894, -89.1872);
        add("Managua", "America/Managua", 12.1149, -86.2362);
        add("Tegucigalpa", "America/Tegucigalpa", 14.0818, -87.2068);
        add("Fernando de Noronha", "America/Noronha", -3.8538, -32.4233);
        add("Nuuk", "America/Nuuk", 64.1836, -51.7214);
        add("San Juan", "America/Puerto_Rico", 18.4655, -66.1057);
        add("Nassau", "America/Nassau", 25.0343, -77.3963);
        add("Havana", "America/Havana", 23.1136, -82.3666);
        add("Kingston", "America/Jamaica", 18.0179, -76.8099);

        // -- Europe --
        add("London", "Europe/London", 51.5074, -0.1278);
        add("Paris", "Europe/Paris", 48.8566, 2.3522);
        add("Berlin", "Europe/Berlin", 52.5200, 13.4050);
        add("Rome", "Europe/Rome", 41.9028, 12.4964);
        add("Madrid", "Europe/Madrid", 40.4168, -3.7038);
        add("Lisbon", "Europe/Lisbon", 38.7223, -9.1393);
        add("Amsterdam", "Europe/Amsterdam", 52.3676, 4.9041);
        add("Brussels", "Europe/Brussels", 50.8503, 4.3517);
        add("Zurich", "Europe/Zurich", 47.3769, 8.5417);
        add("Vienna", "Europe/Vienna", 48.2082, 16.3738);
        add("Prague", "Europe/Prague", 50.0755, 14.4378);
        add("Warsaw", "Europe/Warsaw", 52.2297, 21.0122);
        add("Budapest", "Europe/Budapest", 47.4979, 19.0402);
        add("Stockholm", "Europe/Stockholm", 59.3293, 18.0686);
        add("Copenhagen", "Europe/Copenhagen", 55.6761, 12.5683);
        add("Oslo", "Europe/Oslo", 59.9139, 10.7522);
        add("Helsinki", "Europe/Helsinki", 60.1699, 24.9384);
        add("Dublin", "Europe/Dublin", 53.3498, -6.2603);
        add("Athens", "Europe/Athens", 37.9838, 23.7275);
        add("Istanbul", "Europe/Istanbul", 41.0082, 28.9784);
        add("Moscow", "Europe/Moscow", 55.7558, 37.6173);
        add("Kyiv", "Europe/Kyiv", 50.4501, 30.5234);
        add("Minsk", "Europe/Minsk", 53.9045, 27.5615);
        add("Bucharest", "Europe/Bucharest", 44.4268, 26.1025);
        add("Sofia", "Europe/Sofia", 42.6977, 23.3219);
        add("Belgrade", "Europe/Belgrade", 44.7866, 20.4489);
        add("Zagreb", "Europe/Zagreb", 45.8150, 15.9819);
        add("Reykjavik", "Atlantic/Reykjavik", 64.1466, -21.9426);
        add("Gibraltar", "Europe/Gibraltar", 36.1408, -5.3536);
        add("Monaco", "Europe/Monaco", 43.7384, 7.4246);
        add("Vatican", "Europe/Vatican", 41.9029, 12.4534);
        add("San Marino", "Europe/San_Marino", 43.9424, 12.4568);
        add("Vaduz", "Europe/Vaduz", 47.1410, 9.5215);
        add("Luxembourg", "Europe/Luxembourg", 49.6117, 6.1301);
        add("Valletta", "Europe/Malta", 35.8997, 14.5147);
        add("Tallinn", "Europe/Tallinn", 59.4370, 24.7536);
        add("Riga", "Europe/Riga", 56.9496, 24.1052);
        add("Vilnius", "Europe/Vilnius", 54.6872, 25.2797);
        add("Kaliningrad", "Europe/Kaliningrad", 54.7104, 20.4522);
        add("Samara", "Europe/Samara", 53.1959, 50.1002);
        add("Volgograd", "Europe/Volgograd", 48.7080, 44.5133);

        // -- Asia --
        add("Dubai", "Asia/Dubai", 25.2048, 55.2708);
        add("Singapore", "Asia/Singapore", 1.3521, 103.8198);
        add("Tokyo", "Asia/Tokyo", 35.6762, 139.6503);
        add("Seoul", "Asia/Seoul", 37.5665, 126.9780);
        add("Shanghai", "Asia/Shanghai", 31.2304, 121.4737);
        add("Hong Kong", "Asia/Hong_Kong", 22.3193, 114.1694);
        add("Taipei", "Asia/Taipei", 25.0330, 121.5654);
        add("Bangkok", "Asia/Bangkok", 13.7563, 100.5018);
        add("Kuala Lumpur", "Asia/Kuala_Lumpur", 3.1390, 101.6869);
        add("Jakarta", "Asia/Jakarta", -6.2088, 106.8456);
        add("Manila", "Asia/Manila", 14.5995, 120.9842);
        add("Ho Chi Minh", "Asia/Ho_Chi_Minh", 10.8231, 106.6297);
        add("Yangon", "Asia/Yangon", 16.8661, 96.1951);
        add("Dhaka", "Asia/Dhaka", 23.8103, 90.4125);
        add("Kolkata", "Asia/Kolkata", 22.5726, 88.3639);
        add("Karachi", "Asia/Karachi", 24.8607, 67.0011);
        add("Kathmandu", "Asia/Kathmandu", 27.7172, 85.3240);
        add("Colombo", "Asia/Colombo", 6.9271, 79.8612);
        add("Tehran", "Asia/Tehran", 35.6892, 51.3890);
        add("Baghdad", "Asia/Baghdad", 33.3152, 44.3661);
        add("Riyadh", "Asia/Riyadh", 24.7136, 46.6753);
        add("Doha", "Asia/Qatar", 25.2854, 51.5310);
        add("Manama", "Asia/Bahrain", 26.2285, 50.5860);
        add("Kuwait", "Asia/Kuwait", 29.3697, 47.9783);
        add("Muscat", "Asia/Muscat", 23.5880, 58.3829);
        add("Aden", "Asia/Aden", 12.7855, 45.0187);
        add("Amman", "Asia/Amman", 31.9454, 35.9284);
        add("Beirut", "Asia/Beirut", 33.8938, 35.5018);
        add("Damascus", "Asia/Damascus", 33.5138, 36.2765);
        add("Jerusalem", "Asia/Jerusalem", 31.7683, 35.2137);
        add("Nicosia", "Asia/Nicosia", 35.1856, 33.3823);
        add("Tbilisi", "Asia/Tbilisi", 41.7151, 44.8271);
        add("Yerevan", "Asia/Yerevan", 40.1792, 44.4991);
        add("Baku", "Asia/Baku", 40.4093, 49.8671);
        add("Tashkent", "Asia/Tashkent", 41.2995, 69.2401);
        add("Almaty", "Asia/Almaty", 43.2220, 76.8512);
        add("Bishkek", "Asia/Bishkek", 42.8746, 74.5698);
        add("Dushanbe", "Asia/Dushanbe", 38.5598, 68.7870);
        add("Ashgabat", "Asia/Ashgabat", 37.9601, 58.3261);
        add("Kabul", "Asia/Kabul", 34.5553, 69.2075);
        add("Ulaanbaatar", "Asia/Ulaanbaatar", 47.9184, 106.9177);
        add("Vladivostok", "Asia/Vladivostok", 43.1155, 131.8855);
        add("Magadan", "Asia/Magadan", 59.5612, 150.8301);
        add("Kamchatka", "Asia/Kamchatka", 53.0473, 158.6477);
        add("Yakutsk", "Asia/Yakutsk", 62.0355, 129.6750);
        add("Krasnoyarsk", "Asia/Krasnoyarsk", 56.0153, 92.8932);
        add("Novosibirsk", "Asia/Novosibirsk", 55.0302, 82.9204);
        add("Yekaterinburg", "Asia/Yekaterinburg", 56.8389, 60.6057);

        // -- Africa --
        add("Cairo", "Africa/Cairo", 30.0444, 31.2357);
        add("Casablanca", "Africa/Casablanca", 33.5731, -7.5898);
        add("Johannesburg", "Africa/Johannesburg", -26.2041, 28.0473);
        add("Lagos", "Africa/Lagos", 6.5244, 3.3792);
        add("Nairobi", "Africa/Nairobi", -1.2921, 36.8219);
        add("Addis Ababa", "Africa/Addis_Ababa", 9.0320, 38.7469);
        add("Algiers", "Africa/Algiers", 36.7538, 3.0588);
        add("Tunis", "Africa/Tunis", 36.8065, 10.1815);
        add("Tripoli", "Africa/Tripoli", 32.8872, 13.1913);
        add("Khartoum", "Africa/Khartoum", 15.5007, 32.5599);
        add("Dakar", "Africa/Dakar", 14.7167, -17.4677);
        add("Accra", "Africa/Accra", 5.6037, -0.1870);
        add("Abidjan", "Africa/Abidjan", 5.3600, -4.0083);
        add("Bamako", "Africa/Bamako", 12.6392, -8.0029);
        add("Ouagadougou", "Africa/Ouagadougou", 12.3714, -1.5197);
        add("Niamey", "Africa/Niamey", 13.5137, 2.1098);
        add("N'Djamena", "Africa/Ndjamena", 12.1348, 15.0557);
        add("Luanda", "Africa/Luanda", -8.8390, 13.2894);
        add("Harare", "Africa/Harare", -17.8252, 31.0335);
        add("Lusaka", "Africa/Lusaka", -15.3875, 28.3228);
        add("Maputo", "Africa/Maputo", -25.9692, 32.5732);
        add("Kampala", "Africa/Kampala", 0.3476, 32.5825);
        add("Dar es Salaam", "Africa/Dar_es_Salaam", -6.7924, 39.2083);
        add("Mauritius", "Indian/Mauritius", -20.1609, 57.5012);
        add("Antananarivo", "Indian/Antananarivo", -18.8792, 47.5079);

        // -- Australia & Pacific --
        add("Sydney", "Australia/Sydney", -33.8688, 151.2093);
        add("Melbourne", "Australia/Melbourne", -37.8136, 144.9631);
        add("Brisbane", "Australia/Brisbane", -27.4698, 153.0251);
        add("Perth", "Australia/Perth", -31.9505, 115.8605);
        add("Adelaide", "Australia/Adelaide", -34.9285, 138.6007);
        add("Darwin", "Australia/Darwin", -12.4634, 130.8456);
        add("Hobart", "Australia/Hobart", -42.8821, 147.3272);
        add("Canberra", "Australia/Canberra", -35.2809, 149.1300);
        add("Auckland", "Pacific/Auckland", -36.8485, 174.7633);
        add("Chatham", "Pacific/Chatham", -43.9561, -176.5590);
        add("Fiji", "Pacific/Fiji", -18.1237, 178.4623);
        add("Guam", "Pacific/Guam", 13.4443, 144.7937);
        add("Port Moresby", "Pacific/Port_Moresby", -9.4438, 147.1803);
        add("Noum\u00e9a", "Pacific/Noumea", -22.2718, 166.4422);
        add("Port Vila", "Pacific/Efate", -17.7427, 168.3157);
        add("Apia", "Pacific/Apia", -13.8333, -171.7667);
        add("Tonga", "Pacific/Tongatapu", -21.1386, -175.1998);
        add("Tahiti", "Pacific/Tahiti", -17.6509, -149.4260);
        add("Marquesas", "Pacific/Marquesas", -9.7798, -139.0173);
        add("Easter Island", "Pacific/Easter", -27.1128, -109.3497);
        add("Gal\u00e1pagos", "Pacific/Galapagos", -0.5381, -91.1229);
        add("Kiritimati", "Pacific/Kiritimati", 1.8833, -157.4000);
        add("Norfolk Island", "Pacific/Norfolk", -29.0400, 167.9600);
        add("Palau", "Pacific/Palau", 7.5150, 134.5825);
        add("Nauru", "Pacific/Nauru", -0.5228, 166.9315);

        // -- Atlantic, Arctic & Antarctica --
        add("Azores", "Atlantic/Azores", 37.7412, -25.6756);
        add("Madeira", "Atlantic/Madeira", 32.7607, -16.9595);
        add("Canary Islands", "Atlantic/Canary", 28.2916, -16.6291);
        add("Cape Verde", "Atlantic/Cape_Verde", 14.9331, -23.5133);
        add("Bermuda", "Atlantic/Bermuda", 32.2995, -64.7903);
        add("Falkland Islands", "Atlantic/Stanley", -51.7010, -57.8498);
        add("South Georgia", "Atlantic/South_Georgia", -54.3833, -36.6667);
        add("Svalbard", "Arctic/Longyearbyen", 78.2232, 15.6467);
        add("McMurdo", "Antarctica/McMurdo", -77.8419, 166.6864);
        add("Palmer", "Antarctica/Palmer", -64.7747, -64.0500);
        add("Troll", "Antarctica/Troll", -72.0131, 2.5333);
    }

    private static void add(String name, String tz, double lat, double lon) {
        CITIES.put(name, new City(name, tz, lat, lon));
    }
}
