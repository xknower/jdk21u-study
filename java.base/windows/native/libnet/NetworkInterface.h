#ifndef NETWORK_INTERFACE_H
#define NETWORK_INTERFACE_H

#include "net_util.h"

/*
 * Structures used when enumerating interfaces and addresses
 */
typedef struct _netaddr {
    SOCKADDR_INET Address;
    UINT8 PrefixLength;
    struct _netaddr *Next;
} netaddr;

#endif
