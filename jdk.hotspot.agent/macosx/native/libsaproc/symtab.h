#ifndef _SYMTAB_H_
#define _SYMTAB_H_

#include <stdint.h>

// interface to manage ELF or MachO symbol tables

struct symtab;

// build symbol table for a given ELF or MachO file escriptor
struct symtab* build_symtab(int fd, size_t *p_max_offset);

// destroy the symbol table
void destroy_symtab(struct symtab* symtab);

// search for symbol in the given symbol table. Adds offset
// to the base uintptr_t supplied. Returns NULL if not found.
uintptr_t search_symbol(struct symtab* symtab, uintptr_t base,
                      const char *sym_name, int *sym_size);

// look for nearest symbol for a given offset (not address - base
// subtraction done by caller
const char* nearest_symbol(struct symtab* symtab, uintptr_t offset,
                      uintptr_t* poffset);

#endif /*_SYMTAB_H_*/
