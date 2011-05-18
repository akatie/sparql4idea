package com.mn.plug.idea.sparql4idea.lang.parser.parsing.select;

import com.intellij.lang.PsiBuilder;
import com.mn.plug.idea.sparql4idea.lang.lexer.SparqlTokenTypes;
import com.mn.plug.idea.sparql4idea.lang.parser.SparqlElementTypes;
import com.mn.plug.idea.sparql4idea.lang.parser.parsing.common.DatasetClause;
import com.mn.plug.idea.sparql4idea.lang.parser.parsing.util.ParserUtils;

import static com.mn.plug.idea.sparql4idea.lang.lexer.SparqlTokenTypes.*;
import static com.mn.plug.idea.sparql4idea.lang.lexer.SparqlTokenTypes.VAR;

/**
 * Select query parser.
 *
 * @author Matt Nathan
 */
public class Select {

  public static void parse(PsiBuilder builder) {
    final PsiBuilder.Marker selectQuery = builder.mark();

    if (ParserUtils.getToken(builder, SparqlTokenTypes.KW_SELECT, "Expecting \"SELECT\"")) {

      if (builder.getTokenType() == KW_DISTINCT || builder.getTokenType() == KW_REDUCED) {
        builder.advanceLexer();
      }

      parseProjectionVariables(builder);

      while (ParserUtils.lookAhead(builder, KW_FROM)) {
        DatasetClause.parse(builder);
      }
    }
    selectQuery.done(SparqlElementTypes.SELECT_QUERY);
  }

  private static void parseProjectionVariables(PsiBuilder builder) {
    final PsiBuilder.Marker vars = builder.mark();

    if (ParserUtils.lookAhead(builder, OP_MULT)) {
      builder.advanceLexer();
    } else if (ParserUtils.lookAhead(builder, VAR)) {
      while (ParserUtils.getToken(builder, VAR));
    } else {
      builder.error("Expecting Variable or *");
    }

    vars.done(SparqlElementTypes.PROJECTION_VARIABLES);
  }
}