package com.jnetai.worldtimes.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jnetai.worldtimes.R;
import com.jnetai.worldtimes.data.CityData;
import com.jnetai.worldtimes.data.CoordLookup;
import com.jnetai.worldtimes.utils.TimeUtils;

/**
 * Main clock fragment showing live digital clock, city grid, coordinate lookup.
 */
public class ClockFragment extends Fragment {

    private TextView clockTime;
    private TextView clockDate;
    private TextView gridHint;
    private EditText coordsInput;
    private TextView timeInfoText;
    private LinearLayout timeInfoCard;
    private LinearLayout cityGrid;
    private Button btnShowAll;
    private Button btnCoordFind;

    private final Handler clockHandler = new Handler(Looper.getMainLooper());
    private boolean clockRunning = true;

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

        // Show All Timezones button
        btnShowAll.setOnClickListener(v -> {
            cityGrid.setVisibility(View.VISIBLE);
            gridHint.setText(R.string.tap_city_hint);
        });

        // Coordinate lookup
        btnCoordFind.setOnClickListener(v -> doCoordLookup());
        coordsInput.setOnEditorActionListener((v, actionId, event) -> {
            doCoordLookup();
            return true;
        });

        // Build city grid initially
        buildCityGrid();
    }

    private void buildCityGrid() {
        // Region data
        String[] regionNames = {
                "NORTH AMERICA", "CENTRAL & SOUTH AMERICA", "EUROPE",
                "ASIA", "AFRICA", "AUSTRALIA & PACIFIC", "ATLANTIC, ARCTIC & ANTARCTICA"
        };
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
            regionLabel.setTextColor(0xFF00D4FF);
            regionLabel.setLetterSpacing(0.12f);
            regionLabel.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            ((LinearLayout.LayoutParams) regionLabel.getLayoutParams()).setMargins(0, 10, 0, 4);
            cityGrid.addView(regionLabel);

            // Cities in this region
            LinearLayout row = null;
            for (int c = 0; c < regionCities[r].length; c++) {
                // New row every 3 cities
                if (c % 3 == 0) {
                    row = new LinearLayout(requireContext());
                    row.setOrientation(LinearLayout.HORIZONTAL);
                    row.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    cityGrid.addView(row);
                }

                String cityName = regionCities[r][c];
                Button cityBtn = new Button(requireContext(), null, android.R.attr.buttonStyleSmall);
                cityBtn.setText(cityName);
                cityBtn.setTextSize(11);
                cityBtn.setTextColor(0xFFE0E8F0);
                cityBtn.setBackgroundResource(R.drawable.btn_city_grid);
                cityBtn.setPadding(6, 6, 6, 6);
                cityBtn.setSingleLine(true);
                cityBtn.setEllipsize(android.text.TextUtils.TruncateAt.END);
                cityBtn.setLayoutParams(new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
                ));

                CityData.City city = CityData.CITIES.get(cityName);
                if (city != null) {
                    cityBtn.setOnClickListener(v -> showCityTime(city));
                }

                if (row != null) row.addView(cityBtn);
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
            Toast.makeText(requireContext(), "Enter coordinates (lat, lon)", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] parts = raw.split(",");
        if (parts.length != 2) {
            Toast.makeText(requireContext(), "Invalid format. Use: lat, lon", Toast.LENGTH_SHORT).show();
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
                    "Nearest: " + nearest.displayName + "\n" +
                    "\uD83D\uDCCD " + nearest.displayName + ": " + cityTime + "\n" +
                    "Time Diff: " + TimeUtils.formatTimeDifference(diff)
                );
            }
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Invalid coordinates", Toast.LENGTH_SHORT).show();
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
