#ifndef COMMON_H
#define COMMON_H

#include <simd/simd.h>

#define PGRAM_VERTEX_COUNT 6
#define QUAD_VERTEX_COUNT 4
#define GRAD_MAX_FRACTIONS 12

enum GradCycleMethod {
    GradNoCycle = 0,
    GradReflect = 1,
    GradRepeat = 2
};
enum VertexAttributes {
    VertexAttributePosition = 0,
    VertexAttributeTexPos = 1,
    VertexAttributeITexPos = 2
};

enum BufferIndex  {
    MeshVertexBuffer = 0,
    FrameUniformBuffer = 1,
    MatrixBuffer = 2
};

struct FrameUniforms {
    vector_float4 color;
};

struct TransformMatrix {
    matrix_float4x4 transformMatrix;
};

struct GradFrameUniforms {
    vector_float3 params;
    vector_float4 color1;
    vector_float4 color2;
    int isCyclic;
    float extraAlpha;
};

struct LinGradFrameUniforms {
    vector_float3 params;
    float fract[GRAD_MAX_FRACTIONS];
    vector_float4 color[GRAD_MAX_FRACTIONS];
    int numFracts;
    int isLinear;
    int cycleMethod;
    float extraAlpha;
};

struct RadGradFrameUniforms {
    float fract[GRAD_MAX_FRACTIONS];
    vector_float4 color[GRAD_MAX_FRACTIONS];
    int numFracts;
    int isLinear;
    int cycleMethod;
    vector_float3 m0;
    vector_float3 m1;
    vector_float3 precalc;
    float extraAlpha;
};

struct Vertex {
    float position[2];
};

struct TxtVertex {
    float position[2];
    float txtpos[2];
};

struct AAVertex {
    float position[2];
    float otxtpos[2];
    float itxtpos[2];
};

// These values are mapped from AffineTransformOp
#define INTERPOLATION_NEAREST_NEIGHBOR 1
#define INTERPOLATION_BILINEAR 2
// #define INTERPOLATION_BICUBIC 3
// NOTE: Metal samplers doesn't supports bicubic interpolation
// see table 2.7 from https://developer.apple.com/metal/Metal-Shading-Language-Specification.pdf
// (probably we need to implement separate fragment shader with bicubic interpolation)

struct TxtFrameUniforms {
    vector_float4 color;
    int mode; // NOTE: consider to use bit fields
    int isSrcOpaque;
    float extraAlpha;
};

struct TxtFrameOpRescaleUniforms {
    vector_float4 color;
    float extraAlpha;

    int isSrcOpaque;
    int isNonPremult;

    vector_float4 normScaleFactors;
    vector_float4 normOffsets;
};

struct TxtFrameOpConvolveUniforms {
    float extraAlpha;
    int isSrcOpaque;
    vector_float4 imgEdge;
    int kernelSize;
    int isEdgeZeroFill;
};

struct TxtFrameOpLookupUniforms {
    float extraAlpha;
    int isSrcOpaque;
    vector_float4 offset;
    int isUseSrcAlpha;
    int isNonPremult;
};

struct AnchorData
{
    vector_float3 xParams;
    vector_float3 yParams;
};

struct LCDFrameUniforms {
    vector_float3 src_adj;
    vector_float3 gamma;
    vector_float3 invgamma;
};

struct SwizzleUniforms {
    unsigned char swizzle[4];
    unsigned char hasAlpha;
};
#endif
