#ifndef __MLIB_IMAGEDIVTABLES_H
#define __MLIB_IMAGEDIVTABLES_H

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#ifdef __DIV_TABLE_DEFINED

const mlib_u16 mlib_div6_tab[];
const mlib_u16 mlib_div1_tab[];
const mlib_f32 mlib_HSL2RGB_L2[];
const mlib_f32 mlib_HSL2RGB_F[];
const mlib_f32 mlib_U82F32[];
const mlib_d64 mlib_U82D64[];
const mlib_u32 mlib_FlipAndFixRotateTable[];

#else

extern const mlib_u16 mlib_div6_tab[];
extern const mlib_u16 mlib_div1_tab[];
extern const mlib_f32 mlib_HSL2RGB_L2[];
extern const mlib_f32 mlib_HSL2RGB_F[];
extern const mlib_f32 mlib_U82F32[];
extern const mlib_d64 mlib_U82D64[];
extern const mlib_u32 mlib_FlipAndFixRotateTable[];

#endif /* __DIV_TABLE_DEFINED */

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGEDIVTABLES_H */

/***************************************************************/
