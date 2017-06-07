LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include G:\AS_Work_Space\NDK\ImageViewClip\ivcliplib\src\main\opencv\jni\OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_SRC_FILES  := ivclip.cpp tclip.cpp
LOCAL_C_INCLUDES += $(LOCAL_PATH)
LOCAL_LDFLAGS    += -ljnigraphics
LOCAL_LDLIBS     += -llog -ldl -landroid

LOCAL_MODULE     := ivclip

include $(BUILD_SHARED_LIBRARY)
