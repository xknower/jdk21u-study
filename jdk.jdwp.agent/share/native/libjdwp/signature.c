#include "util.h"
#include "signature.h"


/*
 * JNI signature constants, beyond those defined in JVM_TYPE(*)
 */
#define SIGNATURE_BEGIN_ARGS    '('
#define SIGNATURE_END_ARGS      ')'
#define SIGNATURE_END_CLASS     ';'

char* componentTypeSignature(const char *signature) {
     jbyte typeKey = jdwpTag(signature);
     JDI_ASSERT(isArrayTag(typeKey));
     JVM_TYPE_ASSERT(signature[1]);
     return (char*)&signature[1];
}

jbyte methodSignature_returnTag(char *signature)
{
    char *tagPtr = strchr(signature, SIGNATURE_END_ARGS);
    JDI_ASSERT(tagPtr);
    tagPtr++;    /* 1st character after the end of args */
    JVM_TYPE_ASSERT((jbyte)*tagPtr);
    return (jbyte)*tagPtr;
}

void methodSignature_init(char *signature, void **cursor)
{
     JDI_ASSERT(signature[0] == SIGNATURE_BEGIN_ARGS);
     *cursor = signature + 1; /* skip to the first arg */
}


jboolean methodSignature_nextArgumentExists(void **cursor, jbyte *argumentTag)
{
    char *tagPtr = *cursor;
    jbyte nextType = (jbyte)*tagPtr;

    if (*tagPtr != SIGNATURE_END_ARGS) {
        /* Skip any array modifiers */
        while (*tagPtr == JDWP_TAG(ARRAY)) {
            tagPtr++;
        }
        /* Skip class name */
        if (*tagPtr == JDWP_TAG(OBJECT)) {
            tagPtr = strchr(tagPtr, SIGNATURE_END_CLASS) + 1;
            JDI_ASSERT(tagPtr);
        } else {
            /* Skip primitive sig */
            tagPtr++;
        }
    }
    *cursor = tagPtr;
    if (nextType != SIGNATURE_END_ARGS) {
        JVM_TYPE_ASSERT(nextType);
        *argumentTag = nextType;
        return JNI_TRUE;
    }
    return JNI_FALSE;
}

/*
 * Convert the signature "Ljava/lang/Foo;" to a
 * classname "java.lang.Foo" compatible with the pattern.
 * Signature is overwritten in-place.
 */
void convertSignatureToClassname(char *convert)
{
    char *p;

    p = convert + 1;
    while ((*p != ';') && (*p != '\0')) {
        char c = *p;
        if (c == '/') {
            *(p-1) = '.';
        } else if (c == '.') {
            // class signature of a hidden class is "Ljava/lang/Foo.1234;"
            // map to "java.lang.Foo/1234"
            *(p-1) = '/';
        } else {
            *(p-1) = c;
        }
        p++;
    }
    *(p-1) = '\0';
}
