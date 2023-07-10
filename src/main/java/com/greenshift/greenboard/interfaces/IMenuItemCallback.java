package com.greenshift.greenboard.interfaces;


import com.greenshift.greenboard.models.ui.LeftMenuItem;

@FunctionalInterface
public
interface IMenuItemCallback {
    void execute(LeftMenuItem menuItem);
}