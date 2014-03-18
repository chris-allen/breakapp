#include <jni.h>
#include <stdio.h>
#include "client/linux/handler/exception_handler.h"
#include "client/linux/handler/minidump_descriptor.h"

static google_breakpad::ExceptionHandler* exceptionHandler;
bool DumpCallback
(
	const google_breakpad::MinidumpDescriptor& descriptor,
	void* context,
	bool succeeded
) {
	printf("Dump path: %s\n", descriptor.path());
	return succeeded;
}

extern "C" {

	void Java_net_hockeyapp_android_NativeCrashManager_setUpBreakpad
	(
		JNIEnv* env, jobject obj, jstring filepath
	) {
		const char *path = env->GetStringUTFChars(filepath, 0);
		google_breakpad::MinidumpDescriptor descriptor(path);
		exceptionHandler = new google_breakpad::ExceptionHandler(
			descriptor, NULL, DumpCallback, NULL, true, -1
		);
	}

}
