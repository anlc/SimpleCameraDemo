package com.n.demo;

import android.app.Activity;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by n on 2018/1/29.
 */

public class Camera1 extends CameraViewImpl {

    private static final SparseIntArray DEFAULT_ORIENTATIONS = new SparseIntArray();
    private static final SparseIntArray INVERSE_ORIENTATIONS = new SparseIntArray();

    static {
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_0, 90);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_90, 0);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_180, 270);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_270, 180);

        INVERSE_ORIENTATIONS.append(Surface.ROTATION_0, 270);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_90, 180);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_180, 90);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_270, 0);
    }

    private Camera.Parameters parameters;
    private Camera.Size size;
    private Camera camera;
    private int cameraId = 0;
    private PreviewImpl preview;

    public Camera1(PreviewImpl preview) {
        this.preview = preview;
    }

    @Override
    void start() {
        openCamera();
    }

    private void openCamera() {
        if (camera != null) {
            releaseCamera();
        }
        camera = Camera.open(cameraId);
        initParams();
        camera.stopPreview();

        try {
            camera.setPreviewDisplay(preview.getSurfaceHolder());
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initParams() {
        parameters = camera.getParameters();
        Camera.Size cameraSize = getCameraSize();
        parameters.setPictureSize(cameraSize.width, cameraSize.height);
        int rotation = 90;
        parameters.setRotation(DEFAULT_ORIENTATIONS.get(rotation));
        boolean autoFocus = parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_AUTO);
        final List<String> modes = parameters.getSupportedFocusModes();
        if (autoFocus && modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);
        } else if (modes.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        } else {
            parameters.setFocusMode(modes.get(0));
        }
        camera.setParameters(parameters);
    }

    //获取适合屏幕的宽高
    public Camera.Size getCameraSize() {
        int width = preview.getWidth();
        int height = preview.getHeight();
        float windowRatio = width / height;
        float minRatio = Integer.MAX_VALUE;

        List<Camera.Size> sizeList = camera.getParameters().getSupportedPreviewSizes();
        Camera.Size result = sizeList.get(sizeList.size() - 1);
        for (int i = 0; i < sizeList.size(); i++) {
            if (width == sizeList.get(i).width || width == sizeList.get(i).height) {
                return size = sizeList.get(i);
            }
            float ratio = sizeList.get(i).width / height;
            if (minRatio > Math.abs(windowRatio - ratio)) {
                result = sizeList.get(i);
            }
        }
        return size = result;
    }

    private void releaseCamera() {
    }

    @Override
    void stop() {
        releaseCamera();
    }

    @Override
    void takePic() {

    }

    @Override
    void takeVideo() {

    }
}
