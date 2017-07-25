# NightModel [![](https://jitpack.io/v/achenglike/NightModel.svg)](https://jitpack.io/#achenglike/NightModel)

a night model lib for easy change app's night theme. Based on official night model and need't restart Activity.

[中文README](https://github.com/achenglike/NightModel/blob/master/README_ZH.md)

##  Thanks!
* [ChangeSkin 张鸿洋](https://github.com/hongyangAndroid/ChangeSkin)
* [Blog 张鸿洋](http://blog.csdn.net/lmj623565791/article/details/51503977)
* [Android换肤技术总结 markzhai](http://blog.zhaiyifan.cn/2015/09/10/Android%E6%8D%A2%E8%82%A4%E6%8A%80%E6%9C%AF%E6%80%BB%E7%BB%93/)

## require
* support appcompat 23.2.0 or above
* activity extends AppCompatActivity
* follow Android official nigh model guidance

## import
1. Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.  Add the dependency
```
	dependencies {
	        compile 'com.github.achenglike:NightModel:x.x'
	}

```

## use
1. set NightModel in Application
	```
	public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NightModelManager.getInstance().init(this);
        ...
    }
	}

	```
2. attach and detach in Activity which need refresh, not every Activity
	```
	public class MainActivity extends AppCompatActivity {
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
    		// must before super.onCreate
        NightModelManager.getInstance().attach(this);
        super.onCreate(savedInstanceState);
    }
    
    ...
    
    @Override
    protected void onDestroy() {
        NightModelManager.getInstance().detach(this);
        super.onDestroy();
    }
	}
	```
3. call night/day method

	```
	private void changeNightModel() {
   	if (NightModelManager.getInstance().isCurrentNightModel(this)) {
		NightModelManager.getInstance().applyDayModel(this);
	} else {
		NightModelManager.getInstance().applyNightModel(this);
     	}
    }
	```
	
	
## warn
1. this lib used for resolving the problem that official night model can not refresh started Activities. So, only attach and detach in Activities that need refresh. It's a supplementary lib for official night model.

2. When create View dynamically in attached Activity with code, you should write like this:
	```
	View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, parentView, false);
	```
	because the lib need cache this View, use LayoutInflater.from(MainActivity.this) will use the proxy LayoutInflaterFactory create this View and cache it
3. style:@style/xx just support TextView (android:textColor or android:textSize) now

4. this lib with support lib 23.2.1 performance is better than 24.x.x, as 24.x.x changed some night model method that I have not had time to deal with. In support lib 24.x.x, AppCompatDelegateImplV14#applyDayNight(), it will restart the activity. But you can set configChanges to stop it like this:
    ```
    <activity
        android:name=".MainActivity"
        android:label="@string/app_name"
        android:configChanges="uiMode"
        android:theme="@style/AppTheme.NoActionBar">
    </activity>
    ```

5. in support 24.x.x, if we set a selector as background for View, night mode not work, I will deal with this matter when idle.

