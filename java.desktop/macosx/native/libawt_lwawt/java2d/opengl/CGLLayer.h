#ifndef CGLLayer_h_Included
#define CGLLayer_h_Included

@interface CGLLayer : CAOpenGLLayer
{
@private
    jobject javaLayer;

    // intermediate buffer, used the RQ lock to synchronize
    GLuint textureID;
    GLenum target;
    float textureWidth;
    float textureHeight;
}

@property (nonatomic) jobject javaLayer;
@property (readwrite, assign) GLuint textureID;
@property (readwrite, assign) GLenum target;
@property (readwrite, assign) float textureWidth;
@property (readwrite, assign) float textureHeight;

- (id) initWithJavaLayer:(jobject)javaLayer;
- (void) blitTexture;
@end

#endif /* CGLLayer_h_Included */
