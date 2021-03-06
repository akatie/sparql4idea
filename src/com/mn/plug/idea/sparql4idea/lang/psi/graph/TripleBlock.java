package com.mn.plug.idea.sparql4idea.lang.psi.graph;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Triples block
 *
 * @author Matt Nathan
 */
public class TripleBlock extends ASTWrapperPsiElement {

  public TripleBlock(@NotNull ASTNode node) {
    super(node);
  }

  public PsiElement getSubject() {
    return getFirstChild();
  }

  public TripleProperty[] getPropertyList() {
    return findChildrenByClass(TripleProperty.class);
  }

  @Override
  public String toString() {
    return "TriplesBlock(" + getSubject() + " ...)";
  }
}
