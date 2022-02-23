package com.winassistant.windowsFinder;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import java.util.HashMap;
import java.util.Map;

import com.winassistant.Application;
import com.winassistant.data.DataManager;

public interface WindowsFinder extends StdCallLibrary
{
    public static final Pointer WINDOW_TOPMOST    = new Pointer(-1);

    public static final Pointer WINDOW_NONTOPMOST = new Pointer(-2);

    public static final int SW_SHOW = 5;
    public static final int SW_RESTORE = 9;
    public static final int SW_SHOWMAXIMIZED = 3;


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

    boolean ShowWindow(Pointer hWnd, int nCmdShow);

    boolean SetWindowPos(Pointer hWnd, Pointer hWndAfter, int x, int y, int cx,
                         int cy, int flags);

    boolean EnumWindows(WinsowsCallback callback, StringByReference lParam);

    int GetWindowTextA(Pointer hWnd, StringByReference lpString, int nMaxCount);

    interface WinsowsCallback extends Callback {
        boolean invoke(Pointer hwnd, StringByReference lParam);
    }

    class ExampleCallbackImpl implements WinsowsCallback {
        public boolean invoke(Pointer hwnd, StringByReference windowsToSearch) {
            System.out.println("\nEnumerating Windows - Handle : " + hwnd);
            StringByReference name = new StringByReference(10000);
            WindowsFinder.INSTANCE.GetWindowTextA(hwnd, name, 100);
            System.out.println("Enumerating Windows - Name : " + name.getValue());

            if(name.getValue() != null && name.getValue().contains(windowsToSearch.getValue())){
                Application.windowsHandle = hwnd;
                return false;
            }else{
                return true;
            }
        }
    }
}