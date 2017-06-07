#include <jni.h>

#ifndef __IVCLIP__
#define __IVCLIP__

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_cfox_IVClipLib
 * Method:    crop
 * Signature: (Ljava/lang/String;Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
 */
JNIEXPORT static jobject JNICALL crop(JNIEnv * env, jclass cls,
                    jobject config, jobject bitmap_src, int width, int height);

#ifdef __cplusplus
}
#endif

#endif
