LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

#################################################
# built as system java library

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_MODULE := com.freeme.camera.mtkfeature
LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_STATIC_JAVA_LIBRARY)

#################################################

