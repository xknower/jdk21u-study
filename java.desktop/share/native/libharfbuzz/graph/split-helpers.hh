#ifndef GRAPH_SPLIT_HELPERS_HH
#define GRAPH_SPLIT_HELPERS_HH

namespace graph {

template<typename Context>
HB_INTERNAL
hb_vector_t<unsigned> actuate_subtable_split (Context& split_context,
                                              const hb_vector_t<unsigned>& split_points)
{
  hb_vector_t<unsigned> new_objects;
  if (!split_points)
    return new_objects;

  for (unsigned i = 0; i < split_points.length; i++)
  {
    unsigned start = split_points[i];
    unsigned end = (i < split_points.length - 1)
                   ? split_points[i + 1]
                   : split_context.original_count ();
    unsigned id = split_context.clone_range (start, end);

    if (id == (unsigned) -1)
    {
      new_objects.reset ();
      new_objects.allocated = -1; // mark error
      return new_objects;
    }
    new_objects.push (id);
  }

  if (!split_context.shrink (split_points[0]))
  {
    new_objects.reset ();
    new_objects.allocated = -1; // mark error
  }

  return new_objects;
}

}

#endif  // GRAPH_SPLIT_HELPERS_HH
