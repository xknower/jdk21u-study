#ifndef JDWP_SIGNATURE_H
#define JDWP_SIGNATURE_H

#define JVM_TYPE_ASSERT(typeKey)\
JDI_ASSERT_MSG(JDWP_Tag_OBJECT == typeKey || \
               JDWP_Tag_ARRAY == typeKey || \
               JDWP_Tag_BOOLEAN == typeKey || \
               JDWP_Tag_BYTE == typeKey || \
               JDWP_Tag_CHAR == typeKey || \
               JDWP_Tag_DOUBLE == typeKey || \
               JDWP_Tag_FLOAT == typeKey || \
               JDWP_Tag_INT == typeKey || \
               JDWP_Tag_LONG == typeKey || \
               JDWP_Tag_SHORT == typeKey || \
               JDWP_Tag_VOID == typeKey, \
               "Tag is not a JVM basic type")

static inline jbyte jdwpTag(const char *signature) {
     JVM_TYPE_ASSERT(signature[0]);
     return signature[0];
}

static inline jboolean isReferenceTag(jbyte typeKey) {
    JVM_TYPE_ASSERT(typeKey);
    return (typeKey == JDWP_TAG(OBJECT)) || (typeKey == JDWP_TAG(ARRAY));
}

static inline jboolean isArrayTag(jbyte typeKey) {
    JVM_TYPE_ASSERT(typeKey);
    return (typeKey == JDWP_TAG(ARRAY));
}

char* componentTypeSignature(const char *signature);

void convertSignatureToClassname(char *convert);

void methodSignature_init(char *signature, void **cursor);
jboolean methodSignature_nextArgumentExists(void **cursor, jbyte *argumentTag);
jbyte methodSignature_returnTag(char *signature);

#endif
