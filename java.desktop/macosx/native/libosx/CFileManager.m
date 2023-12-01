#import "com_apple_eio_FileManager.h"

#import "JNIUtilities.h"
#import "ThreadUtilities.h"

#import <Cocoa/Cocoa.h>

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _setFileTypeAndCreator
 * Signature: (Ljava/lang/String;II)V
 */
JNIEXPORT void JNICALL Java_com_apple_eio_FileManager__1setFileTypeAndCreator
(JNIEnv *env, jclass clz, jstring javaFilename, jint type, jint creator)
{
JNI_COCOA_ENTER(env);
        NSString *filename = NormalizedPathNSStringFromJavaString(env, javaFilename);
        NSDictionary *attr = [NSDictionary dictionaryWithObjectsAndKeys:
                                                        [NSNumber numberWithInt:type], NSFileHFSTypeCode,
                                                        [NSNumber numberWithInt:creator], NSFileHFSCreatorCode, nil];
    [[NSFileManager defaultManager] changeFileAttributes:attr atPath:filename];
JNI_COCOA_EXIT(env);
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _setFileType
 * Signature: (Ljava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_com_apple_eio_FileManager__1setFileType
(JNIEnv *env, jclass ckz, jstring javaFilename, jint type)
{
JNI_COCOA_ENTER(env);
        NSString *filename = NormalizedPathNSStringFromJavaString(env, javaFilename);
        NSDictionary *attr = [NSDictionary dictionaryWithObject:[NSNumber numberWithInt:type] forKey:NSFileHFSTypeCode];
    [[NSFileManager defaultManager] changeFileAttributes:attr atPath:filename];
JNI_COCOA_EXIT(env);
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _setFileCreator
 * Signature: (Ljava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_com_apple_eio_FileManager__1setFileCreator
(JNIEnv *env, jclass clz, jstring javaFilename, jint creator)
{
JNI_COCOA_ENTER(env);
        NSString *filename = NormalizedPathNSStringFromJavaString(env, javaFilename);
        NSDictionary *attr = [NSDictionary dictionaryWithObject:[NSNumber numberWithInt:creator] forKey:NSFileHFSCreatorCode];
    [[NSFileManager defaultManager] changeFileAttributes:attr atPath:filename];
JNI_COCOA_EXIT(env);
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _getFileType
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_apple_eio_FileManager__1getFileType
(JNIEnv *env, jclass clz, jstring javaFilename)
{
    jint type = 0;
JNI_COCOA_ENTER(env);
        NSString *filename = NormalizedPathNSStringFromJavaString(env, javaFilename);
    NSDictionary *attributes = [[NSFileManager defaultManager] fileAttributesAtPath:filename traverseLink:YES];
    NSNumber *val = [attributes objectForKey:NSFileHFSTypeCode];
    type = [val intValue];
JNI_COCOA_EXIT(env);
    return type;
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _getFileCreator
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_apple_eio_FileManager__1getFileCreator
  (JNIEnv *env, jclass clz, jstring javaFilename)
{
    jint creator = 0;
JNI_COCOA_ENTER(env);
        NSString *filename = NormalizedPathNSStringFromJavaString(env, javaFilename);
    NSDictionary *attributes = [[NSFileManager defaultManager] fileAttributesAtPath:filename traverseLink:YES];
    NSNumber *val = [attributes objectForKey:NSFileHFSCreatorCode];
    creator = [val intValue];
JNI_COCOA_EXIT(env);
    return creator;
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    _findFolder
 * Signature: (SIZ)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_apple_eio_FileManager__1findFolder__SIZ
(JNIEnv *env, jclass clz, jshort domain, jint folderType, jboolean createIfNeeded)
{
    jstring filename = nil;
JNI_COCOA_ENTER(env);

    FSRef foundRef;
    createIfNeeded = createIfNeeded || (folderType == kTemporaryFolderType) || (folderType == kChewableItemsFolderType);
    if (FSFindFolder((SInt16)domain, (OSType)folderType, (Boolean)createIfNeeded, &foundRef) == noErr) {
        char path[PATH_MAX];
        if (FSRefMakePath(&foundRef, (UInt8 *)path, sizeof(path)) == noErr) {
            NSString *filenameString = [[NSFileManager defaultManager] stringWithFileSystemRepresentation:path length:strlen(path)];
            filename = NormalizedPathJavaStringFromNSString(env, filenameString);
        }
    }

JNI_COCOA_EXIT(env);
    return filename;
}


/*
 * Class:     com_apple_eio_FileManager
 * Method:    _openURL
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_apple_eio_FileManager__1openURL
(JNIEnv *env, jclass clz, jstring urlString)
{
JNI_COCOA_ENTER(env);

    NSURL *url = [NSURL URLWithString:NormalizedPathNSStringFromJavaString(env, urlString)];

        // Radar 3208005: Run this on the main thread; file:// style URLs will hang otherwise.
    [ThreadUtilities performOnMainThreadWaiting:NO block:^(){
        [[NSWorkspace sharedWorkspace] openURL:url];
    }];

JNI_COCOA_EXIT(env);
}


/*
 * Class:     com_apple_eio_FileManager
 * Method:    getNativeResourceFromBundle
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_apple_eio_FileManager_getNativeResourceFromBundle
(JNIEnv *env, jclass clz, jstring javaResourceName, jstring javaSubDirName, jstring javaTypeName)
{
    jstring filename = NULL;
JNI_COCOA_ENTER(env);

    NSString *resourceName = NormalizedPathNSStringFromJavaString(env, javaResourceName);
        NSString *subDirectory = NormalizedPathNSStringFromJavaString(env, javaSubDirName);
        NSString *typeName = NormalizedPathNSStringFromJavaString(env, javaTypeName);

    NSString *path = [[NSBundle mainBundle] pathForResource:resourceName
                                                     ofType:typeName
                                                inDirectory:subDirectory];

    filename = NormalizedPathJavaStringFromNSString(env, path);

JNI_COCOA_EXIT(env);
    return filename;
}


/*
 * Class:     com_apple_eio_FileManager
 * Method:    getNativePathToApplicationBundle
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_apple_eio_FileManager_getNativePathToApplicationBundle
(JNIEnv *env, jclass clazz)
{
        jstring filename = nil;
JNI_COCOA_ENTER(env);

        NSBundle *mainBundle = [NSBundle mainBundle];
        filename = NormalizedPathJavaStringFromNSString(env, [mainBundle bundlePath]);

JNI_COCOA_EXIT(env);
        return filename;
}


/*
 * Class:     com_apple_eio_FileManager
 * Method:    __moveToTrash
 * Signature: (Ljava/lang/String;)V
 */

JNIEXPORT jboolean JNICALL Java_com_apple_eio_FileManager__1moveToTrash
(JNIEnv *env, jclass clz, jstring fileName)
{
    __block BOOL returnValue = NO;
JNI_COCOA_ENTER(env);

    NSString * path = NormalizedPathNSStringFromJavaString(env, fileName);
    NSURL *url = [NSURL fileURLWithPath:path];
    [ThreadUtilities performOnMainThreadWaiting:YES block:^(){

        returnValue  = [[NSFileManager defaultManager] trashItemAtURL:url
                                                     resultingItemURL:nil
                                                                error:nil];
    }];

JNI_COCOA_EXIT(env);

        return returnValue ? JNI_TRUE: JNI_FALSE;
}

/*
 * Class:     com_apple_eio_FileManager
 * Method:    __revealInFinder
 * Signature: (Ljava/lang/String;)V
 */

JNIEXPORT jboolean JNICALL Java_com_apple_eio_FileManager__1revealInFinder
(JNIEnv *env, jclass clz, jstring url)
{
        __block jboolean returnValue = JNI_FALSE;
JNI_COCOA_ENTER(env);

    NSString *path = NormalizedPathNSStringFromJavaString(env, url);
    [ThreadUtilities performOnMainThreadWaiting:YES block:^(){
        returnValue = [[NSWorkspace sharedWorkspace] selectFile:path inFileViewerRootedAtPath:@""];
    }];

JNI_COCOA_EXIT(env);

        return returnValue;
}
