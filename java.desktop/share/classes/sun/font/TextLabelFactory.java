package sun.font;

import java.awt.Font;

import java.awt.font.FontRenderContext;
import java.text.Bidi;

  /**
   * A factory for text labels.  Basically this just holds onto the stuff that
   * doesn't change-- the render context, context, and bidi info for the context-- and gets
   * called for each subrange you want to create.
   *
   * @see Font
   * @see FontRenderContext
   * @see java.awt.font.GlyphVector
   * @see TextLabel
   * @see ExtendedTextLabel
   * @see Bidi
   * @see java.awt.font.TextLayout
   */

public final class TextLabelFactory {
  private final FontRenderContext frc;
  private final char[] text;
  private final Bidi bidi;
  private Bidi lineBidi;
  private final int flags;
  private int lineStart;
  private int lineLimit;

  /**
   * Initialize a factory to produce glyph arrays.
   * @param frc the FontRenderContext to use for the arrays to be produced.
   * @param text the text of the paragraph.
   * @param bidi the bidi information for the paragraph text, or null if the
   * entire text is left-to-right text.
   */
  public TextLabelFactory(FontRenderContext frc,
                          char[] text,
                          Bidi bidi,
                          int flags) {
    this.frc = frc;
    this.text = text.clone();
    this.bidi = bidi;
    this.flags = flags;
    this.lineBidi = bidi;
    this.lineStart = 0;
    this.lineLimit = text.length;
  }

  public FontRenderContext getFontRenderContext() {
    return frc;
  }

  public Bidi getLineBidi() {
    return lineBidi;
  }

  /**
   * Set a line context for the factory.  Shaping only occurs on this line.
   * Characters are ordered as they would appear on this line.
   * @param lineStart the index within the text of the start of the line.
   * @param lineLimit the index within the text of the limit of the line.
   */
  public void setLineContext(int lineStart, int lineLimit) {
    this.lineStart = lineStart;
    this.lineLimit = lineLimit;
    if (bidi != null) {
      lineBidi = bidi.createLineBidi(lineStart, lineLimit);
    }
  }

  /**
   * Create an extended glyph array for the text between start and limit.
   *
   * @param font the font to use to generate glyphs and character positions.
   * @param start the start of the subrange for which to create the glyph array
   * @param limit the limit of the subrange for which to create glyph array
   *
   * Start and limit must be within the bounds of the current line.  If no
   * line context has been set, the entire text is used as the current line.
   * The text between start and limit will be treated as though it all has
   * the same bidi level (and thus the same directionality) as the character
   * at start.  Clients should ensure that all text between start and limit
   * has the same bidi level for the current line.
   */
  public ExtendedTextLabel createExtended(Font font,
                                          CoreMetrics lm,
                                          Decoration decorator,
                                          int start,
                                          int limit) {

    if (start >= limit || start < lineStart || limit > lineLimit) {
      throw new IllegalArgumentException("bad start: " + start + " or limit: " + limit);
    }

    int level = lineBidi == null ? 0 : lineBidi.getLevelAt(start - lineStart);
    int linedir = (lineBidi == null || lineBidi.baseIsLeftToRight()) ? 0 : 1;
    int layoutFlags = flags & ~0x9; // remove bidi, line direction flags
    if ((level & 0x1) != 0) layoutFlags |= 1; // rtl
    if ((linedir & 0x1) != 0) layoutFlags |= 8; // line rtl

    TextSource source = new StandardTextSource(text, start, limit - start, lineStart, lineLimit - lineStart, level, layoutFlags, font, frc, lm);
    return new ExtendedTextSourceLabel(source, decorator);
  }

  /**
   * Create a simple glyph array for the text between start and limit.
   *
   * @param font the font to use to generate glyphs and character positions.
   * @param start the start of the subrange for which to create the glyph array
   * @param limit the limit of the subrange for which to create glyph array
   */
  public TextLabel createSimple(Font font,
                                CoreMetrics lm,
                                int start,
                                int limit) {

    if (start >= limit || start < lineStart || limit > lineLimit) {
      throw new IllegalArgumentException("bad start: " + start + " or limit: " + limit);
    }

    int level = lineBidi == null ? 0 : lineBidi.getLevelAt(start - lineStart);
    int linedir = (lineBidi == null || lineBidi.baseIsLeftToRight()) ? 0 : 1;
    int layoutFlags = flags & ~0x9; // remove bidi, line direction flags
    if ((level & 0x1) != 0) layoutFlags |= 1; // rtl
    if ((linedir & 0x1) != 0) layoutFlags |= 8; // line rtl
    TextSource source = new StandardTextSource(text, start, limit - start, lineStart, lineLimit - lineStart, level, layoutFlags, font, frc, lm);
    return new TextSourceLabel(source);
  }
}
