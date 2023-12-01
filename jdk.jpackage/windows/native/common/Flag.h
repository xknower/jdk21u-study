#ifndef Flag_h
#define Flag_h


template <class T, class T2=int, int Id=0>
class Flag {
public:
    explicit Flag(T2 v): val(v) {}

    bool operator == (const Flag& other) const {
        return val == other.val;
    }
    bool operator != (const Flag& other) const {
        return ! *this == other;
    }

    T2 value() const {
        return val;
    }

private:
    T2 val;
};

#endif // #ifndef Flag_h
