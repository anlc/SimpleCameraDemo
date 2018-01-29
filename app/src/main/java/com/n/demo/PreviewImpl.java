package com.n.demo;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by n on 2018/1/29.
 */

abstract class PreviewImpl {

    interface CallBack{
        void onSurfaceChanged();
    }

    private CallBack callBack;
    private int width;
    private int height;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    abstract Surface getSurface();

    abstract View getView();

    SurfaceHolder getSurfaceHolder() {
        return null;
    }

    Object getSurfaceTexture(){
        return null;
    }

    void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    protected void dispatchSurfaceChanged(){
        callBack.onSurfaceChanged();
    }
}
