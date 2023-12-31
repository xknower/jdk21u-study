#include "ResourceEditor.h"
#include "ErrorHandling.h"
#include "IconSwap.h"
#include "VersionInfo.h"
#include "JniUtils.h"
#include "MsiDb.h"

extern "C" {

    /*
     * Class:     jdk_jpackage_internal_ExecutableRebrander
     * Method:    lockResource
     * Signature: (Ljava/lang/String;)J
     */
    JNIEXPORT jlong JNICALL
        Java_jdk_jpackage_internal_ExecutableRebrander_lockResource(
            JNIEnv *pEnv, jclass c, jstring jExecutable) {

        JP_TRY;

        const std::wstring executable = jni::toUnicodeString(pEnv, jExecutable);

        return reinterpret_cast<jlong>(
                ResourceEditor::FileLock(executable).ownHandle(false).get());

        JP_CATCH_ALL;

        return 0;
    }

    /*
     * Class:     jdk_jpackage_internal_ExecutableRebrander
     * Method:    unlockResource
     * Signature: (J;)V
     */
    JNIEXPORT void JNICALL
        Java_jdk_jpackage_internal_ExecutableRebrander_unlockResource(
            JNIEnv *pEnv, jclass c, jlong jResourceLock) {

        JP_TRY;
        ResourceEditor::FileLock(
                reinterpret_cast<HANDLE>(jResourceLock)).ownHandle(true);
        JP_CATCH_ALL;
    }

    /*
     * Class:     jdk_jpackage_internal_ExecutableRebrander
     * Method:    iconSwap
     * Signature: (J;Ljava/lang/String;)I
     */
    JNIEXPORT jint JNICALL
            Java_jdk_jpackage_internal_ExecutableRebrander_iconSwap(
            JNIEnv *pEnv, jclass c, jlong jResourceLock, jstring jIconTarget) {

        JP_TRY;

        const ResourceEditor::FileLock lock(reinterpret_cast<HANDLE>(jResourceLock));

        const std::wstring iconTarget = jni::toUnicodeString(pEnv, jIconTarget);

        if (ChangeIcon(lock.get(), iconTarget)) {
            return 0;
        }

        JP_CATCH_ALL;

        return 1;
    }

    /*
     * Class:     jdk_jpackage_internal_ExecutableRebrander
     * Method:    versionSwap
     * Signature: (J;[Ljava/lang/String;)I
     */
    JNIEXPORT jint JNICALL
            Java_jdk_jpackage_internal_ExecutableRebrander_versionSwap(
            JNIEnv *pEnv, jclass c, jlong jResourceLock,
            jobjectArray jExecutableProperties) {

        JP_TRY;

        const tstring_array props = jni::toUnicodeStringArray(pEnv,
                jExecutableProperties);

        VersionInfo vi;

        tstring_array::const_iterator it = props.begin();
        tstring_array::const_iterator end = props.end();
        for (; it != end; ++it) {
            const tstring name = *it;
            const tstring value = *++it;
            vi.setProperty(name, value);
        }

        const ResourceEditor::FileLock lock(reinterpret_cast<HANDLE>(jResourceLock));
        vi.apply(lock);

        return 0;

        JP_CATCH_ALL;

        return 1;
    }

    /*
     * Class:     jdk_jpackage_internal_WinExeBundler
     * Method:    embedMSI
     * Signature: (J;Ljava/lang/String;)I
     */
    JNIEXPORT jint JNICALL Java_jdk_jpackage_internal_WinExeBundler_embedMSI(
            JNIEnv *pEnv, jclass c, jlong jResourceLock, jstring jmsiPath) {

        JP_TRY;

        const std::wstring msiPath = jni::toUnicodeString(pEnv, jmsiPath);

        // Put msi file in resources.
        const ResourceEditor::FileLock lock(reinterpret_cast<HANDLE>(jResourceLock));
        ResourceEditor().id(L"msi").type(RT_RCDATA).apply(lock, msiPath);

        // Get product code of the msi being embedded
        const Guid productCode = Guid(msi::Database(msiPath).getProperty(L"ProductCode"));

        // Save product code in resources
        std::istringstream in(tstrings::toUtf8(productCode.toString()));
        ResourceEditor().id(L"product_code").type(RT_RCDATA).apply(lock, in);

        return 0;

        JP_CATCH_ALL;

        return 1;
    }

} // extern "C"