#include "graph.hh"
#include "../hb-ot-layout-gsubgpos.hh"

#ifndef GRAPH_GSUBGPOS_CONTEXT_HH
#define GRAPH_GSUBGPOS_CONTEXT_HH

namespace graph {

struct Lookup;

struct gsubgpos_graph_context_t
{
  hb_tag_t table_tag;
  graph_t& graph;
  unsigned lookup_list_index;
  hb_hashmap_t<unsigned, graph::Lookup*> lookups;
  hb_hashmap_t<unsigned, unsigned> subtable_to_extension;

  HB_INTERNAL gsubgpos_graph_context_t (hb_tag_t table_tag_,
                                        graph_t& graph_);

  HB_INTERNAL unsigned create_node (unsigned size);

  bool add_buffer (char* buffer)
  {
    return graph.add_buffer (buffer);
  }

 private:
  HB_INTERNAL unsigned num_non_ext_subtables ();
};

}

#endif  // GRAPH_GSUBGPOS_CONTEXT
