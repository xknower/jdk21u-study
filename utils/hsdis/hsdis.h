/* decode_instructions -- dump a range of addresses as native instructions
   This implements the protocol required by the HotSpot PrintAssembly option.

   The start_va, end_va is the virtual address the region of memory to
   disasemble and buffer contains the instructions to decode,
   Disassembling instructions in the current address space is done by
   having start_va == buffer.

   The option string, if not empty, is interpreted by the disassembler implementation.

   The printf callback is 'fprintf' or any other workalike.
   It is called as (*printf_callback)(printf_stream, "some format...", some, format, args).

   The event callback receives an event tag (a string) and an argument (a void*).
   It is called as (*event_callback)(event_stream, "tag", arg).

   Events:
     <insn pc='%p'>             begin an instruction, at a given location
     </insn pc='%d'>            end an instruction, at a given location
     <addr value='%p'/>         emit the symbolic value of an address

   A tag format is one of three basic forms: "tag", "/tag", "tag/",
   where tag is a simple identifier, signifying (as in XML) a element start,
   element end, and standalone element.  (To render as XML, add angle brackets.)
*/
#ifndef SHARED_TOOLS_HSDIS_H
#define SHARED_TOOLS_HSDIS_H

#ifdef __cplusplus
extern "C"
{
#endif

extern
#ifdef _WIN32
__declspec(dllexport)
#endif
void* decode_instructions_virtual(uintptr_t start_va, uintptr_t end_va,
                                  unsigned char* buffer, uintptr_t length,
                                  void* (*event_callback)(void*, const char*, void*),
                                  void* event_stream,
                                  int (*printf_callback)(void*, const char*, ...),
                                  void* printf_stream,
                                  const char* options,
                                  int newline /* bool value for nice new line */);

/* This is the compatability interface for older versions of hotspot */
extern
#ifdef _WIN32
__declspec(dllexport)
#endif
void* decode_instructions(void* start_pv, void* end_pv,
                    void* (*event_callback)(void*, const char*, void*),
                    void* event_stream,
                    int   (*printf_callback)(void*, const char*, ...),
                    void* printf_stream,
                    const char* options);

/* convenience typedefs */

typedef void* (*decode_instructions_event_callback_ftype)  (void*, const char*, void*);
typedef int   (*decode_instructions_printf_callback_ftype) (void*, const char*, ...);
typedef void* (*decode_func_vtype) (uintptr_t start_va, uintptr_t end_va,
                                    unsigned char* buffer, uintptr_t length,
                                    decode_instructions_event_callback_ftype event_callback,
                                    void* event_stream,
                                    decode_instructions_printf_callback_ftype printf_callback,
                                    void* printf_stream,
                                    const char* options,
                                    int newline);
typedef void* (*decode_func_stype) (void* start_pv, void* end_pv,
                                    decode_instructions_event_callback_ftype event_callback,
                                    void* event_stream,
                                    decode_instructions_printf_callback_ftype printf_callback,
                                    void* printf_stream,
                                    const char* options);

#ifdef __cplusplus
}
#endif

#endif /* SHARED_TOOLS_HSDIS_H */
