using System.Collections.Generic;
using System.Linq;
using JOB.WEB.Models;

namespace JOB.WEB.Helper
{
    public class DadosMenu
    {
        public IEnumerable<MenuViewModel> NavbarItems()
        {
            var menu = new List<MenuViewModel>();

            menu.Add(new MenuViewModel { Id = 10, nameOption = "Dashboard", controller = "Home", action = "Index", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 11, nameOption = "Administrador", controller = "Usuario", action = "Index", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = true, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 13, nameOption = "Dashboard", controller = "Usuario", action = "Dashboard", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = false, parentId = 11 });
            menu.Add(new MenuViewModel { Id = 14, nameOption = "Usuários", controller = "Usuario", action = "Index", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = false, parentId = 11 });


            return menu.ToList();
        }
    }
}
