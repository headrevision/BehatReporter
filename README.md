BehatReporter
=============

BehatReporter is a native Android app for viewing the output of the [Behat](https://github.com/Behat/Behat) command line tool on your mobile device.

Why this app was developed?
---------------------------
Besides the fact that the app IS still in development, it was initially developed to enable Behat users (=you) to present the results of a Behat run in a form as accessible as possible to the non-developer stakeholders in the [BDD process](http://www.ymc.ch/en/behavior-driven-development-with-behat-co-more-than-just-testing). Have a look yourself:

![Dialog](https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_dialog.png "Dialog")

![Features](https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_features.png "Features")

![Scenarios](https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_scenarios.png "Scenarios")

![Background](https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_background.png "Background")

![Outline](https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_outline.png "Outline")

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

License
-------

See [LICENSE](https://github.com/headrevision/BehatReporter/raw/master/LICENSE)

Behat was created by [Konstantin Kudryashov](https://github.com/everzet), see [LICENSE](https://github.com/Behat/Behat/raw/master/LICENSE) 
