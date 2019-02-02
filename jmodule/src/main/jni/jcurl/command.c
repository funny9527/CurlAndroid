#include <stdlib.h>
#include <string.h>
#include <curl/curl.h>
#include "main.h"
#include "../log.h"

JNIEXPORT jint JNICALL Java_com_curl_jmodule_Commander_start(JNIEnv* env, jobject obj, jobjectArray jcmd, jstring terminal, jstring sec_path) {
    int size = (*env)->GetArrayLength(env, jcmd);
	char* argv[size];

    for (int i = 0; i < size; i++) {
        jstring item = (jstring) ((*env)->GetObjectArrayElement(env, jcmd, i));
        const char* citem = (*env)->GetStringUTFChars(env, item, 0);

        int len = strlen(citem);
        char* buffer = (char*) malloc(sizeof(char) * (len + 1));
        memset(buffer, 0, sizeof(char) * (len + 1));
        strncpy(buffer, citem, len);

        argv[i] = buffer;
        (*env)->ReleaseStringUTFChars(env, item, citem);
    }

    char* path = (char*) (*env)->GetStringUTFChars(env, terminal, 0);
    char* sec = (char*) (*env)->GetStringUTFChars(env, sec_path, 0);
    execute(size, argv, path, sec);

    (*env)->ReleaseStringUTFChars(env, terminal, path);
    (*env)->ReleaseStringUTFChars(env, sec_path, sec);
    for (int i = 0; i < size; i++) {
        free(argv[i]);
    }

	return 0;
}

