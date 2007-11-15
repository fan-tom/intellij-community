
package com.intellij.refactoring.actions;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageRefactoringSupport;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;

/**
 *
 */
public class IntroduceVariableAction extends BaseRefactoringAction {
  public IntroduceVariableAction() {
    setInjectedContext(true);
  }

  protected boolean isAvailableInEditorOnly() {
    return true;
  }

  protected boolean isEnabledOnElements(PsiElement[] elements) {
    return false;
  }

  protected RefactoringActionHandler getHandler(DataContext dataContext) {
    final Language language = DataKeys.LANGUAGE.getData(dataContext);
    if (language != null) {
      return LanguageRefactoringSupport.INSTANCE.forLanguage(language).getIntroduceVariableHandler();
    }

    return null;
    
  }

  protected boolean isAvailableForLanguage(Language language) {
    return LanguageRefactoringSupport.INSTANCE.forLanguage(language).getIntroduceVariableHandler() != null;
  }
}
