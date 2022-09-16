// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.ide.hierarchy

import com.intellij.openapi.project.Project

abstract class CallHierarchyTreeStructure(project: Project, root: HierarchyNodeDescriptor) : HierarchyTreeStructure(project, root) {
  override fun buildChildren(descriptor: HierarchyNodeDescriptor): Array<Any> {
    val descriptor = descriptor as? CallHierarchyNodeDescriptorBase ?: return arrayOf()
    val removedNodes = descriptor.context.removedNodes
    return buildChildrenInternal(descriptor)
      .filter {
        it.uniqueIdentifier !in removedNodes
      }.toTypedArray()
  }

  protected abstract fun buildChildrenInternal(descriptor: HierarchyNodeDescriptor): Array<CallHierarchyNodeDescriptorBase>
}
