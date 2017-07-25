# NightModel [![](https://jitpack.io/v/achenglike/NightModel.svg)](https://jitpack.io/#achenglike/NightModel)

这是个方便切换夜间模式的库，利用官方夜间模式，同时不用重启Activity

##  致谢!
* [ChangeSkin 张鸿洋](https://github.com/hongyangAndroid/ChangeSkin)
* [Blog 张鸿洋](http://blog.csdn.net/lmj623565791/article/details/51503977)
* [Android换肤技术总结 markzhai](http://blog.zhaiyifan.cn/2015/09/10/Android%E6%8D%A2%E8%82%A4%E6%8A%80%E6%9C%AF%E6%80%BB%E7%BB%93/)

## 实现思路
[夜间模式实践](http://www.jianshu.com/p/c85c25357559)

## 使用要求
* 官方包support appcompat 23.2.0 或以上版本
* activity 需要继承自 AppCompatActivity
* 应用按照官方的夜间模式实现

## 配置引用
1. 在root工程的build.gradle中添加maven仓库:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.  在需要的module build.gradle中添加依赖
```
	dependencies {
	        compile 'com.github.achenglike:NightModel:x.x'
	}

```

## 使用
1. 在appication中初始化
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
2. 只需要在需要刷新的activity中调用attach、detach方法。其它activity不需要调用此方法
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
3. 切换时调用appleyDayModel\appleyNightModel进行夜间模式切换

	```
	private void changeNightModel() {
   	if (NightModelManager.getInstance().isCurrentNightModel(this)) {
		NightModelManager.getInstance().applyDayModel(this);
	} else {
		NightModelManager.getInstance().applyNightModel(this);
     	}
    }
	```
	
	
## 注意
1. 这个库是用来解决官方夜间模式中，已打开的activity需要调用restart方法来刷新界面的问题的。所以不要在不需要刷新界面的activity中调用attach、detach方法。此库是对官方夜间模式的补充。

2. 如果想在需要动态刷新的activity中用代码实例化view控件需要按照下面的方式进行:
	```
	View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, parentView, false);
	```
	因为此库需要缓存动态activity中必要的view控件。使用LayoutInflater.from(MainActivity.this)的方式可以让库中的LayoutInflaterFactory代理viwe控件的生成然后缓存它。

3. style:@style/xx 这这在XML中定义样式的方法，目前只支持 TextView (android:textColor or  android:textSize) 进行样式切换，所以其他view不能使用style:@style/xx这种方式，不然控件没有夜间模式切换效果


4. 因为24.x.x修改了夜间模式的一些方法,我还没有时间处理,所以这个库在support包23.2.1上的表现比24.x.x上要好。在support 24.x.x中 AppCompatDelegateImplV14#applyDayNight()这个方法会立即重启activity。不过你可以通过配置AndroidManifest.xml中的声明uiMode来阻止重启。
    ```
    <activity
        android:name=".MainActivity"
        android:label="@string/app_name"
        android:configChanges="uiMode"
        android:theme="@style/AppTheme.NoActionBar">
    </activity>
    ```

5. 这个库在support 24.x.x 版本中如果你为布局设置的background是selector的话夜间模式会失灵,我有时间的话就会处理它。

