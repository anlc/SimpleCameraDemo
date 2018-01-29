package com.n.demo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by n on 2018/1/29.
 */

public class TextureViewPreView extends PreviewImpl {

    private final TextureView textureView;

    TextureViewPreView(Context context, ViewGroup viewGroup) {
        textureView = new TextureView(context);
        viewGroup.addView(textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                setSize(width, height);
                dispatchSurfaceChanged();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                setSize(width, height);
                dispatchSurfaceChanged();
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                setSize(0, 0);
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    @Override
    Surface getSurface() {
        return new Surface(textureView.getSurfaceTexture());
    }

    @Override
    Object getSurfaceTexture() {
        return textureView.getSurfaceTexture();
    }

    @Override
    View getView() {
        return textureView;
    }
}
