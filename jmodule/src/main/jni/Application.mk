APP_OPTIM := release
APP_PLATFORM := android-19
APP_STL := c++_static
APP_CPPFLAGS += -frtti
APP_CPPFLAGS += -fexceptions
APP_CPPFLAGS += -DANDROID
APP_ABI := arm64-v8a x86_64 armeabi-v7a x86
APP_MODULES := jcurl
NDK_TOOLCHAIN_VERSION := clang
