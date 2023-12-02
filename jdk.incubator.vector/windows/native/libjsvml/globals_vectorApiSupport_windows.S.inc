; Used to check whether building on x86_64 architecture. Equivalent to checking in regular hpp file for #ifdef _WIN64
IFDEF RAX

; @Version is defined by MASM to determine the Visual Studio version. 1410 is the version for VS17
IF @Version GE 1410
__VECTOR_API_MATH_INTRINSICS_WINDOWS TEXTEQU <"vector_api">
ELSE
__VECTOR_API_MATH_INTRINSICS_WINDOWS TEXTEQU <>
ENDIF

ELSE
__VECTOR_API_MATH_INTRINSICS_WINDOWS TEXTEQU <>
ENDIF
