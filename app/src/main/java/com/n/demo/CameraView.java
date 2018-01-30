package com.n.demo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by n on 2018/1/29.
 */

public class CameraView extends FrameLayout {

    private CameraViewImpl cameraImpl;

    public CameraView(@NonNull Context context) {
        this(context, null);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final PreviewImpl preview = createPreviewImpl(context);
//        if (Build.VERSION.SDK_INT < 21) {
        cameraImpl = new Camera1(preview);
//        } else if (Build.VERSION.SDK_INT < 23) {
//            cameraImpl = new Camera2();
//        }
    }

    private PreviewImpl createPreviewImpl(Context context) {
        PreviewImpl preview;
//        if (Build.VERSION.SDK_INT < 14) {
        preview = new SurfaceViewPreview(context, this);
//        } else {
//            preview = new TextureViewPreView(context, this);
//        }
        return preview;
    }

    public void openCamera() {
        cameraImpl.start();
    }

    public void release() {
        cameraImpl.stop();
    }

    public void takePic() {
        cameraImpl.takePic();
    }

    public void takeVideo() {
        cameraImpl.takeVideo();
    }
}
