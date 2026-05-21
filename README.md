# World Times  - World Clock Android App

A native Android world clock app inspired by [jnetai.com/apps/World-Times/](https://jnetai.com/apps/World-Times/index.html).

## Features

- **Live Digital Clock** — HH:MM:SS with current date and day displayed at the top
- **City Grid** — 199+ cities organized by region (North America, South America, Europe, Asia, Africa, Australia/Pacific, Atlantic/Arctic/Antarctica)
- **Tap any city** to see its local time and time difference from your location
- **UTC Offset Blocks** — See the current time at every UTC offset from -12 to +12
- **Search** — Filter cities by name or timezone with live results
- **Coordinate Lookup** — Enter lat/lon to find the nearest city's timezone
- **Dark Theme** — Matching the web app's gradient color scheme (#0f1923 → #1a2a3a → #0d1b2a)
- **Works Offline** — Uses device's built-in timezone database

## Screenshots

| Clock | UTC Blocks | Search | About |
|-------|-----------|--------|-------|
| (screenshots) | | | |

## Build

```bash
./gradlew assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## Release

Tag a version to trigger GitHub Actions CI:

```bash
git tag v1.0.1
git push origin v1.0.1
```

## Tech

- **Language:** Java
- **Min SDK:** 24
- **Target SDK:** 34
- **Architecture:** Single-activity with fragments + bottom navigation
- **CI:** GitHub Actions builds APK on every push and creates releases on tags

## Data

All city coordinates and timezone IDs are embedded in the app — no network required. Uses the IANA timezone database available on all Android devices.

---

Made by [jnetai.com](https://jnetai.com)
