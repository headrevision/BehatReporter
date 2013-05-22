BehatReporter
=============

BehatReporter is a native Android app for viewing the output of the [Behat](https://github.com/Behat/Behat) command line tool on your mobile device.

<a href="https://play.google.com/store/apps/details?id=headrevision.BehatReporter">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_60.png" />
</a>

Why this app was developed?
---------------------------
Besides the fact that the app IS still in development, it was initially developed to enable Behat users (=you) to present the results of a Behat run in a form as accessible as possible to the non-developer stakeholders in the [BDD process](http://www.ymc.ch/en/behavior-driven-development-with-behat-co-more-than-just-testing). Have a look yourself:

<a href="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_dialog.png"><img src="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_dialog.png" alt="Dialog" width="158" height="264"></a>
<a href="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_features.png"><img src="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_features.png" alt="Features" width="264" height="158"></a>
<a href="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_scenarios.png"><img src="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_scenarios.png" alt="Scenarios" width="158" height="264"></a>
<a href="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_background.png"><img src="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_background.png" alt="Background" width="158" height="264"></a>
<a href="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_outline.png"><img src="https://github.com/headrevision/BehatReporter/raw/master/doc/screenshot_outline.png" alt="Outline" width="158" height="264"></a>

What do you need in order to use it?
------------------------------------

- Behat 2.4+ with the [CommonFormatters extension](https://github.com/Behat/CommonFormatters/blob/master/doc/index.rst) installed
- A web server for delivering JSON files via HTTP
- An Android device with Android 3.0+ ("Honeycomb")

Get the app 
-----------

Install the app on your device via Google Play:

<a href="https://play.google.com/store/apps/details?id=headrevision.BehatReporter">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

Alternatively, you can also compile the app from the sources:

1. Get the [Android SDK](http://developer.android.com/sdk/index.html) and install API version 17
2. Clone this repository
3. Follow the [instructions for compiling & signing an Android app](http://developer.android.com/tools/publishing/app-signing.html#releasemode)
4. Install the signed APK on your Android device

How to use it?
--------------

1. Run Behat as usual - but choose the [JsonFormatter](https://github.com/Behat/CommonFormatters/blob/master/src/Behat/CommonFormatters/JsonFormatter.php) this time: `$> bin/behat --format json > output.json`
2. Put the file `output.json` on your web server
3. Start the app on your device and enter the URL of the JSON file, eg. `http://some_local_host/output.json`
4. Enjoy the experience :)

License
-------

See [LICENSE](https://github.com/headrevision/BehatReporter/raw/master/LICENSE)

Behat was created by [Konstantin Kudryashov](https://github.com/everzet), see [LICENSE](https://github.com/Behat/Behat/raw/master/LICENSE) 
