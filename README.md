# react-native-geolocation-android
Simple React Native Android module to use Android's geo location inside your app.
This module will be useful until the official RN support aren't released.

### Installation

```bash
npm install react-native-geolocation-android --save
```

### Add it to your android project

* In `android/setting.gradle`

```gradle
...
include ':GeoLocation', ':app'
project(':GeoLocation').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-geolocation-android')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':GeoLocation')
}
```

* Register Module (in MainActivity.java)

```java
import com.lxsameer.geolocation.ReacLocation;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new ReactLocation()) // <------ add this line to yout MainActivity class
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ......

}
```


## License
LGPLv2.1
