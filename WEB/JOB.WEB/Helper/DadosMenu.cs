using JOB.WEB.Models;
using System.Collections.Generic;
using System.Linq;

namespace JOB.WEB.Helper
{
    public class DadosMenu
    {
        public IEnumerable<MenuViewModel> NavbarItems()
        {
            var menu = new List<MenuViewModel>();

            menu.Add(new MenuViewModel { Id = 10, nameOption = "Dashboard", controller = "Home", action = "Index", imageClass = "fa fa-dashboard fa-fw", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 14, nameOption = "Usuários", controller = "Usuario", action = "Index", imageClass = "fa fa-user fa-fw", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 15, nameOption = "Profissionais", controller = "Profissional", action = "Index", imageClass = "glyphicon glyphicon-briefcase", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 16, nameOption = "Jobs (Cliente)", controller = "Job", action = "Cliente", imageClass = "fa fa-users", status = true, isParent = false, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 17, nameOption = "Jobs (Freela)", controller = "Job", action = "Freela", imageClass = "fa fa-users", status = true, isParent = false, parentId = 0 });

            return menu.ToList();
        }
    }
}