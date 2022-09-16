// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ide.hierarchy

import com.intellij.ide.util.treeView.NodeDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

data class Context(val removedNodes: MutableSet<UniqueCallableIdentifier>)
data class UniqueCallableIdentifier(val id: String)

abstract class CallHierarchyNodeDescriptorBase(
  project: Project,
  parentDescriptor: NodeDescriptor<*>?,
  element: PsiElement,
  isBase: Boolean,
  val context: Context,
  val uniqueIdentifier: UniqueCallableIdentifier
) : HierarchyNodeDescriptor(project, parentDescriptor, element, isBase)
