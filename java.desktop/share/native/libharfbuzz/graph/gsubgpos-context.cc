#include "gsubgpos-graph.hh"

namespace graph {

gsubgpos_graph_context_t::gsubgpos_graph_context_t (hb_tag_t table_tag_,
                                                    graph_t& graph_)
    : table_tag (table_tag_),
      graph (graph_),
      lookup_list_index (0),
      lookups ()
{
  if (table_tag_ != HB_OT_TAG_GPOS
      &&  table_tag_ != HB_OT_TAG_GSUB)
    return;

  GSTAR* gstar = graph::GSTAR::graph_to_gstar (graph_);
  if (gstar) {
    gstar->find_lookups (graph, lookups);
    lookup_list_index = gstar->get_lookup_list_index (graph_);
  }
}

unsigned gsubgpos_graph_context_t::create_node (unsigned size)
{
  char* buffer = (char*) hb_calloc (1, size);
  if (!buffer)
    return -1;

  if (!add_buffer (buffer)) {
    // Allocation did not get stored for freeing later.
    hb_free (buffer);
    return -1;
  }

  return graph.new_node (buffer, buffer + size);
}

unsigned gsubgpos_graph_context_t::num_non_ext_subtables ()  {
  unsigned count = 0;
  for (auto l : lookups.values ())
  {
    if (l->is_extension (table_tag)) continue;
    count += l->number_of_subtables ();
  }
  return count;
}

}
