// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ide.hierarchy.actions

import com.intellij.ide.hierarchy.CallHierarchyBrowserBase
import com.intellij.ide.hierarchy.Context
import com.intellij.ide.hierarchy.HierarchyBrowserBaseEx
import com.intellij.ide.hierarchy.UniqueCallableIdentifier
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys

class RemoveCallHierarchySubtree : AnAction("Remove") {
  override fun actionPerformed(e: AnActionEvent) {
    val browser = e.getData(HierarchyBrowserBaseEx.HIERARCHY_BROWSER) as CallHierarchyBrowserBase
    val context = browser.myContent.getUserData(CallHierarchyBrowserBase.REMOVED_NODES).let {
      if (it != null) {
        it
      } else {
        val newContext = Context(mutableSetOf())
        browser.myContent.putUserData(CallHierarchyBrowserBase.REMOVED_NODES, newContext)
        newContext
      }
    }

    PlatformCoreDataKeys.PSI_ELEMENT_ARRAY.getData(browser)
      ?.map {
        UniqueCallableIdentifier(it.text)
      }?.let {
        context.removedNodes.addAll(it)
      }
  }
}
