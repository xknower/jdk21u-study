#ifndef _PS_CORE_COMMON_H_
#define _PS_CORE_COMMON_H_

map_info* core_lookup(struct ps_prochandle *ph, uintptr_t addr);
map_info* add_map_info(struct ps_prochandle* ph, int fd, off_t offset,
                       uintptr_t vaddr, size_t memsz, uint32_t flags);
void core_release(struct ps_prochandle* ph);
bool read_string(struct ps_prochandle* ph, uintptr_t addr, char* buf, size_t size);
bool init_classsharing_workaround(struct ps_prochandle* ph);

#endif // _PS_CORE_COMMON_H_
