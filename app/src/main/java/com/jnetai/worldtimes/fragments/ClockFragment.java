package com.jnetai.worldtimes.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import com.jnetai.worldtimes.R;
import com.jnetai.worldtimes.data.CityData;
import com.jnetai.worldtimes.data.CoordLookup;
import com.jnetai.worldtimes.utils.TimeUtils;

import java.util.Locale;

/**
 * Main clock fragment showing live digital clock, city grid, coordinate lookup.
 * Mirrors the web app's functionality.
 */
public class ClockFragment extends Fragment {

    private TextView clockTime;
    private TextView clockDate;
    private TextView gridHint;
    private EditText coordsInput;
    private LinearLayout timeInfoCard;
    private TextView timeInfoText;
    private GridLayout cityGrid;
    private Button btnShowAll;
    private Button btnCoordFind;

    private final Handler clockHandler = new Handler(Looper.getMainLooper());
    private boolean clockRunning = true;
    private boolean gridVisible = false;

    private final Runnable clockRunnable = new Runnable() {
        @Override
        public void run() {
            if (clockRunning && isAdded()) {
                clockTime.setText(TimeUtils.getCurrentTimeString());
                clockDate.setText(TimeUtils.getCurrentDateString());
                clockHandler.postDelayed(this, 1000);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clockTime = view.findViewById(R.id.clockTime);
        clockDate = view.findViewById(R.id.clockDate);
        gridHint = view.findViewById(R.id.gridHint);
        coordsInput = view.findViewById(R.id.coordsInput);
        timeInfoCard = view.findViewById(R.id.timeInfoCard);
        timeInfoText = view.findViewById(R.id.timeInfoText);
        cityGrid = view.findViewById(R.id.cityGrid);
        btnShowAll = view.findViewById(R.id.btnShowAll);
        btnCoordFind = view.findViewById(R.id.btnCoordFind);

        // Start live clock
        clockHandler.post(clockRunnable);

        // Build city grid
        buildCityGrid();

        // Show All Timezones button
        btnShowAll.setOnClickListener(v -> {
            gridVisible = true;
            cityGrid.setVisibility(View.VISIBLE);
            gridHint.setText(R.string.tap_city_hint);
        });

        // Coordinate lookup
        btnCoordFind.setOnClickListener(v -> doCoordLookup());
        coordsInput.setOnEditorActionListener((v, actionId, event) -> {
            doCoordLookup();
            return true;
        });
    }

    private void buildCityGrid() {
        // Build grid columns based on width: 3-7 columns responsive
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int dpWidth = (int) (screenWidth / getResources().getDisplayMetrics().density);
        int columns;
        if (dpWidth >= 820) columns = 7;
        else if (dpWidth >= 640) columns = 6;
        else if (dpWidth >= 500) columns = 5;
        else if (dpWidth >= 400) columns = 4;
        else if (dpWidth >= 340) columns = 3;
        else columns = 2;

        cityGrid.setColumnCount(columns);

        String[] regionNames = {
                "North America", "Central & South America", "Europe",
                "Asia", "Africa", "Australia & Pacific", "Atlantic, Arctic & Antarctica"
        };

        // Define which cities belong to which region (maintain web app order)
        String[][] regionCities = {
                {"New York","Chicago","Denver","Phoenix","Los Angeles","Anchorage","Honolulu",
                        "Toronto","Vancouver","Montreal","Halifax","St. John's","Mexico City",
                        "Guatemala","Detroit","Atlanta","Miami","Dallas","Houston","Boise",
                        "Juneau","Portland","Seattle","San Francisco","Las Vegas","Salt Lake City",
                        "Minneapolis","Nashville","Boston","Philadelphia","Washington DC",
                        "Charlotte","Fairbanks","Nome","Adak"},
                {"Bogot\u00e1","Lima","Santiago","Buenos Aires","S\u00e3o Paulo","Rio Branco",
                        "Caracas","La Paz","Asunci\u00f3n","Montevideo","Guayaquil","Panama",
                        "San Jos\u00e9","San Salvador","Managua","Tegucigalpa","Fernando de Noronha",
                        "Nuuk","San Juan","Nassau","Havana","Kingston"},
                {"London","Paris","Berlin","Rome","Madrid","Lisbon","Amsterdam","Brussels",
                        "Zurich","Vienna","Prague","Warsaw","Budapest","Stockholm","Copenhagen",
                        "Oslo","Helsinki","Dublin","Athens","Istanbul","Moscow","Kyiv","Minsk",
                        "Bucharest","Sofia","Belgrade","Zagreb","Reykjavik","Gibraltar","Monaco",
                        "Vatican","San Marino","Vaduz","Luxembourg","Valletta","Tallinn","Riga",
                        "Vilnius","Kaliningrad","Samara","Volgograd"},
                {"Dubai","Singapore","Tokyo","Seoul","Shanghai","Hong Kong","Taipei","Bangkok",
                        "Kuala Lumpur","Jakarta","Manila","Ho Chi Minh","Yangon","Dhaka","Kolkata",
                        "Karachi","Kathmandu","Colombo","Tehran","Baghdad","Riyadh","Doha","Manama",
                        "Kuwait","Muscat","Aden","Amman","Beirut","Damascus","Jerusalem","Nicosia",
                        "Tbilisi","Yerevan","Baku","Tashkent","Almaty","Bishkek","Dushanbe","Ashgabat",
                        "Kabul","Ulaanbaatar","Vladivostok","Magadan","Kamchatka","Yakutsk","Krasnoyarsk",
                        "Novosibirsk","Yekaterinburg"},
                {"Cairo","Casablanca","Johannesburg","Lagos","Nairobi","Addis Ababa","Algiers",
                        "Tunis","Tripoli","Khartoum","Dakar","Accra","Abidjan","Bamako","Ouagadougou",
                        "Niamey","N'Djamena","Luanda","Harare","Lusaka","Maputo","Kampala",
                        "Dar es Salaam","Mauritius","Antananarivo"},
                {"Sydney","Melbourne","Brisbane","Perth","Adelaide","Darwin","Hobart","Canberra",
                        "Auckland","Chatham","Fiji","Guam","Port Moresby","Noum\u00e9a","Port Vila",
                        "Apia","Tonga","Tahiti","Marquesas","Easter Island","Gal\u00e1pagos",
                        "Kiritimati","Norfolk Island","Palau","Nauru"},
                {"Azores","Madeira","Canary Islands","Cape Verde","Bermuda","Falkland Islands",
                        "South Georgia","Svalbard","McMurdo","Palmer","Troll"}
        };

        for (int r = 0; r < regionNames.length; r++) {
            // Region label
            TextView regionLabel = new TextView(requireContext());
            regionLabel.setText(regionNames[r]);
            regionLabel.setTextSize(10);
            regionLabel.setTextColor(getResources().getColor(R.color.accent_blue, null));
            regionLabel.setLetterSpacing(0.12f);
            regionLabel.setAllCaps(true);
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(0, columns);
            lp.width = GridLayout.LayoutParams.MATCH_PARENT;
            lp.setMargins(0, 10, 0, 4);
            cityGrid.addView(regionLabel, lp);

            // City buttons for this region
            for (String cityName : regionCities[r]) {
                Button cityBtn = new Button(requireContext(), null, android.R.attr.buttonStyleSmall);
                cityBtn.setText(cityName);
                cityBtn.setTextSize(11);
                cityBtn.setTextColor(getResources().getColor(R.color.text_primary, null));
                cityBtn.setBackgroundResource(R.drawable.btn_city_grid);
                cityBtn.setPadding(4, 6, 4, 6);
                cityBtn.setSingleLine(true);
                cityBtn.setEllipsize(android.text.TextUtils.TruncateAt.END);

                // Set click handler - look up the timezone for this city
                CityData.City city = CityData.CITIES.get(cityName);
                if (city != null) {
                    cityBtn.setOnClickListener(v -> showCityTime(city));
                }

                cityGrid.addView(cityBtn);
            }
        }
    }

    private void showCityTime(CityData.City city) {
        String cityTime = TimeUtils.getTimeInZone(city.timezoneId);
        float diff = TimeUtils.getTimeDifferenceHours(city.timezoneId);
        String diffStr = TimeUtils.formatTimeDifference(diff);

        timeInfoCard.setVisibility(View.VISIBLE);
        timeInfoText.setText(
                "Your Local: " + TimeUtils.getCurrentDateString() + ", " + TimeUtils.getCurrentTimeString() + "\n" +
                "\uD83D\uDCCD " + city.displayName + ": " + cityTime + "\n" +
                "Time Diff: " + diffStr
        );
    }

    private void doCoordLookup() {
        String raw = coordsInput.getText().toString().trim();
        if (raw.isEmpty()) {
            Toast.makeText(requireContext(), R.string.coords_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String[] parts = raw.split(",");
        if (parts.length != 2) {
            Toast.makeText(requireContext(), R.string.coords_error, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double lat = Double.parseDouble(parts[0].trim());
            double lon = Double.parseDouble(parts[1].trim());

            CityData.City nearest = CoordLookup.findNearestCity(lat, lon);
            if (nearest != null) {
                String cityTime = TimeUtils.getTimeInZone(nearest.timezoneId);
                float diff = TimeUtils.getTimeDifferenceHours(nearest.timezoneId);
                timeInfoCard.setVisibility(View.VISIBLE);
                timeInfoText.setText(
                        "Nearest city: " + nearest.displayName + "\n" +
                        "\uD83D\uDCCD " + nearest.displayName + ": " + cityTime + "\n" +
                        "Time Diff: " + TimeUtils.formatTimeDifference(diff)
                );
            }
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), R.string.coords_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        clockRunning = true;
        clockHandler.post(clockRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        clockRunning = false;
        clockHandler.removeCallbacks(clockRunnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clockRunning = false;
        clockHandler.removeCallbacks(clockRunnable);
    }
}
