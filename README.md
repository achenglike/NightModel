# NightModel
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

