package com.jnetai.worldtimes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jnetai.worldtimes.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * UTC blocks fragment showing current time at every UTC offset (-12 to +12).
 */
public class UtcBlocksFragment extends Fragment {

    private LinearLayout utcContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_utc_blocks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utcContainer = view.findViewById(R.id.utcContainer);
        buildUtcBlocks();
    }

    private void buildUtcBlocks() {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        timeFormat.setTimeZone(utc);

        String[] weekDays = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

        LinearLayout currentRow = null;

        for (int i = -12; i <= 12; i++) {
            // New row every 5 items
            int idx = i + 12;
            if (idx % 5 == 0) {
                currentRow = new LinearLayout(requireContext());
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                utcContainer.addView(currentRow);
            }

            // Calculate time at this offset
            java.util.Calendar cal = java.util.Calendar.getInstance(utc);
            cal.add(java.util.Calendar.HOUR_OF_DAY, i);
            String timeStr = new SimpleDateFormat("HH:mm", Locale.US).format(cal.getTime());
            int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
            String dayStr = weekDays[dayOfWeek];

            String label = i > 0 ? "+" + i : String.valueOf(i);

            LinearLayout block = new LinearLayout(requireContext());
            block.setOrientation(LinearLayout.VERTICAL);
            block.setGravity(android.view.Gravity.CENTER);
            block.setLayoutParams(new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f
            ));

            if (i == 0) {
                block.setBackgroundResource(R.drawable.bg_utc_now);
            } else {
                block.setBackgroundResource(R.drawable.bg_utc_block);
            }

            TextView offsetText = new TextView(requireContext());
            offsetText.setText("UTC" + label);
            offsetText.setTextSize(9);
            offsetText.setTextColor(i == 0 ? 0xFF00D4FF : 0xFF60A5FA);
            offsetText.setGravity(android.view.Gravity.CENTER);

            TextView timeText = new TextView(requireContext());
            timeText.setText(timeStr);
            timeText.setTextSize(11);
            timeText.setTextColor(0xFFE0E8F0);
            timeText.setGravity(android.view.Gravity.CENTER);

            TextView dayText = new TextView(requireContext());
            dayText.setText(dayStr);
            dayText.setTextSize(8);
            dayText.setTextColor(0xFF94A3B8);
            dayText.setGravity(android.view.Gravity.CENTER);

            block.addView(offsetText);
            block.addView(timeText);
            block.addView(dayText);

            if (currentRow != null) currentRow.addView(block);
        }
    }
}
