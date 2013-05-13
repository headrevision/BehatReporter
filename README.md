BehatReporter
=============

BehatReporter is a native Android app for viewing [Behat](https://github.com/Behat/Behat)'s output on your mobile or tablet.

What do you need in order to use it?
------------------------------------

- Behat 2.4+ with the [CommonFormatters extension](https://github.com/Behat/CommonFormatters/blob/master/doc/index.rst) installed
- A web server for delivering JSON files via HTTP
- The [Android SDK](http://developer.android.com/sdk/index.html) with the API version 17 installed
- An Android device with Android 3.0+ ("Honeycomb")

How to use it?
--------------

1. Run Behat as usual - but choose the [JsonFormatter](https://github.com/Behat/CommonFormatters/blob/master/src/Behat/CommonFormatters/JsonFormatter.php) this time: `$> bin/behat --format json > output.json`
2. Put the file `output.json` on your web server
3. Clone this repository
4. Follow the [instructions for compiling & signing an Android app](http://developer.android.com/tools/publishing/app-signing.html#releasemode)
5. Install the signed APK on your Android device
6. Start the app and enter the URL of the JSON file, eg. `http://some_local_host/output.json`
7. Enjoy the experience, you deserved it :)
