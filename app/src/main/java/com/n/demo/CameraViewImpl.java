package com.n.demo;

/**
 * Created by n on 2018/1/29.
 */

abstract class CameraViewImpl {

    abstract void openCamera();

    abstract void release();

    abstract void takePic();

    abstract void takeVideo();
}
