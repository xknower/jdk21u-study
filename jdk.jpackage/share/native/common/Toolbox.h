#ifndef Toolbox_h
#define Toolbox_h


#include <algorithm>
#include "tstrings.h"


/**
 * Placeholder for API not falling into any particular category.
 */



struct deletePtr {
    template <class PtrT>
    void operator () (PtrT ptr) const {
        delete ptr;
    }
};


/**
 * Deletes all pointers in the given range of items.
 */
template <class It>
void deleteAll(It b, It e) {
    std::for_each(b, e, deletePtr());
}

/**
 * Deletes all pointers from the container.
 */
template <class Ctnr>
void deleteAll(Ctnr& ctnr) {
    deleteAll(std::begin(ctnr), std::end(ctnr));
}


/**
 * Applies std::for_each() to the given object that has begin() and end()
 * methods.
 */
template <class Ctnr, class Fn>
void forEach(Ctnr& ctnr, Fn fn) {
    std::for_each(std::begin(ctnr), std::end(ctnr), fn);
}

template <class Ctnr, class Fn>
void forEach(const Ctnr& ctnr, Fn fn) {
    std::for_each(std::begin(ctnr), std::end(ctnr), fn);
}


/**
 * Runs the given functor from destructor. Don't use directly.
 * This is just a helper for runAtEndOfScope().
 */
template <class Fn>
class AtEndOfScope {
    Fn func;
    bool theAbort;
public:
    explicit AtEndOfScope(Fn f): func(f), theAbort(false) {
    }
    ~AtEndOfScope() {
        if (!theAbort) {
            JP_NO_THROW(func());
        }
    }
    void abort(bool v=true) {
        theAbort = v;
    }
};

/**
 * Helper to create AtEndOfScope instance without need to
 * specify template type explicitly. Like std::make_pair to construct
 * std::pair instance.
 *
 * Use case:
 * Say you need to call a function (foo()) at exit from another
 * function (bar()).
 * You will normally do:
 *  void bar() {
 *    workload();
 *    foo();
 *  }
 *
 * If workload() can throw exceptions things become little bit more
 * complicated:
 *  void bar() {
 *    JP_NO_THROW(workload());
 *    foo();
 *  }
 *
 * If there is branching in bar() it is little bit more complicated again:
 *  int bar() {
 *    if (...) {
 *      JP_NO_THROW(workload());
 *      foo();
 *      return 0;
 *    }
 *    if (...) {
 *      JP_NO_THROW(workload2());
 *      foo();
 *      return 1;
 *    }
 *    foo();
 *    return 2;
 *  }
 *
 * So for relatively complex bar() this approach will end up with
 * missing foo() call. The standard solution to avoid errors like this,
 * is to call foo() from some object's destructor, i.e. delegate
 * responsibility to ensure it is always called to compiler:
 *
 * struct FooCaller {
 *     ~FooCaller() { JP_NO_THROW(foo()); }
 * };
 *
 *  int bar() {
 *    FooCaller fooCaller;
 *    if (...) {
 *      JP_NO_THROW(workload());
 *      return 0;
 *    }
 *    if (...) {
 *      JP_NO_THROW(workload2());
 *      foo();
 *    }
 *    return 2;
 *  }
 *
 * However it is annoying to explicitly create FooCaller-like types
 * for tasks like this. Use of runAtEndOfScope() saves you from this:
 *
 *  int bar() {
 *    const auto fooCaller = runAtEndOfScope(foo);
 *    if (...) {
 *      JP_NO_THROW(workload());
 *      return 0;
 *    }
 *    if (...) {
 *      JP_NO_THROW(workload2());
 *      foo();
 *    }
 *    return 2;
 *  }
 *
 */
template <class Fn>
AtEndOfScope<Fn> runAtEndOfScope(Fn func) {
    return AtEndOfScope<Fn>(func);
}

#endif // #ifndef Toolbox_h
