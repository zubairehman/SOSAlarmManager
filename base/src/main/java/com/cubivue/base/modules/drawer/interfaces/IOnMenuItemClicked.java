package com.cubivue.base.modules.drawer.interfaces;

import com.cubivue.base.models.menu.MenuDto;

import java.util.List;

public interface IOnMenuItemClicked {
    void onItemClick(List<MenuDto> items, int position);
}
