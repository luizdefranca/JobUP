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

            menu.Add(new MenuViewModel { Id = 10, nameOption = "Administrador", controller = "", action = "", imageClass = "glyphicon glyphicon-user", status = true, isParent = true, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 11, nameOption = "Dashboard", controller = "Home", action = "Index", imageClass = "", status = true, isParent = false, parentId = 10 });
            menu.Add(new MenuViewModel { Id = 12, nameOption = "Usuários", controller = "Usuario", action = "Index", imageClass = "", status = true, isParent = false, parentId = 10 });

            menu.Add(new MenuViewModel { Id = 20, nameOption = "Cliente", controller = "", action = "", imageClass = "fa fa-users", status = true, isParent = true, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 21, nameOption = "Profissionais", controller = "Profissional", action = "Index", imageClass = "", status = true, isParent = false, parentId = 20 });
            menu.Add(new MenuViewModel { Id = 22, nameOption = "Jobs", controller = "Job", action = "Cliente", imageClass = "", status = true, isParent = false, parentId = 20 });

            menu.Add(new MenuViewModel { Id = 30, nameOption = "Freela", controller = "", action = "", imageClass = "glyphicon glyphicon-briefcase", status = true, isParent = true, parentId = 0 });
            menu.Add(new MenuViewModel { Id = 31, nameOption = "Jobs", controller = "Job", action = "Freela", imageClass = "", status = true, isParent = false, parentId = 30 });

            return menu.ToList();
        }
    }
}