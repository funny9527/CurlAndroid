//
// Created by tical on 2019/1/30.
//

#ifndef CURANDROID_LOG_H
#define CURANDROID_LOG_H

#ifdef ANDROID
	#include <android/log.h>
	#include <jni.h>
	#ifdef __LP64__
		#define SIZE_T_TYPE "%lu"
	#else
		#define SIZE_T_TYPE "%u"
	#endif
#endif

#ifdef ANDROID
	#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, "curl", __VA_ARGS__))
#else
	#define LOGI(...) printf(__VA_ARGS__)
#endif

#endif //CURANDROID_LOG_H
