// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.kotlin.idea.refactoring.safeDelete;

public abstract class AbstractK2SafeDeleteTest extends AbstractSafeDeleteTest {
    @Override
    protected boolean isFirPlugin() {
        return true;
    }
}
