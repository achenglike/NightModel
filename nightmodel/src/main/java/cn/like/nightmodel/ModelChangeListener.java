package cn.like.nightmodel;

/**
 * Created by like on 2017/12/7.
 */

public interface ModelChangeListener {
    /**
     * 夜间模式切换
     * @param isNight true切换到夜间模式，false切换到日间模式
     */
    void onChanged(boolean isNight);
}
