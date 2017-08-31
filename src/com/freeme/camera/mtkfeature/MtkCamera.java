package com.freeme.camera.mtkfeature;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.AutoFocusMoveCallback;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.FbOriginalCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import java.io.IOException;

import android.hardware.Camera.GestureCallback;

public class MtkCamera {
	private static final String TAG = "AndroidCamera";

	protected Camera mCamera = null;

	private class GestureCallbackImpl implements GestureCallback {
		GestureListener mListener;

		public GestureCallbackImpl(GestureListener listener) {
			mListener = listener;
		}

		@Override
		public void onGesture() {
			// Because stopGS will set lister is null,
			// at this time the onGesture is on the road ,so will JE
			if (mListener != null) {
				mListener.onGesture();
			}
		}
	}

	private class FbOriginalCallbackImpl implements FbOriginalCallback {
		FbOriginalListener mListener;

		public FbOriginalCallbackImpl(FbOriginalListener listener) {
			mListener = listener;
		}

		@Override
		public void onCapture(byte[] data) {
			Log.d(TAG, "cFBCallback,[onOriginalCallback],data.length = "
					+ data.length);
			if (mListener != null) {
				mListener.onOriginalCallback(data);
			}
		}

	}

	public interface GestureListener {
		void onGesture();
	}

	public interface FbOriginalListener {
		public void onOriginalCallback(byte[] data);
	}

	public static void assertError(boolean cond) {
		if (!cond) {
			throw new AssertionError();
		}
	}

	public MtkCamera(Camera camera) {
		assertError(null != camera);
		mCamera = camera;
	}

	public Camera getInstance() {
		return mCamera;
	}

	public void stopGestureDetection() {
		Log.i(TAG, "[stopGestureDetection]...");
		try {
			mCamera.stopGestureDetection();
		} catch (Exception e) {
			Log.i(TAG, "Exception :" + e.toString());
		}
	}

	public void lock() {
		Log.i(TAG, "[lock]...");
		mCamera.lock();
	}

	public Parameters getParameters() {
		Log.i(TAG, "[getParameters]...");
		return mCamera.getParameters();
	}

	public void release() {
		Log.i(TAG, "[release]...");
		mCamera.release();
	}

	public void reconnect() throws IOException {
		Log.i(TAG, "[reconnect]...");
		mCamera.reconnect();
	}

	public void setContext(Context context) {
		Log.i(TAG, "[setContext]do nothing...");
	}

	public void setParameters(Parameters params) {
		Log.i(TAG, "[setParameters]...");
		mCamera.setParameters(params);
	}

	public void setGestureCallback(GestureListener listener) {
		Log.i(TAG, "[setGestureCallback]...");
		try {
			mCamera.setGestureCallback(listener != null ? new GestureCallbackImpl(
					listener) : null);
		} catch (Exception e) {
			Log.i(TAG, "Exception :" + e.toString());
		}
	}

	public void setFbOriginalCallback(FbOriginalListener listener) {
		Log.i(TAG, "[setFbOriginalCallback]...");
		try {
			mCamera.setFbOriginalCallback(listener != null ? new FbOriginalCallbackImpl(
					listener) : null);
		} catch (Exception e) {
			Log.i(TAG, "Exception : " + e.toString());
		}

	}

	public void startGestureDetection() {
		Log.i(TAG, "[startGestureDetection]...");
		try {
			mCamera.startGestureDetection();
		} catch (Exception e) {
			Log.i(TAG, "Exception :" + e.toString());
		}
	}

	public void unlock() {
		Log.i(TAG, "[unlock]...");
		mCamera.unlock();
	}
}
