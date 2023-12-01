#import <Cocoa/Cocoa.h>
#import <JavaRuntimeSupport/JavaRuntimeSupport.h>

#define MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE 256

@interface AWTFont : NSObject {
@public
    NSFont    *fFont;
    CGFontRef  fNativeCGFont;
    BOOL       fIsFakeItalic;
}

+ (AWTFont *) awtFontForName:(NSString *)name
    style:(int)style isFakeItalic:(BOOL)isFakeItalic;

+ (NSFont *) nsFontForJavaFont:(jobject)javaFont env:(JNIEnv *)env;

@end

bool IsEmojiFont(CTFontRef font);
