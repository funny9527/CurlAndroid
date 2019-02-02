LOCAL_PATH := $(call my-dir)

#cURL prebuilt
include $(CLEAR_VARS)
LOCAL_MODULE := curl-prebuilt
LOCAL_SRC_FILES := ./prebuilt-with-ssl/android/$(TARGET_ARCH_ABI)/libcurl.a
include $(PREBUILT_STATIC_LIBRARY)
################################################################################

#Test Library
include $(CLEAR_VARS)
LOCAL_MODULE := jcurl
LOCAL_SRC_FILES := ./jcurl/command.c \
                   ./jcurl/tool_main.c \
                   ./jcurl/tool_operate.c \
                   ./jcurl/tool_getparam.c \
                   ./jcurl/tool_libinfo.c \
                   ./jcurl/tool_cfgable.c \
                   ./jcurl/tool_helpers.c \
                   ./jcurl/tool_paramhlp.c \
                   ./jcurl/tool_filetime.c \
                   ./jcurl/tool_urlglob.c \
                   ./jcurl/tool_parsecfg.c \
                   ./jcurl/tool_operhlp.c \
                   ./jcurl/tool_dirhie.c \
                   ./jcurl/tool_setopt.c \
                   ./jcurl/tool_msgs.c \
                   ./jcurl/tool_easysrc.c \
                   ./jcurl/tool_help.c \
                   ./jcurl/tool_hugehelp.c \
                   ./jcurl/tool_homedir.c \
                   ./jcurl/tool_cb_prg.c \
                   ./jcurl/tool_cb_dbg.c \
                   ./jcurl/tool_cb_hdr.c \
                   ./jcurl/tool_util.c \
                   ./jcurl/tool_cb_wrt.c \
                   ./jcurl/tool_sleep.c \
                   ./jcurl/slist_wc.c \
                   ./jcurl/tool_cb_rea.c \
                   ./jcurl/tool_formparse.c \
                   ./jcurl/tool_getpass.c \
                   ./jcurl/tool_writeout.c \
                   ./jcurl/tool_cb_see.c \
                   ./jcurl/tool_xattr.c


LOCAL_STATIC_LIBRARIES := curl-prebuilt 
COMMON_CFLAGS := -Werror -DANDROID 

ifeq ($(TARGET_ARCH),arm)
  LOCAL_CFLAGS := -mfpu=vfp -mfloat-abi=softfp -fno-short-enums
endif

LOCAL_CFLAGS += $(COMMON_CFLAGS)
LOCAL_LDLIBS := -lz -llog -Wl,-s
LOCAL_CPPFLAGS += -std=gnu++0x
LOCAL_C_INCLUDES += \
  $(NDK_PATH)/platforms/$(TARGET_PLATFORM)/arch-$(TARGET_ARCH)/usr/include \
  $(LOCAL_PATH)/prebuilt-with-ssl/android/include

include $(BUILD_SHARED_LIBRARY)
################################################################################
