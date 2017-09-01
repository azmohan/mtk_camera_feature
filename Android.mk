LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional
LOCAL_JACK_ENABLED := disabled
LOCAL_JAVA_LANGUAGE_VERSION := 1.7

#################################################
# built as system java library

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_MODULE := com.freeme.camera.mtkfeature
LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_STATIC_JAVA_LIBRARY)

#################################################

