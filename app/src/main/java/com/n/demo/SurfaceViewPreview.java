package com.n.demo;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by n on 2018/1/29.
 */

public class SurfaceViewPreview extends PreviewImpl {

    final SurfaceView surfaceView;

    SurfaceViewPreview(Context context, ViewGroup viewGroup) {
        surfaceView = new SurfaceView(context);
        surfaceView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(surfaceView);
        final SurfaceHolder holder = surfaceView.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                setSize(width, height);
                if (!ViewCompat.isInLayout(surfaceView)) {
                    dispatchSurfaceChanged();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                setSize(0, 0);
            }
        });
    }

    @Override
    Surface getSurface() {
        return getSurfaceHolder().getSurface();
    }

    @Override
    SurfaceHolder getSurfaceHolder() {
        return surfaceView.getHolder();
    }

    @Override
    View getView() {
        return surfaceView;
    }
}
