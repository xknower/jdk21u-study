#ifndef SourceCodePos_h
#define SourceCodePos_h


//
// Position in source code.
//

struct SourceCodePos
{
    SourceCodePos(const char* fl, const char* fnc, int l):
                                                file(fl), func(fnc), lno(l)
    {
    }

    const char* file;
    const char* func;
    int lno;
};


// Initializes SourceCodePos instance with the
// information from the point of calling.
#ifdef THIS_FILE
    #define JP_SOURCE_CODE_POS SourceCodePos(THIS_FILE, __FUNCTION__, __LINE__)
#else
    #define JP_SOURCE_CODE_POS SourceCodePos(__FILE__, __FUNCTION__, __LINE__)
#endif


#endif // #ifndef SourceCodePos_h
