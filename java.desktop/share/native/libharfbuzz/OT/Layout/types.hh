#ifndef OT_LAYOUT_TYPES_HH
#define OT_LAYOUT_TYPES_HH

namespace OT {
namespace Layout {

struct SmallTypes {
  static constexpr unsigned size = 2;
  using large_int = uint32_t;
  using HBUINT = HBUINT16;
  using HBGlyphID = HBGlyphID16;
  using Offset = Offset16;
  template <typename Type, bool has_null=true>
  using OffsetTo = OT::Offset16To<Type, has_null>;
  template <typename Type>
  using ArrayOf = OT::Array16Of<Type>;
  template <typename Type>
  using SortedArrayOf = OT::SortedArray16Of<Type>;
};

struct MediumTypes {
  static constexpr unsigned size = 3;
  using large_int = uint64_t;
  using HBUINT = HBUINT24;
  using HBGlyphID = HBGlyphID24;
  using Offset = Offset24;
  template <typename Type, bool has_null=true>
  using OffsetTo = OT::Offset24To<Type, has_null>;
  template <typename Type>
  using ArrayOf = OT::Array24Of<Type>;
  template <typename Type>
  using SortedArrayOf = OT::SortedArray24Of<Type>;
};

}
}

#endif  /* OT_LAYOUT_TYPES_HH */
