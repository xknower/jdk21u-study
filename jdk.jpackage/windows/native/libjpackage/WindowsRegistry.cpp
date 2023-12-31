#include "JniUtils.h"
#include "FileUtils.h"
#include "ErrorHandling.h"

// Requires linking with advapi32

namespace {

std::wstring GetLongPath(const std::wstring& path) {
    const std::wstring cleanPath = FileUtils::removeTrailingSlash(path);
    if (cleanPath.size() != path.size()) {
        return GetLongPath(cleanPath);
    }

    enum { BUFFER_SIZE = 4096 };

    std::wstring result;

    TCHAR *pBuffer = new TCHAR[BUFFER_SIZE];
    if (pBuffer != NULL) {
        DWORD dwResult = GetLongPathName(path.c_str(), pBuffer, BUFFER_SIZE);
        if (dwResult > 0 && dwResult < BUFFER_SIZE) {
            result = std::wstring(pBuffer);
        } else {
            delete [] pBuffer;
            pBuffer = new TCHAR[dwResult];
            if (pBuffer != NULL) {
                DWORD dwResult2 =
                        GetLongPathName(path.c_str(), pBuffer, dwResult);
                if (dwResult2 == (dwResult - 1)) {
                    result = std::wstring(pBuffer);
                }
            }
        }

        if (pBuffer != NULL) {
            delete [] pBuffer;
        }
    }

    return result;
}

} // namespace


extern "C" {

#undef jdk_jpackage_internal_WindowsRegistry_HKEY_LOCAL_MACHINE
#define jdk_jpackage_internal_WindowsRegistry_HKEY_LOCAL_MACHINE 1L

    /*
     * Class:     jdk_jpackage_internal_WindowsRegistry
     * Method:    readDwordValue
     * Signature: (ILjava/lang/String;Ljava/lang/String;I)I
     */
    JNIEXPORT jint JNICALL
            Java_jdk_jpackage_internal_WindowsRegistry_readDwordValue(
            JNIEnv *pEnv, jclass c, jint key, jstring jSubKey,
            jstring jValue, jint defaultValue) {
        jint jResult = defaultValue;

        JP_TRY;

        if (key != jdk_jpackage_internal_WindowsRegistry_HKEY_LOCAL_MACHINE) {
            JP_THROW("Invalid Windows registry key id");
        }

        const std::wstring subKey = jni::toUnicodeString(pEnv, jSubKey);
        const std::wstring value = jni::toUnicodeString(pEnv, jValue);

        HKEY hSubKey = NULL;
        LSTATUS status = RegOpenKeyEx(HKEY_LOCAL_MACHINE, subKey.c_str(), 0,
                KEY_QUERY_VALUE, &hSubKey);
        if (status == ERROR_SUCCESS) {
            DWORD dwValue = 0;
            DWORD cbData = sizeof (DWORD);
            status = RegQueryValueEx(hSubKey, value.c_str(), NULL, NULL,
                    (LPBYTE) & dwValue, &cbData);
            if (status == ERROR_SUCCESS) {
                jResult = (jint) dwValue;
            }

            RegCloseKey(hSubKey);
        }

        JP_CATCH_ALL;

        return jResult;
    }

    /*
     * Class:     jdk_jpackage_internal_WindowsRegistry
     * Method:    openRegistryKey
     * Signature: (ILjava/lang/String;)J
     */
    JNIEXPORT jlong JNICALL
            Java_jdk_jpackage_internal_WindowsRegistry_openRegistryKey(
            JNIEnv *pEnv, jclass c, jint key, jstring jSubKey) {

        JP_TRY;

        if (key != jdk_jpackage_internal_WindowsRegistry_HKEY_LOCAL_MACHINE) {
            JP_THROW("Invalid Windows registry key id");
        }

        const std::wstring subKey = jni::toUnicodeString(pEnv, jSubKey);
        HKEY hSubKey = NULL;
        LSTATUS status = RegOpenKeyEx(HKEY_LOCAL_MACHINE, subKey.c_str(), 0,
                KEY_QUERY_VALUE, &hSubKey);
        if (status == ERROR_SUCCESS) {
            return (jlong)hSubKey;
        }

        JP_CATCH_ALL;

        return 0;
    }

    /*
     * Class:     jdk_jpackage_internal_WindowsRegistry
     * Method:    enumRegistryValue
     * Signature: (JI)Ljava/lang/String;
     */
    JNIEXPORT jstring JNICALL
            Java_jdk_jpackage_internal_WindowsRegistry_enumRegistryValue(
            JNIEnv *pEnv, jclass c, jlong lKey, jint jIndex) {

        JP_TRY;

        // Max value name size per MSDN plus NULL
        enum { VALUE_NAME_SIZE = 16384 };

        HKEY hKey = (HKEY)lKey;
        TCHAR valueName[VALUE_NAME_SIZE] = {0}; // Max size per MSDN plus NULL
        DWORD cchValueName = VALUE_NAME_SIZE;
        LSTATUS status = RegEnumValue(hKey, (DWORD)jIndex, valueName,
                &cchValueName, NULL, NULL, NULL, NULL);
        if (status == ERROR_SUCCESS) {
            size_t chLength = 0;
            if (StringCchLength(valueName, VALUE_NAME_SIZE, &chLength)
                    == S_OK) {
                return jni::toJString(pEnv, std::wstring(valueName, chLength));
            }
        }

        JP_CATCH_ALL;

        return NULL;
    }

    /*
     * Class:     jdk_jpackage_internal_WindowsRegistry
     * Method:    closeRegistryKey
     * Signature: (J)V
     */
    JNIEXPORT void JNICALL
            Java_jdk_jpackage_internal_WindowsRegistry_closeRegistryKey(
            JNIEnv *pEnc, jclass c, jlong lKey) {
        HKEY hKey = (HKEY)lKey;
        RegCloseKey(hKey);
    }

    /*
     * Class:     jdk_jpackage_internal_WindowsRegistry
     * Method:    comparePaths
     * Signature: (Ljava/lang/String;Ljava/lang/String;)Z
     */
     JNIEXPORT jboolean JNICALL
            Java_jdk_jpackage_internal_WindowsRegistry_comparePaths(
            JNIEnv *pEnv, jclass c, jstring jPath1, jstring jPath2) {

         JP_TRY;

         std::wstring path1 = jni::toUnicodeString(pEnv, jPath1);
         std::wstring path2 = jni::toUnicodeString(pEnv, jPath2);

         path1 = GetLongPath(path1);
         path2 = GetLongPath(path2);

         if (path1.empty() || path2.empty()) {
             return JNI_FALSE;
         }

         if (tstrings::equals(path1, path2, tstrings::IGNORE_CASE)) {
             return JNI_TRUE;
         }

         JP_CATCH_ALL;

         return JNI_FALSE;
     }

} // extern "C"
