typedef LONG (*FPTR_SCardEstablishContext)(DWORD dwScope,
                LPCVOID pvReserved1,
                LPCVOID pvReserved2,
                LPSCARDCONTEXT phContext);

typedef LONG (*FPTR_SCardConnect)(SCARDCONTEXT hContext,
                LPCSTR szReader,
                DWORD dwShareMode,
                DWORD dwPreferredProtocols,
                LPSCARDHANDLE phCard, LPDWORD pdwActiveProtocol);

typedef LONG (*FPTR_SCardDisconnect)(SCARDHANDLE hCard, DWORD dwDisposition);

typedef LONG (*FPTR_SCardStatus)(SCARDHANDLE hCard,
                LPSTR mszReaderNames,
                LPDWORD pcchReaderLen,
                LPDWORD pdwState,
                LPDWORD pdwProtocol,
                LPBYTE pbAtr, LPDWORD pcbAtrLen);

typedef LONG (*FPTR_SCardGetStatusChange)(SCARDCONTEXT hContext,
                DWORD dwTimeout,
                SCARD_READERSTATE *rgReaderStates, DWORD cReaders);

typedef LONG (*FPTR_SCardTransmit)(SCARDHANDLE hCard,
                const SCARD_IO_REQUEST *pioSendPci,
                LPCBYTE pbSendBuffer,
                DWORD cbSendLength,
                SCARD_IO_REQUEST *pioRecvPci,
                LPBYTE pbRecvBuffer, LPDWORD pcbRecvLength);

typedef LONG (*FPTR_SCardListReaders)(SCARDCONTEXT hContext,
                LPCSTR mszGroups,
                LPSTR mszReaders, LPDWORD pcchReaders);

typedef LONG (*FPTR_SCardBeginTransaction)(SCARDHANDLE hCard);

typedef LONG (*FPTR_SCardEndTransaction)(SCARDHANDLE hCard,
                DWORD dwDisposition);

typedef LONG (*FPTR_SCardControl)(SCARDHANDLE hCard, DWORD dwControlCode,
                LPCVOID pbSendBuffer, DWORD cbSendLength, LPVOID pbRecvBuffer,
                DWORD pcbRecvLength, LPDWORD lpBytesReturned);

#define CALL_SCardEstablishContext(dwScope, pvReserved1, pvReserved2, phContext) \
    ((scardEstablishContext)(dwScope, pvReserved1, pvReserved2, phContext))

#define CALL_SCardConnect(hContext, szReader, dwSharedMode, dwPreferredProtocols, phCard, pdwActiveProtocols) \
    ((scardConnect)(hContext, szReader, dwSharedMode, dwPreferredProtocols, phCard, pdwActiveProtocols))

#define CALL_SCardDisconnect(hCard, dwDisposition) \
    ((scardDisconnect)(hCard, dwDisposition))

#define CALL_SCardStatus(hCard, mszReaderNames, pcchReaderLen, pdwState, pdwProtocol, pbAtr, pcbAtrLen) \
    ((scardStatus)(hCard, mszReaderNames, pcchReaderLen, pdwState, pdwProtocol, pbAtr, pcbAtrLen))

#define CALL_SCardGetStatusChange(hContext, dwTimeout, rgReaderStates, cReaders) \
    ((scardGetStatusChange)(hContext, dwTimeout, rgReaderStates, cReaders))

#define CALL_SCardTransmit(hCard, pioSendPci, pbSendBuffer, cbSendLength, \
                            pioRecvPci, pbRecvBuffer, pcbRecvLength) \
    ((scardTransmit)(hCard, pioSendPci, pbSendBuffer, cbSendLength, \
                            pioRecvPci, pbRecvBuffer, pcbRecvLength))

#define CALL_SCardListReaders(hContext, mszGroups, mszReaders, pcchReaders) \
    ((scardListReaders)(hContext, mszGroups, mszReaders, pcchReaders))

#define CALL_SCardBeginTransaction(hCard) \
    ((scardBeginTransaction)(hCard))

#define CALL_SCardEndTransaction(hCard, dwDisposition) \
    ((scardEndTransaction)(hCard, dwDisposition))

#define CALL_SCardControl(hCard, dwControlCode, pbSendBuffer, cbSendLength, \
            pbRecvBuffer, pcbRecvLength, lpBytesReturned) \
    ((scardControl)(hCard, dwControlCode, pbSendBuffer, cbSendLength, \
            pbRecvBuffer, pcbRecvLength, lpBytesReturned))

extern FPTR_SCardEstablishContext scardEstablishContext;
extern FPTR_SCardConnect scardConnect;
extern FPTR_SCardDisconnect scardDisconnect;
extern FPTR_SCardStatus scardStatus;
extern FPTR_SCardGetStatusChange scardGetStatusChange;
extern FPTR_SCardTransmit scardTransmit;
extern FPTR_SCardListReaders scardListReaders;
extern FPTR_SCardBeginTransaction scardBeginTransaction;
extern FPTR_SCardEndTransaction scardEndTransaction;
extern FPTR_SCardControl scardControl;
