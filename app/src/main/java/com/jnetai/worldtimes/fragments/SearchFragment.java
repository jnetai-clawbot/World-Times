package com.jnetai.worldtimes.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jnetai.worldtimes.R;
import com.jnetai.worldtimes.data.CityData;
import com.jnetai.worldtimes.utils.TimeUtils;

/**
 * Search fragment with city filter, matching the web app's live search.
 */
public class SearchFragment extends Fragment {

    private EditText searchInput;
    private TextView searchStatus;
    private LinearLayout searchResults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchInput = view.findViewById(R.id.searchInput);
        searchStatus = view.findViewById(R.id.searchStatus);
        searchResults = view.findViewById(R.id.searchResults);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCities(s.toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterCities(String query) {
        searchResults.removeAllViews();

        if (query.isEmpty()) {
            searchStatus.setText("");
            return;
        }

        int matchedCount = 0;
        for (CityData.City city : CityData.CITIES.values()) {
            String name = city.displayName.toLowerCase();
            if (name.contains(query) || city.timezoneId.toLowerCase().contains(query)) {
                Button cityBtn = new Button(requireContext(), null, android.R.attr.buttonStyleSmall);
                cityBtn.setText(city.displayName);
                cityBtn.setTextSize(11);
                cityBtn.setTextColor(0xFFE0E8F0);
                cityBtn.setBackgroundResource(R.drawable.btn_city_grid);
                cityBtn.setPadding(8, 6, 8, 6);
                cityBtn.setSingleLine(true);
                cityBtn.setEllipsize(android.text.TextUtils.TruncateAt.END);
                cityBtn.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ));

                cityBtn.setOnClickListener(v -> {
                    String cityTime = TimeUtils.getTimeInZone(city.timezoneId);
                    float diff = TimeUtils.getTimeDifferenceHours(city.timezoneId);
                    searchStatus.setText(city.displayName + ": " + cityTime + " (" + TimeUtils.formatTimeDifference(diff) + ")");
                    searchStatus.setTextColor(0xFF00D4FF);
                });

                searchResults.addView(cityBtn);
                matchedCount++;
            }
        }

        if (matchedCount > 0) {
            searchStatus.setText(matchedCount + " cities match \"" + searchInput.getText().toString().trim() + "\"");
            searchStatus.setTextColor(0xFF00D4FF);
        } else {
            searchStatus.setText("No matching cities found");
            searchStatus.setTextColor(0xFFF44336);
        }
    }
}
