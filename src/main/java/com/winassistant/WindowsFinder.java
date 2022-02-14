package com.winassistant;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import java.util.HashMap;
import java.util.Map;

import static com.sun.jna.Library.OPTION_FUNCTION_MAPPER;
import static com.sun.jna.Library.OPTION_TYPE_MAPPER;

public interface WindowsFinder extends StdCallLibrary
{
    public static final int WINDOW_TOPMOST    = -1;

    public static final int WINDOW_NONTOPMOST = -2;

    Map<String, Object> UNICODE_OPTIONS   = new HashMap<String, Object>()
    {
        private static final long serialVersionUID = 1L;

        {
            put(OPTION_TYPE_MAPPER,
                    W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER,
                    W32APIFunctionMapper.UNICODE);
        }
    };

    Map<String, Object>     ASCII_OPTIONS     = new HashMap<String, Object>()
    {
        private static final long serialVersionUID = 1L;
        {
            put(OPTION_TYPE_MAPPER,
                    W32APITypeMapper.ASCII);
            put(OPTION_FUNCTION_MAPPER,
                    W32APIFunctionMapper.ASCII);
        }
    };

    Map<String, Object>     DEFAULT_OPTIONS   = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS
            : UNICODE_OPTIONS;

    WindowsFinder INSTANCE = (WindowsFinder) Native.load(
            "user32", WindowsFinder.class,
            DEFAULT_OPTIONS);

    Pointer FindWindow(String lpClassName, String lpWindowName);

    void SetFocus(Pointer hWnd);

    boolean SetWindowPos(Pointer hWnd, int hWndAfter, int x, int y, int cx,
                         int cy, int flags);
}