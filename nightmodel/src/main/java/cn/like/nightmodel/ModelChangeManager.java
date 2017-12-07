package cn.like.nightmodel;

import android.os.Looper;
import android.os.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理夜间模式监听的回调
 * @author like
 */
class ModelChangeManager {

    List<ModelChangeListener> listeners = new ArrayList<>();

    void addListener(ModelChangeListener listener){
        listeners.add(listener);
    }

    void removeListener(ModelChangeListener listener) {
        listeners.remove(listener);
    }

    void notifyChange(final boolean isNight) {
        Looper.myQueue().addIdleHandler(new NotifyHandler(listeners, isNight));
    }

    static class NotifyHandler implements MessageQueue.IdleHandler {

        List<ModelChangeListener> listeners;
        boolean isNight;

        NotifyHandler(List<ModelChangeListener> listeners, boolean isNight) {
            this.listeners = listeners;
            this.isNight = isNight;
        }

        @Override
        public boolean queueIdle() {
            if (listeners != null) {
                for (ModelChangeListener listener:listeners) {
                    listener.onChanged(isNight);
                }
            }
            return false;
        }
    }

    static class Holder {
        static ModelChangeManager INSTANCE = new ModelChangeManager();
    }

    static ModelChangeManager getInstance() {
        return Holder.INSTANCE;
    }
}
