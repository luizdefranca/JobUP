﻿using System.Collections.Generic;
using System.Linq;
using System.Security.Principal;
using JOB.WEB.Models;
using Microsoft.AspNet.Identity;

namespace JOB.WEB.Helper
{
    public class DadosMenu
    {
        public IEnumerable<MenuViewModel> NavbarItems()
        {
            var menu = new List<MenuViewModel>();

            menu.Add(new MenuViewModel { Id = 10, nameOption = "Dashboard", controller = "Home", action = "Index", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 14, nameOption = "Usuários", controller = "Usuario", action = "Index", imageClass = "fa fa-user fa-fw", status = true, isParent = false, parentId = 0 });

            //menu.Add(new MenuViewModel { Id = 2, nameOption = "Charts", imageClass = "fa fa-bar-chart-o fa-fw", status = true, isParent = true, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 3, nameOption = "Flot Charts", controller = "Home", action = "FlotCharts", status = true, isParent = false, parentId = 2 });
            //menu.Add(new MenuViewModel { Id = 4, nameOption = "Morris.js Charts", controller = "Home", action = "MorrisCharts", status = true, isParent = false, parentId = 2 });
            //menu.Add(new MenuViewModel { Id = 5, nameOption = "Tables", controller = "Home", action = "Tables", imageClass = "fa fa-table fa-fw", status = true, isParent = false, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 6, nameOption = "Forms", controller = "Home", action = "Forms", imageClass = "fa fa-edit fa-fw", status = true, isParent = false, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 7, nameOption = "UI Elements", imageClass = "fa fa-wrench fa-fw", status = true, isParent = true, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 8, nameOption = "Panels and Wells", controller = "Home", action = "Panels", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 9, nameOption = "Buttons", controller = "Home", action = "Buttons", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 10, nameOption = "Notifications", controller = "Home", action = "Notifications", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 11, nameOption = "Typography", controller = "Home", action = "Typography", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 12, nameOption = "Icons", controller = "Home", action = "Icons", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 13, nameOption = "Grid", controller = "Home", action = "Grid", status = true, isParent = false, parentId = 7 });
            //menu.Add(new MenuViewModel { Id = 14, nameOption = "Multi-Level Dropdown", imageClass = "fa fa-sitemap fa-fw", status = true, isParent = true, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 15, nameOption = "Second Level Item", status = true, isParent = false, parentId = 14 });
            //menu.Add(new MenuViewModel { Id = 16, nameOption = "Sample Pages", imageClass = "fa fa-files-o fa-fw", status = true, isParent = true, parentId = 0 });
            //menu.Add(new MenuViewModel { Id = 17, nameOption = "Blank Page", controller = "Home", action = "Blank", status = true, isParent = false, parentId = 16 });
            //menu.Add(new MenuViewModel { Id = 18, nameOption = "Login Page", controller = "Home", action = "Login", status = true, isParent = false, parentId = 16 });

            return menu.ToList();
        }
    }
}