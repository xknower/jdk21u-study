#ifndef _SALIBELF_H_
#define _SALIBELF_H_

#include <elf.h>
#include "elfmacros.h"
#include "libproc_impl.h"

struct elf_section {
  ELF_SHDR   *c_shdr;
  void       *c_data;
};

// read ELF file header.
int read_elf_header(int fd, ELF_EHDR* ehdr);

// is given file descriptor corresponds to an ELF file?
bool is_elf_file(int fd);

// read program header table of an ELF file. caller has to
// free the result pointer after use. NULL on failure.
ELF_PHDR* read_program_header_table(int fd, ELF_EHDR* hdr);

// read section header table of an ELF file. caller has to
// free the result pointer after use. NULL on failure.
ELF_SHDR* read_section_header_table(int fd, ELF_EHDR* hdr);

// read a particular section's data. caller has to free the
// result pointer after use. NULL on failure.
void* read_section_data(int fd, ELF_EHDR* ehdr, ELF_SHDR* shdr);

// find the base address at which the library wants to load itself
uintptr_t find_base_address(int fd, ELF_EHDR* ehdr);

// Find an ELF section.
struct elf_section *find_section_by_name(char *name,
                                         int fd,
                                         ELF_EHDR *ehdr,
                                         struct elf_section *scn_cache);
#endif /* _SALIBELF_H_ */
