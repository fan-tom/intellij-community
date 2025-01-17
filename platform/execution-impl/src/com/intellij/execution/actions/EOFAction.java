// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.execution.actions;

import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.DumbAwareAction;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;

public final class EOFAction extends DumbAwareAction {

  public static final @NonNls String ACTION_ID = "SendEOF";

  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    RunContentDescriptor descriptor = StopAction.getRecentlyStartedContentDescriptor(e.getDataContext());
    ProcessHandler handler = descriptor != null ? descriptor.getProcessHandler() : null;
    e.getPresentation().setEnabledAndVisible(e.getData(LangDataKeys.CONSOLE_VIEW) != null
                                             && e.getData(CommonDataKeys.EDITOR) != null
                                             && handler != null
                                             && !handler.isProcessTerminated());
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    RunContentDescriptor descriptor = StopAction.getRecentlyStartedContentDescriptor(e.getDataContext());
    ProcessHandler activeProcessHandler = descriptor != null ? descriptor.getProcessHandler() : null;
    if (activeProcessHandler == null || activeProcessHandler.isProcessTerminated()) return;

    try (OutputStream input = activeProcessHandler.getProcessInput()) {
      if (input != null) {
        ConsoleView console = e.getData(LangDataKeys.CONSOLE_VIEW);
        if (console != null) {
          console.print("^D\n", ConsoleViewContentType.SYSTEM_OUTPUT);
        }
      }
    }
    catch (IOException ignored) {
    }
  }
}
