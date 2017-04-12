using JOB.WEB.Helper;
using System.Linq;
using System.Web.Mvc;

namespace JOB.WEB.Controllers
{
    public class NavbarController : Controller
    {
        // GET: Navbar
        public ActionResult Index()
        {
            var data = new DadosMenu();
            return PartialView("_Navbar", data.NavbarItems().ToList());
        }
    }
}